package shape;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class BasicObject extends Object {
	protected int width;
	protected int height;
	protected Port ports[] = new Port[4];
	protected Port oldPorts[];
	protected Rectangle rectports[] = new Rectangle[4];
	protected Text name;
	protected boolean isSelect = false;

	// used for move object
	private double oldPosX;
	private double oldPosY;
	private double oldTranslateX;
	private double oldTranslateY;

	private Button ok;
	private Button cancel;
	private TextField textField;
	private Stage stage;

	public BasicObject() {
		// initialize port's rectangle
		for (int i = 0; i < 4; i++) {
			rectports[i] = new Rectangle();
		}
	}
	
	public Port[] GetPorts() {
		return ports;
	}
	
	// choose the index of closest port to connect line
	public int ChoosePort(Point2D point) {
		int choose = 0;
		double min = point.distance(ports[0]);

		// choose the port which has minimum distance
		for (int j = 1; j < 4; j++) {
			if (point.distance(ports[j]) < min) {
				min = point.distance(ports[j]);
				choose = j;
			}
		}
		return choose;
	}

	public void SetPortsVisible(boolean set) {
		for (int i = 0; i < 4; i++) {
			rectports[i].setVisible(set);
		}
	}

	public void SetSelect(boolean bool) {
		isSelect = bool;
	}

	public boolean GetSelect() {
		return isSelect;
	}

	// store old translate before move objects
	public void SetOldTranslate(Point2D pos) {
		oldPosX = pos.getX();
		oldPosY = pos.getY();
		oldTranslateX = GetBound().getTranslateX();
		oldTranslateY = GetBound().getTranslateY();

		oldPorts = ports.clone();
	}

	// move while dragging
	public void Move(Point2D pos) {
		double offsetX = pos.getX() - oldPosX;
		double offsetY = pos.getY() - oldPosY;
		double newTranslateX = oldTranslateX + offsetX;
		double newTranslateY = oldTranslateY + offsetY;

		for (int i = 0; i < shapes.length; i++) {
			shapes[i].setTranslateX(newTranslateX);
			shapes[i].setTranslateY(newTranslateY);
		}
		for (int i = 0; i < 4; i++) {
			ports[i] = new Port(oldPorts[i].getX() + offsetX, oldPorts[i].getY() + offsetY);
		}

	}

	// set new port positions after move
	public void SetNewPorts(Point2D pos) {
		double offsetX = pos.getX() - oldPosX;
		double offsetY = pos.getY() - oldPosY;
		for (int i = 0; i < 4; i++) {
			ports[i] = new Port(ports[i].getX() + offsetX, ports[i].getY() + offsetY);
		}
	}

	// get outside bound
	public Shape GetBound() {
		return shapes[0];
	}

	public void SetName() {
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		ok = new Button("OK");
		ok.setPrefSize(70, 20);
		ok.setOnAction(event -> ButtonClicked(event));
		cancel = new Button("Cancel");
		cancel.setPrefSize(70, 20);
		cancel.setOnAction(event -> ButtonClicked(event));
		hbox.getChildren().addAll(ok, cancel);

		Label label = new Label("Name: ");
		textField = new TextField();
		textField.setPrefWidth(175);

		FlowPane pane = new FlowPane();
		pane.setPadding(new Insets(15, 12, 15, 12));
		pane.setHgap(15);
		pane.setVgap(10);
		pane.getChildren().addAll(label, textField, hbox);

		Scene scene = new Scene(pane, 260, 80);
		stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Change Name");
		stage.showAndWait();
	}

	public void ButtonClicked(ActionEvent event) {
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

	protected abstract void SetPort();
}
