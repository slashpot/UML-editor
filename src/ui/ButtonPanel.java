package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import button.AssociationButton;
import button.ClassButton;
import button.CompositionButton;
import button.FunctionButton;
import button.GeneralizationButton;
import button.SelectButton;
import button.UseCaseButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;
import mode.Mode;

public final class ButtonPanel extends GridPane {
	private static final ButtonPanel INSTANCE = new ButtonPanel();

	private ToggleGroup group = new ToggleGroup();
	private FunctionButton[] buttons;
	private int buttonNumber;

	private ButtonPanel() {
		setId("button-panel");
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(5, 3, 5, 3));
		setButtons();
		addButtons();
	}

	private void setButtons() {
		buttonNumber = 6;
		buttons = new FunctionButton[buttonNumber];

		buttons[0] = new SelectButton();
		buttons[1] = new AssociationButton();
		buttons[2] = new GeneralizationButton();
		buttons[3] = new CompositionButton();
		buttons[4] = new ClassButton();
		buttons[5] = new UseCaseButton();
	}
	
	private void addButtons() {
		for (int i = 0; i < buttonNumber; i++) {
			buttons[i].setUserData(buttons[i].getMode());
			buttons[i].setToggleGroup(group);
			add(buttons[i], 0, i);
		}

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
