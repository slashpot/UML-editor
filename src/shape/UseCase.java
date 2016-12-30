package shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UseCase extends BasicObject {
	private Ellipse ellipse;

	public UseCase() {
		name = new Text();
		ellipse = new Ellipse();
		shapes = new Shape[6];
		width = 60;
		height = 35;
	}

	protected void SetPort() {
		// set port's position
		ports[0] = new Port(origin.getX() + width, origin.getY() + height * 2);
		ports[1] = new Port(origin.getX() + width * 2, origin.getY() + height);
		ports[2] = new Port(origin.getX() + width, origin.getY());
		ports[3] = new Port(origin.getX(), origin.getY() + height);

		// set port's rectangle
		for (int i = 0; i < 4; i++) {
			rectports[i].setX(ports[i].getX() - 2.5);
			rectports[i].setY(ports[i].getY() - 2.5);
			rectports[i].setWidth(5);
			rectports[i].setHeight(5);
			rectports[i].setVisible(false);
		}

		// add port rectangles to shapes
		shapes[2] = rectports[0];
		shapes[3] = rectports[1];
		shapes[4] = rectports[2];
		shapes[5] = rectports[3];
	}

	@Override
	public Shape[] GetShape() {
		SetPort();
		// set object text
		name.setX(origin.getX() + width / 1.7);
		name.setY(origin.getY() + height * 1.1);
		name.setText("Name");
		name.setFont(new Font(17));
		name.setTextAlignment(TextAlignment.CENTER);
		// set ellipse
		ellipse.setCenterX(origin.getX() + width);
		ellipse.setCenterY(origin.getY() + height);
		ellipse.setRadiusX(width);
		ellipse.setRadiusY(height);
		ellipse.setFill(Color.WHITE);
		ellipse.setStroke(Color.GRAY);
		// add all shapes
		shapes[0] = ellipse;
		shapes[1] = name;

		return shapes;
	}
}
