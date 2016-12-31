package mode;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import ui.Canvas;

public abstract class Mode {
	protected Canvas canvas = Canvas.getInstance();
	protected EventHandler<MouseEvent> clickEvent;
	protected EventHandler<MouseEvent> pressEvent;
	protected EventHandler<MouseEvent> dragEvent;
	protected EventHandler<MouseEvent> releaseEvent;
	
	public Mode(){
		setEvent();
	}
	
	public EventHandler<MouseEvent> getClickEvent(){
		return clickEvent;
	}
	
	public EventHandler<MouseEvent> getPressEvent(){
		return pressEvent;
	}
	
	public EventHandler<MouseEvent> getDragEvent(){
		return dragEvent;
	}
	
	public EventHandler<MouseEvent> getReleaseEvent(){
		return releaseEvent;
	}
	
	protected abstract void setEvent();
}
