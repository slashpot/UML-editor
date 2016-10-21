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
	private int objIndex = -1;
	private int originIndex = -1;
	private int destIndex = -1;

	public CanvasPanel() {
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		setOnMouseClicked(DrawBasic);
		setOnMousePressed(GetLineOrigin);
		setOnMouseDragged(GetDragDest);
		setOnMouseReleased(GetLineDest);
	}

	public void SetObjIndex(int index) {
		objIndex = index;
		ResetObj();
	}

	private void ResetObj() {
		switch (objIndex) {
		case 1:
			newLine = new AssociateLine(0);
			newBasic = null;
			break;

		case 4:
			newBasic = new Class(0);
			newLine = null;
			break;

		case 5:
			newBasic = new UseCase(0);
			newLine = null;
			break;
		}
	}

	private void DrawPorts(Point2D[] ports) {
		Rectangle rectports[] = new Rectangle[4];
		for (int i = 0; i < 4; i++) {
			rectports[i] = new Rectangle(ports[i].getX() - 2.5, ports[i].getY() - 2.5, 5, 5);
		}
		this.getChildren().addAll(rectports[0], rectports[1], rectports[2], rectports[3]);
	}

	private int CheckPort(Point2D point) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).GetShape().contains(point) == true)
				return i;
		}
		return -1;
	}

	private Point2D ChoosePort(Point2D point, int index) {
		Point2D[] ports = objects.get(index).GetPorts();
		Point2D choose = ports[0];
		double min = point.distance(ports[0]);

		for (int j = 1; j < 4; j++) {
			if (point.distance(ports[j]) < min) {
				min = point.distance(ports[j]);
				choose = ports[j];
			}
		}
		return choose;
	}

	private EventHandler<MouseEvent> DrawBasic = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (newBasic != null) {
				Point2D pos = new Point2D(event.getX(), event.getY());
				newBasic.SetOrigin(pos);
				CanvasPanel.this.getChildren().add(newBasic.GetShape());
				objects.add(newBasic);
				DrawPorts(newBasic.GetPorts());
				ResetObj();
			}
		}
	};

	private EventHandler<MouseEvent> GetLineOrigin = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (newLine != null) {
				Point2D pos = new Point2D(event.getX(), event.getY());
				originIndex = CheckPort(pos);
				if (originIndex != -1) {
					lineOrigin = ChoosePort(pos, originIndex);
					newLine.SetOrigin(lineOrigin);
					newLine.SetDest(lineOrigin);
					CanvasPanel.this.getChildren().add(newLine.GetShape());
				} else {
					lineOrigin = null;
				}
			}
		}
	};

	private EventHandler<MouseEvent> GetDragDest = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (newLine != null && lineOrigin != null) {
				Point2D pos = new Point2D(event.getX(), event.getY());
				newLine.SetDest(pos);
			}
		}
	};

	private EventHandler<MouseEvent> GetLineDest = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (newLine != null && lineOrigin != null) {
				Point2D pos = new Point2D(event.getX(), event.getY());
				destIndex = CheckPort(pos);
				if (destIndex != -1 && destIndex != originIndex) {
					lineDest = ChoosePort(pos, destIndex);
					newLine.SetDest(lineDest);
				} else {
					CanvasPanel.this.getChildren().remove(newLine.GetShape());
				}
			}
			ResetObj();
		}
	};

}
