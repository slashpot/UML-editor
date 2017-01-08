package button;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import mode.CompositionMode;

public class CompositionButton extends FunctionButton {

	public CompositionButton() {

	}

	@Override
	protected void setUp() {
		name = "Composition Line";
		tip = new Tooltip(name);
		icon = new Image("icon/composition line.png");
		mode = new CompositionMode();
	}
}
