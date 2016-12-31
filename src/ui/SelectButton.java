package ui;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mode.Mode;
import mode.SelectMode;

public class SelectButton extends ToggleButton{
	private String name = "Select";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("icon/select.png");
	private Mode mode = new SelectMode();
	
	public SelectButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
	
	public Mode getMode(){
		return mode;
	}
}
