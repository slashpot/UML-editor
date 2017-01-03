package ui;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import mode.Mode;
import shape.BasicObject;

public final class Canvas extends Pane {
	private static final Canvas INSTANCE = new Canvas();

	private ArrayList<BasicObject> objects = new ArrayList<BasicObject>();

	private Canvas() {
		setId("canvas");
	}

	public void setMouseEvent(Mode mode) {
		setOnMouseClicked(mode.getClickEvent());
		setOnMousePressed(mode.getPressEvent());
		setOnMouseDragged(mode.getDragEvent());
		setOnMouseReleased(mode.getReleaseEvent());
	}

	public ArrayList<BasicObject> getBasicObjs() {
		return objects;
	}

	public static Canvas getInstance() {
		return INSTANCE;
	}
}
