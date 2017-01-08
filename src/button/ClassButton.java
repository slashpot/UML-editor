package button;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import mode.ClassMode;

public class ClassButton extends FunctionButton {
	public ClassButton() {

	}

	@Override
	protected void setUp() {
		name = "Class";
		tip = new Tooltip(name);
		icon = new Image("icon/class.png");
		mode = new ClassMode();
	}
}
