package shape;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import ui.RenameWindow;

public abstract class BasicObject extends Object {
	protected int width;
	protected int height;
	protected Port ports[] = new Port[4];
	protected Port oldPorts[] = new Port[4];
	protected Text name;
	protected boolean isSelect = false;
	protected boolean isGroup = false;

	// used for move object
	protected double oldPosX;
	protected double oldPosY;
	protected double oldTranslateX;
	protected double oldTranslateY;
	
	private RenameWindow renamePane;

	public Port[] getPorts() {
		return ports;
	}

	public void setPortsVisible(boolean set) {
		for (int i = 0; i < 4; i++) {
			ports[i].getRectangle().setVisible(set);
		}
	}

	public Port choosePort(Point2D point) {
		int choose = 0;
		double min = ports[0].getDistance(point);

		for (int j = 1; j < 4; j++) {
			if (ports[j].getDistance(point) < min) {
				min = ports[j].getDistance(point);
				choose = j;
			}
		}
		return ports[choose];
	}

	public void setSelect(boolean bool) {
		isSelect = bool;
	}

	public boolean checkIfSelected() {
		return isSelect;
	}

	public boolean checkIfIsGroup() {
		return isGroup;
	}

	// store old translate before move objects
	public void setOldTranslate(Point2D pos) {
		oldPosX = pos.getX();
		oldPosY = pos.getY();
		oldTranslateX = getBound().getTranslateX();
		oldTranslateY = getBound().getTranslateY();

		for (int i = 0; i < ports.length; i++) {
			oldPorts[i] = new Port(ports[i].getX(), ports[i].getY());
		}
	}

	// move while dragging
	public void move(Point2D pos) {
		double offsetX = pos.getX() - oldPosX;
		double offsetY = pos.getY() - oldPosY;
		double newTranslateX = oldTranslateX + offsetX;
		double newTranslateY = oldTranslateY + offsetY;

		for (int i = 0; i < shapes.length; i++) {
			shapes[i].setTranslateX(newTranslateX);
			shapes[i].setTranslateY(newTranslateY);
		}

		for (int i = 0; i < ports.length; i++) {
			ports[i].setX(oldPorts[i].getX() + offsetX);
			ports[i].setY(oldPorts[i].getY() + offsetY);
			ports[i].resetLines();
		}
	}

	public void setName() {
		renamePane = new RenameWindow(name);
		renamePane.show();
	}


	public abstract Shape getBound();

	public abstract Shape getSelectedObjBound(Point2D mouse);

	public abstract void unGroup();
}
