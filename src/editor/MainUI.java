package editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainUI extends Application {
	private BorderPane root;
	private ButtonPanel buttonpane;
	private CanvasPanel canvaspane;
	private Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	public void init() throws Exception {
		root = new BorderPane();
		canvaspane = new CanvasPanel();
		buttonpane = new ButtonPanel();
		buttonpane.SetCanvasPane(canvaspane);
		root.setLeft(buttonpane);
		root.setCenter(canvaspane);
		SetMenuBar();
		scene = new Scene(root, 800, 520);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("UML editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void SetMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuEdit = new Menu("Edit");
		menuBar.getMenus().addAll(menuFile, menuEdit);
		root.setTop(menuBar);
	}

}