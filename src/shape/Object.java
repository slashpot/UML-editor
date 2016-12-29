package shape;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public abstract class Object {
	private int depth;
	protected Point2D origin;
	protected Shape[] shapes;

	public Object() {

	}

	public int GetDepth() {
		return depth;
	}

	public void SetDepth(int depth) {
		this.depth = depth;
	}

	public void SetOrigin(Point2D point) {
		origin = point;
	}

	public Point2D GetOrigin() {
		return origin;
	}

	public abstract Shape[] GetShape();
}
