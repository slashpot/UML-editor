package button;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mode.Mode;

public abstract class FunctionButton extends ToggleButton {
	protected String name;
	protected Tooltip tip;
	protected Image icon;
	protected Mode mode;

	public FunctionButton() {
		setUp();
		initialize();
	}

	protected void initialize() {
		setGraphic(new ImageView(icon));
		setTooltip(tip);
		setId(name);
	}

	public Mode getMode() {
		return mode;
	}

	protected abstract void setUp();
}
