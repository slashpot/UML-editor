package shape;

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
		name = new Text();
		rect = new Rectangle();
		shapes = new Shape[8];
		width = 90;
		height = 90;
	}
	
	@Override
	// get outside bound
	public Shape getBound() {
		return shapes[0];
	}
	
	@Override
	public Shape getSelectedObjBound(Point2D mouse) {
		if(shapes[0].getBoundsInParent().contains(mouse))
			return shapes[0];
		else
			return null;
	}

	private void setPort() {
		// set port's position
		ports[0] = new Port(origin.getX() + width / 2, origin.getY());
		ports[1] = new Port(origin.getX() + width, origin.getY() + height / 2);
		ports[2] = new Port(origin.getX() + width / 2, origin.getY() + height);
		ports[3] = new Port(origin.getX(), origin.getY() + height / 2);
		
		// set port's rectangle
		for (int i = 0; i < 4; i++) 
			ports[i].initializeRectangle();
		
		// add port rectangles to shapes
		shapes[4] = ports[0].getRectangle();
		shapes[5] = ports[1].getRectangle();
		shapes[6] = ports[2].getRectangle();
		shapes[7] = ports[3].getRectangle();
	}

	// get all shapes
	public Shape[] getShape() {
		setPort();
		
		name.setX(origin.getX() + width / 4.5);
		name.setY(origin.getY() + height/4.5);
		name.setText("Name");
		name.setFont(new Font(17));
		name.setTextAlignment(TextAlignment.CENTER);

		top = new Line(origin.getX(), origin.getY() + height / 3, origin.getX() + width, origin.getY() + height / 3);
		middle = new Line(origin.getX(), origin.getY() + height / 3 * 2, origin.getX() + width, origin.getY() + height / 3 * 2);
		top.setStroke(Color.BLACK);
		middle.setStroke(Color.BLACK);
		
		rect.setX(origin.getX());
		rect.setY(origin.getY());
		rect.setWidth(width);
		rect.setHeight(height);
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.BLACK);
		
		shapes[0] = rect;
		shapes[1] = name;
		shapes[2] = top;
		shapes[3] = middle;
		
		return shapes;
	}

	@Override
	public void unGroup() {

	}
}
