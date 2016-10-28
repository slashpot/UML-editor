package editor;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class CompositionLine extends LineObject {
	private Polygon diamond = new Polygon();
	private double length = 5;

	public CompositionLine() {
		shapes = new Shape[2];
	}

	@Override
	public void SetDest(Point2D point) {
		dest = point;
		line.setEndX(dest.getX());
		line.setEndY(dest.getY());
		DrawDiamond();
	}

	@Override
	public Shape[] GetShape() {
		shapes[0] = line;
		shapes[1] = diamond;
		return shapes;
	}

	private void DrawDiamond() {
		double[] xpoints = { dest.getX(), dest.getX() + length, dest.getX(), dest.getX() - length };
		double[] ypoints = { dest.getY() + length, dest.getY(), dest.getY() - length, dest.getY() };
		diamond.getPoints().clear();
		diamond.getPoints().addAll(xpoints[0], ypoints[0], xpoints[1], ypoints[1], xpoints[2], ypoints[2],xpoints[3],ypoints[3]);
	}

}
