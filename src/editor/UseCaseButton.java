package editor;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UseCaseButton extends ToggleButton{
	private String name = "Use Case";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("editor/image/use case.png");
	
	public UseCaseButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
}
