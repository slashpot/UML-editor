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

	public void SetOrigin(Point2D point) {
		origin = point;
		line.setStartX(origin.getX());
		line.setStartY(origin.getY());
	}

	public Point2D GetOrigin() {
		return origin;
	}

	public void SetDest(Point2D point) {
		dest = point;
		line.setEndX(dest.getX());
		line.setEndY(dest.getY());
	}

	public Point2D GetDest() {
		return dest;
	}

	public void SetStartPort(Port port) {
		startPort = port;
	}

	public Port GetStartPort() {
		return startPort;
	}

	public void SetEndPort(Port port) {
		endPort = port;
	}

	public Port GetEndPort() {
		return endPort;
	}

}
