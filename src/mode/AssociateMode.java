package mode;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import shape.BasicObject;
import shape.Class;
import ui.Canvas;

public class AssociateMode extends Mode{

	@Override
	public void SetEvent() {
		ClickEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

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
