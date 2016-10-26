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
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).GetBound().contains(point) == true)
				return i;
		}
		return -1;
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
					if (objects.get(i).GetSelect() == true) {
						objects.get(i).SetSelect(false);
						getChildren().removeAll(objects.get(i).GetRects());
					}
				}
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
				range = new Rectangle(0, 0);
				range.setFill(Color.TRANSPARENT);
				range.setStroke(Color.GRAY);
				range.setX(pos.getX());
				range.setY(pos.getY());
				rangeOrigin = pos;
				getChildren().add(range);
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

			if (objIndex == 0) {
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
			if (objIndex == 0) {
				for (int i = 0; i < objects.size(); i++) {
					if (range.getBoundsInParent().intersects(objects.get(i).GetBound().getBoundsInParent())) {
						
					}
				}
				getChildren().remove(range);
			}
			ResetObj();
		}
	};
}
