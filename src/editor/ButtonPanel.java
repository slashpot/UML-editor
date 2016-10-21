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

	private void SetButtons() {
		for (int i = 0; i < buttonNumbers; i++) {
			String path = "editor/image/" + names[i] + ".png";
			icons[i] = new Image(path);
			buttons[i] = new FunctionButton(names[i], icons[i]);
			buttons[i].setToggleGroup(group);
			group.selectedToggleProperty().addListener(ButtonListener);
			add(buttons[i], 0, i);
		}
		SetButtonDatas();
	}

	private void SetButtonDatas() {
		buttons[4].setUserData(new Class(0));
		buttons[5].setUserData(new UseCase(0));
	}

	public void SetCanvasPane(CanvasPanel panel) {
		canvaspane = panel;
	}

	private ChangeListener<Toggle> ButtonListener = new ChangeListener<Toggle>() {
		@Override
		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
			Object newObject = (Object) newValue.getUserData();
			canvaspane.SetObject(newObject);
		}

	};

}
