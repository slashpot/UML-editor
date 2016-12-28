package editor;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public final class Canvas extends Pane{
	private static final Canvas INSTANCE = new Canvas();
	
	private ArrayList<BasicObject> objects = new ArrayList<BasicObject>();
	
	private Canvas(){
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void SetMode(Mode currentMode){
		if(currentMode.GetClickEvent()!=null)
			setOnMouseClicked(currentMode.GetClickEvent());
	}
	
	public void AddBasicObject(Point2D mouse, BasicObject newObject) {
		newObject.SetOrigin(mouse);
		getChildren().addAll(newObject.GetShape());
		newObject.SetDepth(99 - objects.size());
		objects.add(newObject);
	}
	
	public static Canvas getInstance() {
		return INSTANCE;
	}
}
