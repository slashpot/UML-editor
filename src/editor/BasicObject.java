package editor;

import javafx.geometry.Point2D;

public class BasicObject extends Object{
	Point2D topLeft, bottomRight;
	Point2D ports[];
	
	public BasicObject(int depth) {
		super(depth);
	}
}
