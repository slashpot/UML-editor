package mode;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import shape.BasicObject;

public abstract class BasicObjMode extends Mode{
	protected BasicObject newObject;
	
	protected void setEvent() {
		clickEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());
				newObj();
				addBasicObject(mouse, newObject);
			}
		};
		
		pressEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				
			}
		};
		
		dragEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				
			}
		};
		
		releaseEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				
			}
		};
	}
	
	private void addBasicObject(Point2D mouse, BasicObject newObject) {
		newObject.setOrigin(mouse);
		canvas.getChildren().addAll(newObject.getShape());
		newObject.setDepth(99 - objects.size());
		objects.add(newObject);
	}
	
	protected abstract void newObj();
}
