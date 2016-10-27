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
	private ArrayList<CompositeNode> nodes = new ArrayList<CompositeNode>();
	private LineObject newLine;
	private BasicObject newBasic;
	private Point2D lineOrigin;
	private Point2D lineDest;
	private Rectangle range;
	private Point2D rangeOrigin;
	private int objIndex = -1;
	private int originIndex = -1;
	private int destIndex = -1;
	private int portIndex = -1;

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
			if (objects.get(i).GetBound().contains(point) == true) {
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
							objects.get(index).DrawRects();
							getChildren().addAll(objects.get(index).GetRects());
						}
					}
					break;
				}
			}
		}
	}

	private Point2D ChoosePort(Point2D point, int index) {
		Point2D[] ports = objects.get(index).GetPorts();
		Point2D choose = ports[0];
		double min = point.distance(ports[0]);
		portIndex = 0;

		for (int j = 1; j < 4; j++) {
			if (point.distance(ports[j]) < min) {
				min = point.distance(ports[j]);
				choose = ports[j];
				portIndex = j;
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
					objects.get(i).DrawRects();
					getChildren().addAll(objects.get(i).GetRects());
				} else if (i != check && objects.get(i).GetSelect() == true) {
					objects.get(i).SetSelect(false);
					getChildren().removeAll(objects.get(i).GetRects());
				}
			}
			if (nodes.size() != 0) {
				CheckifinGroup(check);
			}
		} else {
			for (int i = 0; i < objects.size(); i++) {
				if (objects.get(i).GetSelect() == true) {
					objects.get(i).SetSelect(false);
					getChildren().removeAll(objects.get(i).GetRects());
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
				CanvasPanel.this.getChildren().addAll(newBasic.GetShape());
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
				originIndex = CheckifInside(pos);
				if (originIndex != -1) {
					lineOrigin = ChoosePort(pos, originIndex);
					newLine.SetOrigin(lineOrigin);
					newLine.SetDest(pos, portIndex);
					getChildren().addAll(newLine.GetShape());
				} else {
					lineOrigin = null;
				}
			}

			if (objIndex == 0) {
				SelectSingle(pos);
				if (CheckifInside(pos) == -1) {
					range = new Rectangle(0, 0);
					range.setFill(Color.TRANSPARENT);
					range.setStroke(Color.GRAY);
					range.setX(pos.getX());
					range.setY(pos.getY());
					rangeOrigin = pos;
					getChildren().add(range);
				}
			}
		}
	};

	private EventHandler<MouseEvent> DragEvent = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			Point2D pos = new Point2D(event.getX(), event.getY());

			if (newLine != null && lineOrigin != null) {
				newLine.SetDest(pos, portIndex);
			}

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
		}
	};

	private EventHandler<MouseEvent> ReleaseEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (newLine != null && lineOrigin != null) {
				Point2D pos = new Point2D(event.getX(), event.getY());
				destIndex = CheckifInside(pos);
				if (destIndex != -1 && destIndex != originIndex) {
					lineDest = ChoosePort(pos, destIndex);
					newLine.SetDest(lineDest, portIndex);
				} else {
					getChildren().removeAll(newLine.GetShape());
				}
			}
			if (objIndex == 0 && range != null) {
				for (int i = 0; i < objects.size(); i++) {
					if (range.getBoundsInParent().contains(objects.get(i).GetBound().getBoundsInParent())) {
						if (objects.get(i).GetSelect() == false) {
							objects.get(i).SetSelect(true);
							objects.get(i).DrawRects();
							getChildren().addAll(objects.get(i).GetRects());
						}
					}
				}
				getChildren().remove(range);
				range = null;
			}
			ResetObj();
		}
	};
}
