package editor;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClassButton extends ToggleButton{
	private String name = "Class";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("editor/image/class.png");
	private Mode mode = new ClassMode();
	
	public ClassButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
	
	public Mode GetMode(){
		return mode;
	}
}
