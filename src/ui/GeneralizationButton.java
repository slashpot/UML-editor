package ui;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mode.GeneralizationMode;
import mode.Mode;

public class GeneralizationButton extends ToggleButton{
	private String name = "Generalization Line";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("icon/generalization line.png");
	private Mode mode = new GeneralizationMode();
	
	public GeneralizationButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
	
	public Mode getMode() {
		return mode;
	}
}

