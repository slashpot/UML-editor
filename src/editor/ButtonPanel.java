package editor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class ButtonPanel extends GridPane {
	private int buttonNumbers = 6;

	private CanvasPanel canvaspane;

	private FunctionButton buttons[] = new FunctionButton[buttonNumbers];
	private Image icons[] = new Image[buttonNumbers];
	private ToggleGroup group = new ToggleGroup();
	private String names[] = { "select", "association line", "generalization line", "composition line", "class",
			"use case" };

	public ButtonPanel() {
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(5, 3, 5, 3));
		SetButtons();
	}

	public void SetCanvasPane(CanvasPanel panel) {
		canvaspane = panel;
	}

	private void SetButtons() {
		// connection line group
		for (int i = 0; i < buttonNumbers; i++) {
			String path = "editor/image/" + names[i] + ".png";
			icons[i] = new Image(path);
			buttons[i] = new FunctionButton(names[i], icons[i]);
			buttons[i].setUserData(i);
			buttons[i].setToggleGroup(group);
			group.selectedToggleProperty().addListener(ButtonListener);
			add(buttons[i], 0, i);
		}
	}

	private ChangeListener<Toggle> ButtonListener = new ChangeListener<Toggle>() {
		@Override
		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
			int index = (int) newValue.getUserData();
			canvaspane.SetObjIndex(index);
		}
	};
}
