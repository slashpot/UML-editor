package mode;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import shape.BasicObject;
import shape.LineObject;
import ui.Canvas;

public abstract class Mode {
	protected Canvas canvas = Canvas.getInstance();
	protected ArrayList<BasicObject> objects = canvas.getBasicObjs();
	protected ArrayList<LineObject> lines = canvas.getLineObjs();
	protected EventHandler<MouseEvent> clickEvent;
	protected EventHandler<MouseEvent> pressEvent;
	protected EventHandler<MouseEvent> dragEvent;
	protected EventHandler<MouseEvent> releaseEvent;
	
	public Mode(){
		setEvent();
	}
	
	protected BasicObject getInsideObject(Point2D point) {
		int index = -1;
		int depth = 100;

		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).getSelectedObjBound(point) != null) {
				if (objects.get(i).getDepth() < depth) {
					index = i;
					depth = objects.get(i).getDepth();
				}
			}
		}

		if (index == -1)
			return null;
		else
			return objects.get(index);
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
