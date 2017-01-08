package button;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import mode.SelectMode;

public class SelectButton extends FunctionButton {
	public SelectButton() {

	}

	@Override
	protected void setUp() {
		name = "Select";
		tip = new Tooltip(name);
		icon = new Image("icon/select.png");
		mode = new SelectMode();
	}
}
