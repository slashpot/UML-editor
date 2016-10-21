package editor;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

public abstract class Object {
	private int depth;
	protected Point2D origin;

	public Object(int depth) {
		this.depth = depth;
	}

	public int GetDepth() {
		return depth;
	}

	public void SetOrigin(Point2D point) {
		origin = point;
	}

	public abstract Shape GetShape();
}
