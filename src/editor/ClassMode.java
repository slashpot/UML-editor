package editor;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class ClassMode extends Mode{
	public ClassMode(){
		SetEvents();
	}
	
	private void SetEvents(){
		ClickEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());
				BasicObject newObject = new Class();
				Canvas.getInstance().AddBasicObject(mouse, newObject);
			}
		};
		
		PressEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				
			}
		};
		
		DragEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				
			}
		};
		
		ReleaseEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				
			}
		};
	}
	

}
