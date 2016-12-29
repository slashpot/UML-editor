package ui;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CompositionButton extends ToggleButton{	
	private String name = "Composition Line";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("icon/composition line.png");
	
	public CompositionButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
}
