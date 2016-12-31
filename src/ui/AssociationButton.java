package ui;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mode.AssociateMode;
import mode.Mode;

public class AssociationButton extends ToggleButton{
	private String name = "Association Line";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("icon/association line.png");
	private Mode mode = new AssociateMode();
	
	public AssociationButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
	
	public Mode getMode(){
		return mode;
	}
}
