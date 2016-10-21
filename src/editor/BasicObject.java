package editor;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

public abstract class BasicObject extends Object {
	protected int width;
	protected int height;
	protected Shape shape;
	protected Point2D ports[];

	public BasicObject(int depth) {
		super(depth);
	}

	public Point2D[] GetPorts() {
		return ports;
	}

	protected abstract void SetPort();
}
