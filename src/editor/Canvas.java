package editor;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public final class Canvas extends Pane{
	private static final Canvas INSTANCE = new Canvas();
	
	private Canvas(){
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public static Canvas getInstance() {
		return INSTANCE;
	}
}
