package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;
import mode.Mode;

public final class ButtonPanel extends GridPane {
	private static final ButtonPanel INSTANCE = new ButtonPanel();

	private ToggleGroup group = new ToggleGroup();

	private SelectButton selectButton = new SelectButton();
	private AssociationButton associationButton = new AssociationButton();
	private GeneralizationButton generalizationButton = new GeneralizationButton();
	private CompositionButton compositionButton = new CompositionButton();
	private ClassButton classButton = new ClassButton();
	private UseCaseButton useCaseButton = new UseCaseButton();

	private ButtonPanel() {
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(5, 3, 5, 3));
		SetButtons();
	}

	private void SetButtons() {
		add(selectButton, 0, 0);

		// associationButton.setUserData(associationButton.GetMode());
		// associationButton.setToggleGroup(group);
		add(associationButton, 0, 1);

		add(generalizationButton, 0, 2);
		add(compositionButton, 0, 3);

		classButton.setUserData(classButton.GetMode());
		classButton.setToggleGroup(group);
		add(classButton, 0, 4);

		useCaseButton.setUserData(useCaseButton.GetMode());
		useCaseButton.setToggleGroup(group);
		add(useCaseButton, 0, 5);

		group.selectedToggleProperty().addListener(ButtonListener);
	}

	private ChangeListener<Toggle> ButtonListener = new ChangeListener<Toggle>() {
		@Override
		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
			if (newValue != null) {
				Mode SelectMode = (Mode) newValue.getUserData();
				Canvas.getInstance().SetMouseEvent(SelectMode);
			}
		}
	};

	public static ButtonPanel getInstance() {
		return INSTANCE;
	}
}
