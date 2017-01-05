package ui;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import mode.GeneralizationMode;

public class GeneralizationButton extends FunctionButton {

	public GeneralizationButton() {

	}

	@Override
	protected void setUp() {
		name = "Generalization Line";
		tip = new Tooltip(name);
		icon = new Image("icon/generalization line.png");
		mode = new GeneralizationMode();
	}
}
