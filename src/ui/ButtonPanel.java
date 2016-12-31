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
		setButtons();
	}

	private void setButtons() {
		selectButton.setUserData(selectButton.getMode());
		selectButton.setToggleGroup(group);
		add(selectButton, 0, 0);

		associationButton.setUserData(associationButton.getMode());
		associationButton.setToggleGroup(group);
		add(associationButton, 0, 1);

		generalizationButton.setUserData(generalizationButton.getMode());
		generalizationButton.setToggleGroup(group);
		add(generalizationButton, 0, 2);
		
		compositionButton.setUserData(compositionButton.getMode());
		compositionButton.setToggleGroup(group);
		add(compositionButton, 0, 3);

		classButton.setUserData(classButton.getMode());
		classButton.setToggleGroup(group);
		add(classButton, 0, 4);

		useCaseButton.setUserData(useCaseButton.getMode());
		useCaseButton.setToggleGroup(group);
		add(useCaseButton, 0, 5);

		group.selectedToggleProperty().addListener(buttonListener);
	}

	private ChangeListener<Toggle> buttonListener = new ChangeListener<Toggle>() {
		@Override
		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
			if (newValue != null) {
				Mode newMode = (Mode) newValue.getUserData();
				Canvas.getInstance().setMouseEvent(newMode);
			}
		}
	};

	public static ButtonPanel getInstance() {
		return INSTANCE;
	}
}
