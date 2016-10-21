package editor;

import javafx.geometry.Point2D;

public abstract class LineObject extends Object {
	protected Point2D dest = origin;

	public LineObject(int depth) {
		super(depth);
	}

	public void SetDest(Point2D point) {
		dest = point;
	}

	public Point2D GetDest() {
		return dest;
	}

}
