package editor;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AssociationLineButton extends ToggleButton{
	private String name = "Association Line";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("editor/image/association line.png");
	
	public AssociationLineButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
}
