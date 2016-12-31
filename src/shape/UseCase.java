package shape;

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
	
	// get outside bound
	public Shape GetBound() {
		return shapes[0];
	}
	
	@Override
	public Shape GetSelectedObjBound(Point2D mouse) {
		if(shapes[0].getBoundsInParent().contains(mouse))
			return shapes[0];
		else
			return null;
	}

	protected void SetPort() {
		// set port's position
		ports[0] = new Port(origin.getX() + width, origin.getY() + height * 2);
		ports[1] = new Port(origin.getX() + width * 2, origin.getY() + height);
		ports[2] = new Port(origin.getX() + width, origin.getY());
		ports[3] = new Port(origin.getX(), origin.getY() + height);

		// set port's rectangle
		for (int i = 0; i < 4; i++) {
			ports[i].GetRectangle().setX(ports[i].getX() - 2.5);
			ports[i].GetRectangle().setY(ports[i].getY() - 2.5);
			ports[i].GetRectangle().setWidth(5);
			ports[i].GetRectangle().setHeight(5);
			ports[i].GetRectangle().setVisible(false);
		}

		// add port rectangles to shapes
		shapes[2] = ports[0].GetRectangle();
		shapes[3] = ports[1].GetRectangle();
		shapes[4] = ports[2].GetRectangle();
		shapes[5] = ports[3].GetRectangle();
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
