package shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Class extends BasicObject {
	private Rectangle rect;
	private Line top;
	private Line middle;

	public Class() {
		name = new Text();
		rect = new Rectangle();
		shapes = new Shape[8];
		width = 90;
		height = 90;
	}

	protected void SetPort() {
		// set port's position
		ports[0] = new Port(origin.getX() + width / 2, origin.getY());
		ports[1] = new Port(origin.getX() + width, origin.getY() + height / 2);
		ports[2] = new Port(origin.getX() + width / 2, origin.getY() + height);
		ports[3] = new Port(origin.getX(), origin.getY() + height / 2);
		
		// set port's rectangle
		for (int i = 0; i < 4; i++) {
			rectports[i].setX(ports[i].getX() - 2.5);
			rectports[i].setY(ports[i].getY() - 2.5);
			rectports[i].setWidth(5);
			rectports[i].setHeight(5);
			rectports[i].setVisible(false);
		}
		
		// add port rectangles to shapes
		shapes[4] = rectports[0];
		shapes[5] = rectports[1];
		shapes[6] = rectports[2];
		shapes[7] = rectports[3];
	}

	// get all shapes
	public Shape[] GetShape() {
		SetPort();
		
		name.setX(origin.getX() + width / 4.5);
		name.setY(origin.getY() + height/4.5);
		name.setText("Name");
		name.setFont(new Font(17));
		name.setTextAlignment(TextAlignment.CENTER);

		top = new Line(origin.getX(), origin.getY() + height / 3, origin.getX() + width, origin.getY() + height / 3);
		middle = new Line(origin.getX(), origin.getY() + height / 3 * 2, origin.getX() + width, origin.getY() + height / 3 * 2);
		top.setStroke(Color.GRAY);
		middle.setStroke(Color.GRAY);
		
		rect.setX(origin.getX());
		rect.setY(origin.getY());
		rect.setWidth(width);
		rect.setHeight(height);
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.GRAY);
		
		shapes[0] = rect;
		shapes[1] = name;
		shapes[2] = top;
		shapes[3] = middle;
		
		return shapes;
	}
}
