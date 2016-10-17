package editor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	BorderPane root;
	FunctionButton buttons[];
	Scene scene;
	
	public void init() throws Exception{
		root = new BorderPane();
		SetButtons();
		SetCanvas();
		SetMenuBar();
		scene = new Scene(root, 800, 520);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("UML editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void SetButtons() {
		GridPane buttonpane = new GridPane();
		buttonpane.setHgap(10);
		buttonpane.setVgap(10);
		buttonpane.setPadding(new Insets(5, 3, 5, 3));
		
		buttons = new FunctionButton[6];
		Image icons[] = new Image[6];

		for (int i = 0; i < 6; i++) {
			String name = "editor/image/" + i + ".png";
			icons[i] = new Image(name);
			
			buttons[i] = new FunctionButton(i);
			buttons[i].setGraphic(new ImageView(icons[i]));
			buttonpane.add(buttons[i], 0, i);
		}
		root.setLeft(buttonpane);
	}

	private void SetCanvas() {
		Pane canvaspane = new Pane();
		root.setCenter(canvaspane);
		canvaspane.setBackground(new Background(new BackgroundFill(
			    Color.WHITE,
			    CornerRadii.EMPTY,
			    Insets.EMPTY)));
	}

	private void SetMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuEdit = new Menu("Edit");
		menuBar.getMenus().addAll(menuFile, menuEdit);
		root.setTop(menuBar);
	}
	
}
