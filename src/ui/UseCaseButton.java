package ui;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mode.Mode;
import mode.UseCaseMode;


public class UseCaseButton extends ToggleButton{
	private String name = "Use Case";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("icon/use case.png");
	private Mode mode = new UseCaseMode();
	
	public UseCaseButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
	
	public Mode GetMode(){
		return mode;
	}
}
