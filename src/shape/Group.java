package shape;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ui.Canvas;

public class Group extends BasicObject {
	private ArrayList<BasicObject> members = new ArrayList<BasicObject>();
	private Rectangle bound = new Rectangle();
	private double padding = 15.0;

	public Group() {
		isGroup = true;
		
		bound.setFill(Color.TRANSPARENT);
		bound.setStroke(Color.BLACK);
		Canvas.getInstance().getChildren().add(bound);
	}
	
	public void SetPortsVisible(boolean set) {
		for(BasicObject obj: members){
			obj.SetPortsVisible(set);
		}
	}
	
	// store old translate before move objects
	public void SetOldTranslate(Point2D pos) {
		oldPosX = pos.getX();
		oldPosY = pos.getY();
		oldTranslateX = GetBound().getTranslateX();
		oldTranslateY = GetBound().getTranslateY();
		
		for(BasicObject obj: members){
			obj.SetOldTranslate(pos);
		}
	}
	
	// move while dragging
	public void Move(Point2D pos) {
		double offsetX = pos.getX() - oldPosX;
		double offsetY = pos.getY() - oldPosY;
		double newTranslateX = oldTranslateX + offsetX;
		double newTranslateY = oldTranslateY + offsetY;
		
		bound.setTranslateX(newTranslateX);
		bound.setTranslateY(newTranslateY);

		for(BasicObject obj: members){
			obj.Move(pos);
		}
	}

	private void calculateBound() {
		Bounds first = members.get(0).GetBound().getBoundsInParent();
		double minX = first.getMinX();
		double minY = first.getMinY();
		double maxX = first.getMaxX();
		double maxY = first.getMaxY();
		
		for(BasicObject obj: members){
			Bounds objBound = obj.GetBound().getBoundsInParent(); 
			
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
			if (members.get(i).GetSelectedObjBound(mouse) != null) {
				if (members.get(i).GetDepth() < depth) {
					object = members.get(i);
					depth = object.GetDepth();
				}
			}
		}
		
		if(depth == 100)
			return null;
		else
			return object;
	}

	public Shape GetSelectedObjBound(Point2D mouse) {
		BasicObject object = GetSelectedObj(mouse);

		if (object == null)
			return null;
		else
			return object.GetSelectedObjBound(mouse);
	}

	@Override
	public Port ChoosePort(Point2D mouse) {
		BasicObject object = GetSelectedObj(mouse);
		return object.ChoosePort(mouse);
	}

	@Override
	public Shape GetBound() {
		return bound;
	}
	
	@Override
	public void unGroup() {
		Canvas.getInstance().getChildren().remove(bound);
		ArrayList<BasicObject> objs = Canvas.getInstance().GetBasicObjs();
		objs.addAll(members);
		objs.remove(this);
	}
	
	@Override
	public void SetName() {

	}

	@Override
	public void ButtonClicked(ActionEvent event) {

	}

	@Override
	public Shape[] GetShape() {
		return null;
	}
}
