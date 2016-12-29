package ui;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SelectButton extends ToggleButton{
	private String name = "Select";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("icon/select.png");
	
	public SelectButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
}
