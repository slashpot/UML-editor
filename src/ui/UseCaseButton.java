package ui;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import mode.UseCaseMode;

public class UseCaseButton extends FunctionButton {

	public UseCaseButton() {

	}

	@Override
	protected void setUp() {
		name = "Use Case";
		tip = new Tooltip(name);
		icon = new Image("icon/use case.png");
		mode = new UseCaseMode();
	}
}
