package shape;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class Port{
	private ArrayList<LineObject> connectedLines = new ArrayList<LineObject>();
	private Rectangle rect = new Rectangle();
	private double x;
	private double y;

	public Port(double x, double y) {
		rect.getStyleClass().add("port");
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
	
	public void addLine(LineObject newLine){
		connectedLines.add(newLine);
	}
	
	public void resetLines(){
		for(LineObject line: connectedLines){
			Point2D startPoint = new Point2D(line.getStartPort().getX(), line.getStartPort().getY());
			Point2D endPoint = new Point2D(line.getEndPort().getX(), line.getEndPort().getY());
			line.setOrigin(startPoint);
			line.setDest(endPoint);
		}
	}
	
	public double getDistance(Point2D point){
		double xDistance = x - point.getX();
		double yDistance = y - point.getY();
		return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
	}
	
	public void initializeRectangle() {
		rect.setX(x - 2.5);
		rect.setY(y - 2.5);
		rect.setWidth(5);
		rect.setHeight(5);
		rect.setVisible(false);
	}

	public Rectangle getRectangle() {
		return rect;
	}

}
