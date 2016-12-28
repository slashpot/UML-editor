package editor;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FunctionButton extends ToggleButton{
	private Tooltip tip;

	public FunctionButton(String name, Image icon) {
		tip = new Tooltip(name);
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}
}
