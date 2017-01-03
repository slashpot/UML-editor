package shape;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ui.Canvas;

public class Group extends BasicObject {
	private ArrayList<BasicObject> members = new ArrayList<BasicObject>();
	private Rectangle bound = new Rectangle();
	private double padding = 15.0;

	public Group() {
		isGroup = true;
		bound.getStyleClass().add("group-bound");
		Canvas.getInstance().getChildren().add(bound);
	}
	
	public void setPortsVisible(boolean set) {
		for(BasicObject obj: members){
			obj.setPortsVisible(set);
		}
	}
	
	// store old translate before move objects
	public void setOldTranslate(Point2D pos) {
		oldPosX = pos.getX();
		oldPosY = pos.getY();
		oldTranslateX = getBound().getTranslateX();
		oldTranslateY = getBound().getTranslateY();
		
		for(BasicObject obj: members){
			obj.setOldTranslate(pos);
		}
	}
	
	// move while dragging
	public void move(Point2D pos) {
		double offsetX = pos.getX() - oldPosX;
		double offsetY = pos.getY() - oldPosY;
		double newTranslateX = oldTranslateX + offsetX;
		double newTranslateY = oldTranslateY + offsetY;
		
		bound.setTranslateX(newTranslateX);
		bound.setTranslateY(newTranslateY);

		for(BasicObject obj: members){
			obj.move(pos);
		}
	}

	private void calculateBound() {
		Bounds first = members.get(0).getBound().getBoundsInParent();
		double minX = first.getMinX();
		double minY = first.getMinY();
		double maxX = first.getMaxX();
		double maxY = first.getMaxY();
		
		for(BasicObject obj: members){
			Bounds objBound = obj.getBound().getBoundsInParent(); 
			
			if(objBound.getMinX() < minX)
				minX = objBound.getMinX();
			if(objBound.getMinY() < minY)
				minY = objBound.getMinY();
			if(objBound.getMaxX() > maxX)
				maxX = objBound.getMaxX();
			if(objBound.getMaxY() > maxY)
				maxY = objBound.getMaxY();
		}
		
		bound.setX(minX - padding);
		bound.setY(minY - padding);
		bound.setWidth(maxX - minX + padding * 2);
		bound.setHeight(maxY - minY + padding * 2);
	}

	public void AddMember(BasicObject obj) {
		members.add(obj);
		calculateBound();
	}

	public ArrayList<BasicObject> GetMembers() {
		return members;
	}

	private BasicObject GetSelectedObj(Point2D mouse) {
		BasicObject object = null;
		int depth = 100;

		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getSelectedObjBound(mouse) != null) {
				if (members.get(i).getDepth() < depth) {
					object = members.get(i);
					depth = object.getDepth();
				}
			}
		}
		
		if(depth == 100)
			return null;
		else
			return object;
	}

	public Shape getSelectedObjBound(Point2D mouse) {
		BasicObject object = GetSelectedObj(mouse);

		if (object == null)
			return null;
		else
			return object.getSelectedObjBound(mouse);
	}

	@Override
	public Port choosePort(Point2D mouse) {
		BasicObject object = GetSelectedObj(mouse);
		return object.choosePort(mouse);
	}

	@Override
	public Shape getBound() {
		return bound;
	}
	
	@Override
	public void unGroup() {
		Canvas.getInstance().getChildren().remove(bound);
		ArrayList<BasicObject> objs = Canvas.getInstance().getBasicObjs();
		objs.addAll(members);
		objs.remove(this);
	}
	
	@Override
	public void setName() {

	}

	@Override
	public void buttonClicked(ActionEvent event) {

	}

	@Override
	public Shape[] getShape() {
		return null;
	}
}
