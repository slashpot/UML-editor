package shape;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class GeneralizationLine extends LineObject {
	private Polygon triangle = new Polygon();
	private double length = 5;

	public GeneralizationLine() {
		shapes = new Shape[2];
	}

	public void setDest(Point2D point) {
		dest = point;
		line.setEndX(dest.getX());
		line.setEndY(dest.getY());
		DrawArrow(origin.getX(),origin.getY(),dest.getX(),dest.getY(),length);
	}

	@Override
	public Shape[] getShape() {
		shapes[0] = line;
		shapes[1] = triangle;
		triangle.getStyleClass().add("line-head");
		return shapes;
	}

	private void DrawArrow(double x1, double y1, double x2, double y2, double length) {
		double dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - length, xn = xm, ym = length, yn = -length, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		double[] xpoints = { x2, xm, xn };
		double[] ypoints = { y2, ym, yn };

		triangle.getPoints().clear();
		triangle.getPoints().addAll(xpoints[0], ypoints[0], xpoints[1], ypoints[1], xpoints[2], ypoints[2]);
	}

}
