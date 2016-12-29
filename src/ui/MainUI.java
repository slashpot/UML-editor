package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainUI extends Application {
	private BorderPane root;
	private Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	public void init() throws Exception {
		root = new BorderPane();
		root.setTop(FunctionMenu.getInstance());
		root.setLeft(ButtonPanel.getInstance());
		root.setCenter(Canvas.getInstance());
		scene = new Scene(root, 1100, 735);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("UML editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
