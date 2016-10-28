package editor;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasPanel extends Pane {
	private ArrayList<BasicObject> objects = new ArrayList<BasicObject>();
	private ArrayList<LineObject> lines = new ArrayList<LineObject>();
	private ArrayList<CompositeNode> nodes = new ArrayList<CompositeNode>();

	// used for choose function
	private int functionIndex = -1;
	// used for store new LineObject
	private LineObject newLine;
	// used for store new BasicObject
	private BasicObject newBasic;
	// multiple select's rectangle range
	private Rectangle range;
	private Point2D rangeOrigin;

	public CanvasPanel() {
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		setOnMouseClicked(ClickEvent);
		setOnMousePressed(PressEvent);
		setOnMouseDragged(DragEvent);
		setOnMouseReleased(ReleaseEvent);
	}

	public void SetFunctionIndex(int index) {
		functionIndex = index;
		InitializeObject();
	}

	public ArrayList<BasicObject> GetObjects() {
		return objects;
	}

	public void AddNode(CompositeNode node) {
		nodes.add(node);
	}

	public void RemoveNode(int index) {
		nodes.remove(index);
	}

	public ArrayList<CompositeNode> GetNodes() {
		return nodes;
	}

	private void InitializeObject() {
		switch (functionIndex) {
		case 0:
			newBasic = null;
			newLine = null;
			break;

		case 1:
			newLine = new AssociateLine();
			newBasic = null;
			break;

		case 2:
			newLine = new GenLine();
			newBasic = null;
			break;

		case 3:
			newLine = new CompositionLine();
			newBasic = null;
			break;

		case 4:
			newBasic = new Class();
			newLine = null;
			break;

		case 5:
			newBasic = new UseCase();
			newLine = null;
			break;
		}
	}

	// check if point is inside any object
	private int CheckifInside(Point2D point) {
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

		if (object != -1)
			return object;
		else
			return -1;
	}

	// check if object is in any composite group
	private void CheckifinGroup(int object) {
		boolean isSelected = false;
		for (int node = nodes.size() - 1; node >= 0; node--) {
			// store data inside node
			ArrayList<Integer> data = nodes.get(node).GetData();

			// check if object is in node's data
			for (int i = 0; i < data.size(); i++) {
				if (object == data.get(i)) {
					// select every objects in the same data
					for (int j = 0; j < data.size(); j++) {
						int index = data.get(j);
						if (objects.get(index).isSelect == false) {
							objects.get(index).SetSelect(true);
							objects.get(index).SetPortsVisible(true);
						}
					}
					// doesn't need to check anymore
					isSelected = true;
					break;
				}
			}
			if (isSelected == true)
				break;
		}
	}

	// choose the closest port to connect line
	private int ChoosePort(Point2D point, int index) {
		Point2D[] ports = objects.get(index).GetPorts();
		int choose = 0;
		double min = point.distance(ports[0]);

		// choose the port which has minimum distance
		for (int j = 1; j < 4; j++) {
			if (point.distance(ports[j]) < min) {
				min = point.distance(ports[j]);
				choose = j;
			}
		}
		return choose;
	}

	// select objects with single click
	private void SelectSingle(Point2D point) {
		// check if point is inside any object
		int object = CheckifInside(point);

		// if point is inside an object
		if (object != -1) {
			// select this object and unselect other selected objects
			for (int i = 0; i < objects.size(); i++) {
				if (i == object && objects.get(i).GetSelect() == false) {
					objects.get(i).SetSelect(true);
					objects.get(i).SetPortsVisible(true);
				} else if (i != object && objects.get(i).GetSelect() == true) {
					objects.get(i).SetSelect(false);
					objects.get(i).SetPortsVisible(false);
				}
			}

			// if object is in a group, select other objects in the same group
			if (nodes.size() != 0) {
				CheckifinGroup(object);
			}
		}
		// if point isn't in any object
		else {
			// unselect selected objects
			for (int i = 0; i < objects.size(); i++) {
				if (objects.get(i).GetSelect() == true) {
					objects.get(i).SetSelect(false);
					objects.get(i).SetPortsVisible(false);
				}
			}
		}
	}

	// select objects with multiple select
	private void SelectMultiple() {
		// if mutiple selection rectangle is created
		if (functionIndex == 0 && range != null) {
			// select every objects in rectangle
			for (int i = 0; i < objects.size(); i++) {
				if (range.getBoundsInParent().contains(objects.get(i).GetBound().getBoundsInParent())) {
					if (objects.get(i).GetSelect() == false) {
						objects.get(i).SetSelect(true);
						objects.get(i).SetPortsVisible(true);
					}
				}
			}
			// remove rectangle
			getChildren().remove(range);
			range = null;
		}
	}

	private void AddBasicObject(Point2D mouse) {
		if (newBasic != null) {
			newBasic.SetOrigin(mouse);
			getChildren().addAll(newBasic.GetShape());
			newBasic.SetDepth(99 - objects.size());
			objects.add(newBasic);
			InitializeObject();
		}
	}

	private void SetLineStartPoint(Point2D mouse) {
		if (newLine != null) {
			// check if in an object
			int startObject = CheckifInside(mouse);
			// if in an object
			if (startObject != -1) {
				// choose start port
				int startPort = ChoosePort(mouse, startObject);
				// set start object and port
				newLine.SetStartPort(startObject, startPort);
				// set line's start point
				newLine.SetOrigin(objects.get(startObject).GetPorts()[startPort]);
				// temporary set line's destination to mouse
				newLine.SetDest(mouse);
				// add line shapes to panel
				getChildren().addAll(newLine.GetShape());
			}
		}
	}

	private void SetLineEndPoint(Point2D mouse) {
		// if newLine has start point
		if (newLine != null && newLine.GetOrigin() != null) {
			// check if mouse is in an object
			int endObject = CheckifInside(mouse);
			// if it's in an object, set start point
			if (endObject != -1 && endObject != newLine.GetStartObject()) {
				int endPort = ChoosePort(mouse, endObject);
				newLine.SetEndPort(endObject, endPort);
				newLine.SetDest(objects.get(endObject).GetPorts()[endPort]);
				lines.add(newLine);
			}
			// else remove current line
			else {
				getChildren().removeAll(newLine.GetShape());
			}

			// reset line object
			InitializeObject();
		}
	}

	private void SelectWithPress(Point2D mouse) {
		// if select button is enabled
		if (functionIndex == 0) {
			// check if select a single basic object
			SelectSingle(mouse);

			// doesn't select a single basic object
			if (CheckifInside(mouse) == -1) {
				// create mutiple select rectangle
				range = new Rectangle(0, 0);
				range.setFill(Color.TRANSPARENT);
				range.setStroke(Color.GRAY);
				range.setX(mouse.getX());
				range.setY(mouse.getY());
				rangeOrigin = mouse;
				getChildren().add(range);
			}
			// does select object and set translate
			else {
				for (BasicObject object : objects) {
					if (object.GetSelect() == true) {
						object.SetOldTranslate(mouse);
					}
				}
			}
		}
	}

	private void DrawSelectionRange(Point2D mouse) {
		// draw select rectangle
		if (functionIndex == 0 && range != null) {
			double width = mouse.getX() - rangeOrigin.getX();
			double height = mouse.getY() - rangeOrigin.getY();

			if (width > 0) {
				range.setWidth(width);
			} else {
				range.setWidth((-1) * width);
				range.setX(mouse.getX());
			}

			if (height > 0) {
				range.setHeight(height);
			} else {
				range.setHeight((-1) * height);
				range.setY(mouse.getY());
			}
		}
	}

	private void MoveObjects(Point2D mouse) {
		if (functionIndex == 0) {
			// move selected objects
			for (BasicObject object : objects) {
				if (object.GetSelect() == true) {
					object.Move(mouse);
				}
			}

			// redraw lines
			for (LineObject line : lines) {
				line.SetOrigin(objects.get(line.GetStartObject()).GetPorts()[line.GetStartPort()]);
				line.SetDest(objects.get(line.GetEndObject()).GetPorts()[line.GetEndPort()]);
			}
		}
	}

	private EventHandler<MouseEvent> ClickEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Point2D mouse = new Point2D(event.getX(), event.getY());
			AddBasicObject(mouse);
		}
	};

	private EventHandler<MouseEvent> PressEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Point2D mouse = new Point2D(event.getX(), event.getY());
			SetLineStartPoint(mouse);
			SelectWithPress(mouse);
		}
	};

	private EventHandler<MouseEvent> DragEvent = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			Point2D mouse = new Point2D(event.getX(), event.getY());

			// restrict mouse position
			if (mouse.getX() < 0 || mouse.getY() < 0) {
				return;
			}

			// drag line destination
			if (newLine != null && newLine.GetOrigin() != null) {
				newLine.SetDest(mouse);
			}

			DrawSelectionRange(mouse);
			MoveObjects(mouse);
		}
	};

	private EventHandler<MouseEvent> ReleaseEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Point2D mouse = new Point2D(event.getX(), event.getY());
			SetLineEndPoint(mouse);
			SelectMultiple();
		}
	};
}
