package ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RenamePanel {
	private HBox hbox;
	private Button ok;
	private Button cancel;
	private Label label;
	private TextField textField;
	private FlowPane pane;
	private Scene scene;
	private Stage stage;
	private Text name;
	
	public RenamePanel(Text name) {
		this.name = name;
	}
	
	public void rename() {
		hbox = new HBox();
		hbox.setSpacing(10);
		ok = new Button("OK");
		ok.setPrefSize(70, 20);
		ok.setOnAction(event -> buttonClicked(event));
		cancel = new Button("Cancel");
		cancel.setPrefSize(70, 20);
		cancel.setOnAction(event -> buttonClicked(event));
		hbox.getChildren().addAll(ok, cancel);

		label = new Label("Name: ");
		textField = new TextField();
		textField.setPrefWidth(175);

		pane = new FlowPane();
		pane.setId("rename-pane");
		pane.setPadding(new Insets(15, 12, 15, 12));
		pane.setHgap(15);
		pane.setVgap(10);
		pane.getChildren().addAll(label, textField, hbox);

		scene = new Scene(pane, 260, 80);
		scene.getStylesheets().add("css/Dark.css");
		stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Change Name");
		stage.showAndWait();
	}
	
	private void buttonClicked(ActionEvent event) {
		if (event.getSource() == ok) {
			String text = textField.getText();
			if (text.equals("") == false) {
				name.setText(text);
			}
			stage.close();
		} else if (event.getSource() == cancel) {
			stage.close();
		}
	}
}
