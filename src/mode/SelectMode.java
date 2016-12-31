package mode;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class SelectMode extends Mode {
	
	public SelectMode() {

	}

	@Override
	public void setEvent() {
		clickEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			}
		};
		
		pressEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());
				canvas.SelectWithPress(mouse);
			}
		};
		
		dragEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				Point2D mouse = new Point2D(event.getX(), event.getY());

				// restrict mouse position
				if (mouse.getX() < 0 || mouse.getY() < 0) {
					return;
				}
				
				if(canvas.HasSelectRange() == true)
					canvas.DrawSelectRange(mouse);
				else
					canvas.MoveObjects(mouse);
			}
		};
		
		releaseEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(canvas.HasSelectRange() == true)
					canvas.SelectMultiple();
			}
		};
	}
}
