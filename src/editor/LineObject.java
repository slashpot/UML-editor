package editor;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public abstract class LineObject extends Object {
	protected Point2D dest = origin;
	protected Line line;
	protected int startObject;
	protected int startPort;
	protected int endObject;
	protected int endPort;

	public LineObject() {
		line = new Line();
	}
	
	public void SetOrigin(Point2D point) {
		origin = point;
		line.setStartX(origin.getX());
		line.setStartY(origin.getY());
	}
	
	public Point2D GetOrigin(){
		return origin;
	}
	
	public void SetDest(Point2D point) {
		dest = point;
		line.setEndX(dest.getX());
		line.setEndY(dest.getY());
	}

	public Point2D GetDest() {
		return dest;
	}
	
	public void SetStartPort(int object, int port){
		startObject = object;
		startPort = port;
	}
	
	public int GetStartObject(){
		return startObject;
	}
	
	public int GetStartPort(){
		return startPort;
	}
	
	public void SetEndPort(int object, int port){
		endObject = object;
		endPort = port;
	}
	
	public int GetEndObject(){
		return endObject;
	}
	
	public int GetEndPort(){
		return endPort;
	}
}
