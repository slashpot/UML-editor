package ui;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mode.Mode;
import shape.BasicObject;
import shape.LineObject;

public final class Canvas extends Pane {
	private static final Canvas INSTANCE = new Canvas();

	private ArrayList<BasicObject> objects = new ArrayList<BasicObject>();
	private ArrayList<LineObject> lines = new ArrayList<LineObject>();

	private Canvas() {
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void SetMouseEvent(Mode mode){
		setOnMouseClicked(mode.GetClickEvent());
		setOnMousePressed(mode.GetPressEvent());
		setOnMouseDragged(mode.GetDragEvent());
		setOnMouseReleased(mode.GetReleaseEvent());
	}

	public void AddBasicObject(Point2D mouse, BasicObject newObject) {
		newObject.SetOrigin(mouse);
		getChildren().addAll(newObject.GetShape());
		newObject.SetDepth(99 - objects.size());
		objects.add(newObject);
	}

	// If point is inside an object, return index of the object
	public int CheckifInside(Point2D point) {
		int object = -1;
		int depth = 100;

		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).GetBound().getBoundsInParent().contains(point) == true) {
				// choose object which has less depth value
				if (objects.get(i).GetDepth() < depth) {
					object = i;
					depth = objects.get(i).GetDepth();
				}
			}
		}
		return object;
	}

	public void SetLineStartPoint(Point2D mouse, LineObject newLine) {
		// Get index of object
		int startObject = CheckifInside(mouse);
		int startPort = objects.get(startObject).ChoosePort(mouse);

		// Store started object and port so if we moved the object, we can still
		// get the location of the started point.
		newLine.SetStartPort(startObject, startPort);
		newLine.SetOrigin(objects.get(startObject).GetPorts()[startPort]);
		newLine.SetDest(mouse);

		// add line shapes to panel
		getChildren().addAll(newLine.GetShape());
	}

	public void SetLineEndPoint(Point2D mouse, LineObject newLine) {
		// Get index of object
		int endObject = CheckifInside(mouse);
		int endPort = objects.get(endObject).ChoosePort(mouse);
		
		newLine.SetEndPort(endObject, endPort);
		newLine.SetDest(objects.get(endObject).GetPorts()[endPort]);
		lines.add(newLine);
	}

	public static Canvas getInstance() {
		return INSTANCE;
	}
}
