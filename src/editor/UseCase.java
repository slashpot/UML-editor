package editor;

import javafx.geometry.Point2D;
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
		ports[0] = new Point2D(origin.getX() + width, origin.getY() + height * 2);
		ports[1] = new Point2D(origin.getX() + width * 2, origin.getY() + height);
		ports[2] = new Point2D(origin.getX() + width, origin.getY());
		ports[3] = new Point2D(origin.getX(), origin.getY() + height);
		
		for (int i = 0; i < 4; i++) {
			rectports[i].setX(ports[i].getX() - 2.5);
			rectports[i].setY(ports[i].getY() - 2.5);
			rectports[i].setWidth(5);
			rectports[i].setHeight(5);
			rectports[i].setVisible(false);
		}
		
		shapes[2] = rectports[0];
		shapes[3] = rectports[1];
		shapes[4] = rectports[2];
		shapes[5] = rectports[3];
	}

	@Override
	public Shape[] GetShape() {
		SetPort();
		// set object text
		name.setX(origin.getX() + width / 2);
		name.setY(origin.getY() + height * 1.1);
		name.setText("Name");
		name.setFont(new Font(20));
		name.setTextAlignment(TextAlignment.JUSTIFY);
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
