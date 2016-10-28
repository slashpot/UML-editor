package editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainUI extends Application {
	private FuncMenu menuBar;
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
		menuBar = new FuncMenu();
		menuBar.SetCanvasPane(canvaspane);

		root.setTop(menuBar);
		root.setLeft(buttonpane);
		root.setCenter(canvaspane);
		scene = new Scene(root, 1100, 735);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("UML editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}