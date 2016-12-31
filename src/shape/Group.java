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

	public Group() {
		isGroup = true;
	}
	
	public void SetPortsVisible(boolean set) {
		for(BasicObject obj: members){
			obj.SetPortsVisible(set);
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
		
		bound.setX(minX);
		bound.setY(minY);
		bound.setWidth(maxX - minX);
		bound.setHeight(maxY - minY);
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
