package shape;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public abstract class LineObject extends Object {
	protected Point2D dest = origin;
	protected Line line;
	protected Port startPort;
	protected Port endPort;

	public LineObject() {
		line = new Line();
	}
	
	public void setOrigin(Point2D point) {
		origin = point;
		line.setStartX(origin.getX());
		line.setStartY(origin.getY());
	}

	public Point2D getOrigin() {
		return origin;
	}

	public void setDest(Point2D point) {
		dest = point;
		line.setEndX(dest.getX());
		line.setEndY(dest.getY());
	}

	public Point2D getDest() {
		return dest;
	}

	public void setStartPort(Port port) {
		startPort = port;
	}

	public Port getStartPort() {
		return startPort;
	}

	public void setEndPort(Port port) {
		endPort = port;
	}

	public Port getEndPort() {
		return endPort;
	}

}
