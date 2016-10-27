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

	private int objIndex = -1;
	private LineObject newLine;
	private BasicObject newBasic;
	private Rectangle range;
	private Point2D rangeOrigin;

	public CanvasPanel() {
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		setOnMouseClicked(ClickEvent);
		setOnMousePressed(PressEvent);
		setOnMouseDragged(DragEvent);
		setOnMouseReleased(ReleaseEvent);
	}

	public void SetObjIndex(int index) {
		objIndex = index;
		ResetObj();
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

	private void ResetObj() {
		switch (objIndex) {
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

	private int CheckifInside(Point2D point) {
		int object = -1;
		int depth = 100;
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).GetBound().getBoundsInParent().contains(point) == true) {
				// range.getBoundsInParent().contains(objects.get(i).GetBound().getBoundsInParent())
				if (objects.get(i).GetDepth() < depth) {
					object = i;
					depth = objects.get(i).GetDepth();
				}
			}
		}

		if (object != -1) {
			return object;
		} else {
			return -1;
		}
	}

	private void CheckifinGroup(int check) {
		for (int node = nodes.size() - 1; node >= 0; node--) {
			ArrayList<Integer> data = nodes.get(node).GetData();
			for (int i = 0; i < data.size(); i++) {
				if (check == data.get(i)) {
					for (int j = 0; j < data.size(); j++) {
						int index = data.get(j);
						if (objects.get(index).isSelect == false) {
							objects.get(index).SetSelect(true);
							objects.get(index).SetPortsVisible(true);
						}
					}
					break;
				}
			}
		}
	}

	private int ChoosePort(Point2D point, int index) {
		Point2D[] ports = objects.get(index).GetPorts();
		int choose = 0;
		double min = point.distance(ports[0]);

		for (int j = 1; j < 4; j++) {
			if (point.distance(ports[j]) < min) {
				min = point.distance(ports[j]);
				choose = j;
			}
		}
		return choose;
	}

	private void SelectSingle(Point2D pos) {
		int check = CheckifInside(pos);

		if (check != -1) {
			for (int i = 0; i < objects.size(); i++) {
				if (i == check && objects.get(i).GetSelect() == false) {
					objects.get(i).SetSelect(true);
					objects.get(i).SetPortsVisible(true);
				} else if (i != check && objects.get(i).GetSelect() == true) {
					objects.get(i).SetSelect(false);
					objects.get(i).SetPortsVisible(false);
				}
			}
			if (nodes.size() != 0) {
				CheckifinGroup(check);
			}
		} else {
			for (int i = 0; i < objects.size(); i++) {
				if (objects.get(i).GetSelect() == true) {
					objects.get(i).SetSelect(false);
					objects.get(i).SetPortsVisible(false);
				}
			}
		}
	}

	private EventHandler<MouseEvent> ClickEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Point2D pos = new Point2D(event.getX(), event.getY());

			if (newBasic != null) {
				newBasic.SetOrigin(pos);
				getChildren().addAll(newBasic.GetShape());
				newBasic.SetDepth(99 - objects.size());
				objects.add(newBasic);
				ResetObj();
			}
		}
	};

	private EventHandler<MouseEvent> PressEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Point2D pos = new Point2D(event.getX(), event.getY());
			if (newLine != null) {
				// check if in an object
				int startObject = CheckifInside(pos);
				if (startObject != -1) {
					int startPort = ChoosePort(pos, startObject);
					newLine.SetStartPort(startObject, startPort);
					newLine.SetOrigin(objects.get(startObject).GetPorts()[startPort]);
					newLine.SetDest(pos);
					getChildren().addAll(newLine.GetShape());
				}
			}

			if (objIndex == 0) {
				// check if select a single basic object
				SelectSingle(pos);

				// doesn't select a single basic object
				if (CheckifInside(pos) == -1) {
					range = new Rectangle(0, 0);
					range.setFill(Color.TRANSPARENT);
					range.setStroke(Color.GRAY);
					range.setX(pos.getX());
					range.setY(pos.getY());
					rangeOrigin = pos;
					getChildren().add(range);
				}
				// does select object and set translate
				else {
					for (BasicObject object : objects) {
						if (object.GetSelect() == true) {
							object.SetOldTranslate(pos);
						}
					}
				}
			}
		}
	};

	private EventHandler<MouseEvent> DragEvent = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			Point2D pos = new Point2D(event.getX(), event.getY());

			// restrict mouse position
			if (pos.getX() < 0 || pos.getY() < 0) {
				return;
			}

			// drag line destination
			if (newLine != null && newLine.GetOrigin() != null) {
				newLine.SetDest(pos);
			}

			// draw select rectangle
			if (objIndex == 0 && range != null) {
				double width = pos.getX() - rangeOrigin.getX();
				double height = pos.getY() - rangeOrigin.getY();

				if (width > 0) {
					range.setWidth(width);
				} else {
					range.setWidth((-1) * width);
					range.setX(pos.getX());
				}

				if (height > 0) {
					range.setHeight(height);
				} else {
					range.setHeight((-1) * height);
					range.setY(pos.getY());
				}
			}

			if (objIndex == 0) {
				// move selected objects
				for (BasicObject object : objects) {
					if (object.GetSelect() == true) {
						object.Move(pos);
					}
				}

				// redraw lines
				for (LineObject line : lines) {
					line.SetOrigin(objects.get(line.GetStartObject()).GetPorts()[line.GetStartPort()]);
					line.SetDest(objects.get(line.GetEndObject()).GetPorts()[line.GetEndPort()]);
				}
			}
		}
	};

	private EventHandler<MouseEvent> ReleaseEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Point2D pos = new Point2D(event.getX(), event.getY());
			if (newLine != null && newLine.GetOrigin() != null) {
				int endObject = CheckifInside(pos);
				if (endObject != -1 && endObject != newLine.GetStartObject()) {
					int endPort = ChoosePort(pos, endObject);
					newLine.SetEndPort(endObject, endPort);
					newLine.SetDest(objects.get(endObject).GetPorts()[endPort]);
					lines.add(newLine);
				} else {
					getChildren().removeAll(newLine.GetShape());
				}

				// reset line object
				ResetObj();
			}
			if (objIndex == 0 && range != null) {
				for (int i = 0; i < objects.size(); i++) {
					if (range.getBoundsInParent().contains(objects.get(i).GetBound().getBoundsInParent())) {
						if (objects.get(i).GetSelect() == false) {
							objects.get(i).SetSelect(true);
							objects.get(i).SetPortsVisible(true);
						}
					}
				}
				getChildren().remove(range);
				range = null;
			}
		}
	};
}
