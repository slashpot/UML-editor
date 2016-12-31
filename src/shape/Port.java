package shape;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class Port{
	private Rectangle rect = new Rectangle();
	private double x;
	private double y;

	public Port(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getDistance(Point2D point){
		double xDistance = x - point.getX();
		double yDistance = y - point.getY();
		return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
	}

	public Rectangle GetRectangle() {
		return rect;
	}

}
