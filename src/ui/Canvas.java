package ui;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mode.Mode;
import shape.Port;
import shape.BasicObject;
import shape.LineObject;

public final class Canvas extends Pane {
	private static final Canvas INSTANCE = new Canvas();

	private ArrayList<BasicObject> objects = new ArrayList<BasicObject>();
	private ArrayList<LineObject> lines = new ArrayList<LineObject>();

	private Rectangle selectRange;
	private Point2D rangeOrigin;
	private boolean hasSelectRange = false;

	private Canvas() {
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void SetMouseEvent(Mode mode) {
		setOnMouseClicked(mode.getClickEvent());
		setOnMousePressed(mode.getPressEvent());
		setOnMouseDragged(mode.getDragEvent());
		setOnMouseReleased(mode.getReleaseEvent());
	}

	public ArrayList<BasicObject> GetBasicObjs() {
		return objects;
	}

	public void AddBasicObject(Point2D mouse, BasicObject newObject) {
		newObject.SetOrigin(mouse);
		getChildren().addAll(newObject.GetShape());
		newObject.SetDepth(99 - objects.size());
		objects.add(newObject);
	}

	public BasicObject GetInsideObject(Point2D point) {
		int index = -1;
		int depth = 100;

		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).GetSelectedObjBound(point) != null) {
				if (objects.get(i).GetDepth() < depth) {
					index = i;
					depth = objects.get(i).GetDepth();
				}
			}
		}

		if (index == -1)
			return null;
		else
			return objects.get(index);
	}

	public void SetLineStartPoint(Point2D mouse, LineObject newLine) {
		BasicObject startObject = GetInsideObject(mouse);
		Port startPort = startObject.ChoosePort(mouse);
		System.out.println(startPort.getX());
		System.out.println(startPort.getY());

		newLine.SetStartPort(startPort);
		Point2D startPoint = new Point2D(startPort.getX(), startPort.getY());
		newLine.SetOrigin(startPoint);
		newLine.SetDest(mouse);

		getChildren().addAll(newLine.GetShape());
	}

	public void SetLineEndPoint(Point2D mouse, LineObject newLine) {
		BasicObject endObject = GetInsideObject(mouse);
		Port endPort = endObject.ChoosePort(mouse);

		newLine.SetEndPort(endPort);
		Point2D endPoint = new Point2D(endPort.getX(), endPort.getY());
		newLine.SetDest(endPoint);
		lines.add(newLine);
	}

	public void SelectWithPress(Point2D mouse) {
		BasicObject obj = GetInsideObject(mouse);

		if (obj != null) {
			SelectSingle(mouse, obj);
			saveTranslates(mouse);
		}

		else {
			UnselectObjects();
			CreateSelectRectangle(mouse);
		}
	}

	private void SelectSingle(Point2D mouse, BasicObject selectedObject) {
		selectedObject.SetSelect(true);
		selectedObject.SetPortsVisible(true);

		for (BasicObject obj : objects) {
			if (obj.equals(selectedObject) == false) {
				obj.SetSelect(false);
				obj.SetPortsVisible(false);
			}
		}
	}

	private void saveTranslates(Point2D mouse) {
		for (BasicObject object : objects) {
			if (object.checkIfSelected() == true) {
				object.SetOldTranslate(mouse);
			}
		}
	}

	private void UnselectObjects() {
		for (BasicObject obj : objects) {
			obj.SetSelect(false);
			obj.SetPortsVisible(false);
		}
	}

	private void CreateSelectRectangle(Point2D mouse) {
		selectRange = new Rectangle(0, 0);
		selectRange.setFill(Color.TRANSPARENT);
		selectRange.setStroke(Color.GRAY);
		selectRange.setX(mouse.getX());
		selectRange.setY(mouse.getY());
		rangeOrigin = mouse;
		getChildren().add(selectRange);
		hasSelectRange = true;
	}

	public void DrawSelectRange(Point2D mouse) {
		double width = mouse.getX() - rangeOrigin.getX();
		double height = mouse.getY() - rangeOrigin.getY();

		if (width > 0) {
			selectRange.setWidth(width);
		} else {
			selectRange.setWidth((-1.0) * width);
			selectRange.setX(mouse.getX());
		}

		if (height > 0) {
			selectRange.setHeight(height);
		} else {
			selectRange.setHeight((-1.0) * height);
			selectRange.setY(mouse.getY());
		}
	}

	public void SelectMultiple() {
		for (int i = 0; i < objects.size(); i++) {
			if (selectRange.getBoundsInParent().contains(objects.get(i).GetBound().getBoundsInParent())) {
				objects.get(i).SetSelect(true);
				objects.get(i).SetPortsVisible(true);
			}
		}

		getChildren().remove(selectRange);
		hasSelectRange = false;
	}

	public void MoveObjects(Point2D mouse) {
		for (BasicObject object : objects) {
			if (object.checkIfSelected() == true) {
				object.Move(mouse);
			}
		}

		// redraw lines
		for (LineObject line : lines) {
			Point2D startPoint = new Point2D(line.GetStartPort().getX(), line.GetStartPort().getY());
			Point2D endPoint = new Point2D(line.GetEndPort().getX(), line.GetEndPort().getY());
			line.SetOrigin(startPoint);
			line.SetDest(endPoint);
		}
	}

	public boolean HasSelectRange() {
		return hasSelectRange;
	}

	public static Canvas getInstance() {
		return INSTANCE;
	}
}
