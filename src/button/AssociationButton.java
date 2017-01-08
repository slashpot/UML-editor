package button;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import mode.AssociateMode;

public class AssociationButton extends FunctionButton {
	public AssociationButton() {

	}

	@Override
	protected void setUp() {
		name = "Association Line";
		tip = new Tooltip(name);
		icon = new Image("icon/association line.png");
		mode = new AssociateMode();
	}
}
