package editor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tooltip;
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
	Pane canvaspane;
	Button buttons[];
	Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void init() throws Exception {
		root = new BorderPane();
		SetButtons();
		SetCanvas();
		SetMenuBar();
		scene = new Scene(root, 800, 520);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("UML editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void SetButtons() {
		// create button panel
		GridPane buttonpane = new GridPane();
		buttonpane.setHgap(10);
		buttonpane.setVgap(10);
		buttonpane.setPadding(new Insets(5, 3, 5, 3));
		
		// set Tooptips
		String tipnames[] = {"select", "association line","generalization line","composition line","class","usecase"};
		final Tooltip tips[] = new Tooltip[6];
		for (int i = 0; i < 6; i++) {
			tips[i] = new Tooltip();
			tips[i].setText(tipnames[i]);
		}

		// initialize and add buttons
		buttons = new Button[6];
		Image icons[] = new Image[6];

		for (int i = 0; i < 6; i++) {
			String name = "editor/image/" + i + ".png";
			icons[i] = new Image(name);

			buttons[i] = new Button();
			buttons[i].setGraphic(new ImageView(icons[i]));
			buttons[i].setTooltip(tips[i]);
			buttonpane.add(buttons[i], 0, i);
		}

		// add panel to root
		root.setLeft(buttonpane);
	}

	private void SetCanvas() {
		canvaspane = new Pane();
		root.setCenter(canvaspane);
		canvaspane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void SetMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuEdit = new Menu("Edit");
		menuBar.getMenus().addAll(menuFile, menuEdit);
		root.setTop(menuBar);
	}

}
