package editor;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class Mode {
	protected  EventHandler<MouseEvent> ClickEvent;
	protected  EventHandler<MouseEvent> PressEvent;
	protected  EventHandler<MouseEvent> DragEvent;
	protected  EventHandler<MouseEvent> ReleaseEvent;
	
	public Mode(){
		
	}
	
	public EventHandler<MouseEvent> GetClickEvent(){
		return ClickEvent;
	}
	
	public EventHandler<MouseEvent> GetPressEvent(){
		return PressEvent;
	}
	
	public EventHandler<MouseEvent> GetDragEvent(){
		return DragEvent;
	}
	
	public EventHandler<MouseEvent> GetReleaseEvent(){
		return ReleaseEvent;
	}
}
