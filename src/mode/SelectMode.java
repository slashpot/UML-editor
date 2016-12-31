package mode;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shape.BasicObject;
import shape.LineObject;

public class SelectMode extends Mode {
	private Rectangle selectRange;
	private Point2D rangeOrigin;
	private boolean hasSelectRange = false;
	
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
				selectWithPress(mouse);
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
				
				if(hasSelectRange == true)
					drawSelectRange(mouse);
				else
					moveObjects(mouse);
			}
		};
		
		releaseEvent = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(hasSelectRange == true)
					selectMultiple();
			}
		};
	}
	
	private void selectWithPress(Point2D mouse) {
		BasicObject obj = getInsideObject(mouse);

		if (obj != null) {
			selectSingle(mouse, obj);
			saveTranslates(mouse);
		}

		else {
			unselectObjects();
			createSelectRectangle(mouse);
		}
	}
	
	private void selectSingle(Point2D mouse, BasicObject selectedObject) {
		selectedObject.setSelect(true);
		selectedObject.setPortsVisible(true);

		for (BasicObject obj : objects) {
			if (obj.equals(selectedObject) == false) {
				obj.setSelect(false);
				obj.setPortsVisible(false);
			}
		}
	}
	
	private void saveTranslates(Point2D mouse) {
		for (BasicObject object : objects) {
			if (object.checkIfSelected() == true) {
				object.setOldTranslate(mouse);
			}
		}
	}

	private void unselectObjects() {
		for (BasicObject obj : objects) {
			obj.setSelect(false);
			obj.setPortsVisible(false);
		}
	}

	private void createSelectRectangle(Point2D mouse) {
		selectRange = new Rectangle(0, 0);
		selectRange.setFill(Color.TRANSPARENT);
		selectRange.setStroke(Color.GRAY);
		selectRange.setX(mouse.getX());
		selectRange.setY(mouse.getY());
		rangeOrigin = mouse;
		canvas.getChildren().add(selectRange);
		hasSelectRange = true;
	}

	private void drawSelectRange(Point2D mouse) {
		double width = mouse.getX() - rangeOrigin.getX();
		double height = mouse.getY() - rangeOrigin.getY();

		if (width > 0) {
			selectRange.setWidth(width);
		} else {
			selectRange.setWidth((-1.0) * width);
			selectRange.setX(mouse.getX());
		}

		if (height > 0) {
			selectRange.setHeight(height);
		} else {
			selectRange.setHeight((-1.0) * height);
			selectRange.setY(mouse.getY());
		}
	}
	
	private void selectMultiple() {
		for (int i = 0; i < objects.size(); i++) {
			if (selectRange.getBoundsInParent().contains(objects.get(i).getBound().getBoundsInParent())) {
				objects.get(i).setSelect(true);
				objects.get(i).setPortsVisible(true);
			}
		}

		canvas.getChildren().remove(selectRange);
		hasSelectRange = false;
	}
	

	private void moveObjects(Point2D mouse) {
		for (BasicObject object : objects) {
			if (object.checkIfSelected() == true) {
				object.move(mouse);
			}
		}

		// redraw lines
		for (LineObject line : canvas.getLineObjs()) {
			Point2D startPoint = new Point2D(line.getStartPort().getX(), line.getStartPort().getY());
			Point2D endPoint = new Point2D(line.getEndPort().getX(), line.getEndPort().getY());
			line.setOrigin(startPoint);
			line.setDest(endPoint);
		}
	}
}
