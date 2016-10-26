package editor;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public abstract class BasicObject extends Object {
	protected int width;
	protected int height;
	protected Shape shape;
	protected Point2D ports[];
	protected Text name;
	protected Rectangle rectports[] = new Rectangle[4];
	protected boolean isSelect = false;

	public BasicObject() {
		for (int i = 0; i < 4; i++) {
			rectports[i] = new Rectangle();
		}
	}

	public Point2D[] GetPorts() {
		return ports;
	}

	public void DrawRects() {
		for (int i = 0; i < 4; i++) {
			rectports[i].setX(ports[i].getX()-2.5);
			rectports[i].setY(ports[i].getY()-2.5);
			rectports[i].setWidth(5);
			rectports[i].setHeight(5);
		}
	}
	
	public Rectangle[] GetRects(){
		return rectports;
	}

	public void SetSelect(boolean bool) {
		isSelect = bool;
	}

	public boolean GetSelect() {
		return isSelect;
	}

	public abstract Shape GetBound();

	protected abstract void SetPort();
}
