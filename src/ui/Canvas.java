package ui;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mode.Mode;
import shape.BasicObject;
import shape.LineObject;

public final class Canvas extends Pane {
	private static final Canvas INSTANCE = new Canvas();

	private ArrayList<BasicObject> objects = new ArrayList<BasicObject>();
	private ArrayList<LineObject> lines = new ArrayList<LineObject>();

	private Canvas() {
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
	
	public ArrayList<LineObject> getLineObjs() {
		return lines;
	}

	public static Canvas getInstance() {
		return INSTANCE;
	}
}
