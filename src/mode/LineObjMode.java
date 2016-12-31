package mode;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import shape.LineObject;

public abstract class LineObjMode extends Mode{
	protected LineObject newLine;

	@Override
	protected void setEvent() {
		clickEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			}
		};

		pressEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());
				newObj();

				if (canvas.GetInsideObject(mouse) != null)
					canvas.SetLineStartPoint(mouse, newLine);
			}
		};

		dragEvent = new EventHandler<MouseEvent>() {
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

		releaseEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());

				if (canvas.GetInsideObject(mouse) != null)
					canvas.SetLineEndPoint(mouse, newLine);
				else
					canvas.getChildren().removeAll(newLine.GetShape());
			}
		};
	}
	
	protected abstract void newObj();
}
