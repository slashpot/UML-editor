package shape;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class Port extends Point2D{
	private ArrayList<LineObject> connectedLines = new ArrayList<LineObject>();
	private Rectangle rect = new Rectangle();
	
	public Port(double x, double y) {
		super(x, y);
	}
	
	public void AddConnectedLine(LineObject line){
		connectedLines.add(line);
	}
	
}
