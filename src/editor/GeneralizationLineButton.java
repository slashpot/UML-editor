package editor;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GeneralizationLineButton extends ToggleButton{
	private String name = "Generalization Line";
	private Tooltip tip = new Tooltip(name);
	private Image icon = new Image("editor/image/generalization line.png");
	
	public GeneralizationLineButton(){
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
}

