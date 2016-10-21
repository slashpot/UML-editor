package editor;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public class AssociateLine extends LineObject {
	private Line line;

	public AssociateLine(int depth) {
		super(depth);
		line = new Line();
	}
	
	public void SetOrigin(Point2D point) {
		origin = point;
		line.setStartX(origin.getX());
		line.setStartY(origin.getY());
	}
	
	public void SetDest(Point2D point) {
		dest = point;
		line.setEndX(dest.getX());
		line.setEndY(dest.getY());
	}

	@Override
	public Line GetShape() {
		return line;
	}

}
