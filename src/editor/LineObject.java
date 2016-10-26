package editor;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public abstract class LineObject extends Object {
	protected Point2D dest = origin;
	protected Line line;
	private int portIndex;

	public LineObject() {
		line = new Line();
	}
	
	public void SetOrigin(Point2D point) {
		origin = point;
		line.setStartX(origin.getX());
		line.setStartY(origin.getY());
	}
	
	public void SetDest(Point2D point , int port) {
		portIndex = port;
		dest = point;
		line.setEndX(dest.getX());
		line.setEndY(dest.getY());
	}

	public Point2D GetDest() {
		return dest;
	}

}
