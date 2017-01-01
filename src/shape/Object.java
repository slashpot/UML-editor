package shape;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public abstract class Object {
	private int depth;
	protected Point2D origin;
	protected Shape[] shapes;

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setOrigin(Point2D point) {
		origin = point;
	}

	public Point2D getOrigin() {
		return origin;
	}

	public abstract Shape[] getShape();
}
