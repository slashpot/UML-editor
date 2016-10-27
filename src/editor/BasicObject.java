package editor;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public abstract class BasicObject extends Object {
	protected int width;
	protected int height;
	protected Shape shape;
	protected Point2D ports[] = new Point2D[4];
	protected Point2D oldPorts[];
	protected Text name;
	protected Rectangle rectports[] = new Rectangle[4];
	protected boolean isSelect = false;

	// used for move object
	private double oldPosX;
	private double oldPosY;
	private double oldTranslateX;
	private double oldTranslateY;

	public BasicObject() {
		for (int i = 0; i < 4; i++) {
			rectports[i] = new Rectangle();
		}
	}

	public Point2D[] GetPorts() {
		return ports;
	}

	public void SetPortsVisible(boolean set) {
		for (int i = 0; i < 4; i++) {
			rectports[i].setVisible(set);
		}
	}

	public void SetSelect(boolean bool) {
		isSelect = bool;
	}

	public boolean GetSelect() {
		return isSelect;
	}

	public void SetOldTranslate(Point2D pos) {
		oldPosX = pos.getX();
		oldPosY = pos.getY();
		oldTranslateX = GetBound().getTranslateX();
		oldTranslateY = GetBound().getTranslateY();
		
		oldPorts = ports.clone();
	}

	// move while dragging
	public void Move(Point2D pos) {
		double offsetX = pos.getX() - oldPosX;
		double offsetY = pos.getY() - oldPosY;
		double newTranslateX = oldTranslateX + offsetX;
		double newTranslateY = oldTranslateY + offsetY;

		for (int i = 0; i < shapes.length; i++) {
			shapes[i].setTranslateX(newTranslateX);
			shapes[i].setTranslateY(newTranslateY);
		}
		for (int i = 0; i < 4; i++) {
			ports[i] = new Point2D(oldPorts[i].getX()+offsetX,oldPorts[i].getY()+offsetY);
		}

	}
	
	public void SetNewPorts(Point2D pos){
		double offsetX = pos.getX() - oldPosX;
		double offsetY = pos.getY() - oldPosY;
		for (int i = 0; i < 4; i++) {
			ports[i] = new Point2D(ports[i].getX()+offsetX,ports[i].getY()+offsetY);
		}
	}

	public Shape GetBound() {
		return shapes[0];
	}

	protected abstract void SetPort();
}
