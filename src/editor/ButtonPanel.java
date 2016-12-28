package editor;

import javafx.geometry.Insets;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public final class ButtonPanel extends GridPane{
	private static final ButtonPanel INSTANCE = new ButtonPanel();
	
	private int buttonNumbers = 6;
	private FunctionButton buttons[] = new FunctionButton[buttonNumbers];
	private Image icons[] = new Image[buttonNumbers];
	private ToggleGroup group = new ToggleGroup();
	private String names[] = { "select", "association line", "generalization line", "composition line", "class",
			"use case" };
	
	private ButtonPanel(){
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
			// set function index
			buttons[i].setUserData(i);
			buttons[i].setToggleGroup(group);
			add(buttons[i], 0, i);
		}
	}
	
	public static ButtonPanel getInstance(){
		return INSTANCE;
	}
}
