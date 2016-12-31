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
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class BasicObject extends Object {
	protected int width;
	protected int height;
	protected Port ports[] = new Port[4];
	protected Port oldPorts[] = new Port[4];
	protected Text name;
	protected boolean isSelect = false;
	protected boolean isGroup = false;

	// used for move object
	protected double oldPosX;
	protected double oldPosY;
	protected double oldTranslateX;
	protected double oldTranslateY;

	private Button ok;
	private Button cancel;
	private TextField textField;
	private Stage stage;

	public BasicObject() {
		
	}
	
	public Port[] GetPorts() {
		return ports;
	}
	
	
	public void SetPortsVisible(boolean set) {
		for (int i = 0; i < 4; i++) {
			ports[i].GetRectangle().setVisible(set);
		}
	}

	public Port ChoosePort(Point2D point) {
		int choose = 0;
		double min = ports[0].getDistance(point);

		for (int j = 1; j < 4; j++) {
			if (ports[j].getDistance(point) < min) {
				min = ports[j].getDistance(point);
				choose = j;
			}
		}
		return ports[choose];
	}

	public void SetSelect(boolean bool) {
		isSelect = bool;
	}

	public boolean checkIfSelected() {
		return isSelect;
	}
	
	public boolean checkIfIsGroup() {
		return isGroup;
	}

	// store old translate before move objects
	public void SetOldTranslate(Point2D pos) {
		oldPosX = pos.getX();
		oldPosY = pos.getY();
		oldTranslateX = GetBound().getTranslateX();
		oldTranslateY = GetBound().getTranslateY();
		
		for(int i = 0; i < ports.length; i++){
			oldPorts[i] = new Port(ports[i].getX(), ports[i].getY());
		}
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
		
		for(int i = 0; i < ports.length; i++){
			ports[i].setX(oldPorts[i].getX() + offsetX);
			ports[i].setY(oldPorts[i].getY() + offsetY);
		}
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

	public abstract Shape GetBound();
	public abstract Shape GetSelectedObjBound(Point2D mouse);
	public abstract void unGroup();
}
