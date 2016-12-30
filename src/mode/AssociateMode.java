package mode;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import shape.AssociateLine;
import shape.LineObject;
import ui.Canvas;

public class AssociateMode extends Mode {
	protected LineObject newLine;

	@Override
	public void SetEvent() {
		ClickEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			}
		};

		PressEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());
				newLine = new AssociateLine();

				if (Canvas.getInstance().GetInsideObject(mouse) != -1)
					Canvas.getInstance().SetLineStartPoint(mouse, newLine);
			}
		};

		DragEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());

				// restrict mouse position
				if (mouse.getX() < 0 || mouse.getY() < 0)
					return;

				if (newLine.GetOrigin() != null)
					newLine.SetDest(mouse);
			}
		};

		ReleaseEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());

				if (Canvas.getInstance().GetInsideObject(mouse) != -1)
					Canvas.getInstance().SetLineEndPoint(mouse, newLine);
				else
					Canvas.getInstance().getChildren().removeAll(newLine.GetShape());
			}
		};
	}
}
