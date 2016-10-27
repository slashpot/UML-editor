package editor;

import javafx.geometry.Point2D;
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
		shapes = new Shape[8];
		width = 100;
		height = 100;
	}

	protected void SetPort() {
		// set port's position
		ports[0] = new Point2D(origin.getX() + width / 2, origin.getY());
		ports[1] = new Point2D(origin.getX() + width, origin.getY() + height / 2);
		ports[2] = new Point2D(origin.getX() + width / 2, origin.getY() + height);
		ports[3] = new Point2D(origin.getX(), origin.getY() + height / 2);
		
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
		name = new Text(origin.getX() + width / 4.5, origin.getY() + height / 4, "Name");
		name.setFont(new Font(20));
		name.setTextAlignment(TextAlignment.JUSTIFY);
		
		top = new Line(origin.getX(), origin.getY() + height / 3, origin.getX() + width, origin.getY() + height / 3);
		middle = new Line(origin.getX(), origin.getY() + height / 3 * 2, origin.getX() + width, origin.getY() + height / 3 * 2);
		
		rect = new Rectangle(origin.getX(), origin.getY(), width, height);
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.GRAY);
		
		shapes[0] = rect;
		shapes[1] = name;
		shapes[2] = top;
		shapes[3] = middle;
		
		return shapes;
	}
}
