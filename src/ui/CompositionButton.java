package ui;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mode.CompositionMode;
import mode.Mode;

public class CompositionButton extends ToggleButton{	
	private String name = "Composition Line";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("icon/composition line.png");
	private Mode mode = new CompositionMode();
	
	public CompositionButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
	
	public Mode getMode() {
		return mode;
	}
}
