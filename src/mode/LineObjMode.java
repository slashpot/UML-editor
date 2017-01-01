package mode;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import shape.BasicObject;
import shape.LineObject;
import shape.Port;

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

				if (getInsideObject(mouse) != null)
					setLineStartPoint(mouse, newLine);
			}
		};

		dragEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());

				// restrict mouse position
				if (mouse.getX() < 0 || mouse.getY() < 0)
					return;

				if (newLine.getOrigin() != null)
					newLine.setDest(mouse);
			}
		};

		releaseEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());

				if (getInsideObject(mouse) != null){
					setLineEndPoint(mouse, newLine);
					newLine.getStartPort().addLine(newLine);
					newLine.getEndPort().addLine(newLine);
				}
				else
					canvas.getChildren().removeAll(newLine.getShape());
			}
		};
	}
	
	private void setLineStartPoint(Point2D mouse, LineObject newLine) {
		BasicObject startObject = getInsideObject(mouse);
		Port startPort = startObject.choosePort(mouse);

		Point2D startPoint = new Point2D(startPort.getX(), startPort.getY());
		newLine.setStartPort(startPort);
		newLine.setOrigin(startPoint);
		newLine.setDest(mouse);

		canvas.getChildren().addAll(newLine.getShape());
	}
	
	private void setLineEndPoint(Point2D mouse, LineObject newLine) {
		BasicObject endObject = getInsideObject(mouse);
		Port endPort = endObject.choosePort(mouse);

		newLine.setEndPort(endPort);
		Point2D endPoint = new Point2D(endPort.getX(), endPort.getY());
		newLine.setDest(endPoint);
		canvas.getLineObjs().add(newLine);
	}
	
	protected abstract void newObj();
}
