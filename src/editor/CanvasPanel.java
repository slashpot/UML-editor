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

public class CanvasPanel extends Pane {
	private ArrayList<Object> objects = new ArrayList<Object>();
	private Object newObject;
	private boolean hasObject = false;

	private EventHandler<MouseEvent> Event = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (hasObject == true) {
				Point2D mousePos = new Point2D(event.getX(), event.getY());
				newObject.SetOrigin(mousePos);
				CanvasPanel.this.getChildren().add(newObject.GetShape());
				objects.add(newObject);
			}
		}
	};

	public CanvasPanel() {
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		setOnMouseClicked(Event);
	}

	private void FindOnBasic(Point2D pos) {
		for (int i = 0; i < objects.size(); i++) {

		}
	}

	public void SetObject(Object object) {
		newObject = object;
		hasObject = true;
	}

}
