package editor;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

public abstract class BasicObject extends Object {
	protected Point2D ports[];

	public BasicObject(int depth) {
		super(depth);
	}

	public abstract Shape GetShape();

	public abstract void SetPort();

	public abstract void DrawPort();
}
