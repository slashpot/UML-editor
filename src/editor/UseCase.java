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
		shapes = new Shape[2];
		width = 60;
		height = 35;
	}

	protected void SetPort() {
		ports = new Point2D[4];
		ports[0] = new Point2D(origin.getX() + width, origin.getY() + height * 2);
		ports[1] = new Point2D(origin.getX() + width * 2, origin.getY() + height);
		ports[2] = new Point2D(origin.getX() + width, origin.getY());
		ports[3] = new Point2D(origin.getX(), origin.getY() + height);
	}

	@Override
	public Shape[] GetShape() {
		SetPort();

		name = new Text(origin.getX() + width / 2, origin.getY() + height * 1.1, "Name");
		name.setFont(new Font(20));
		name.setTextAlignment(TextAlignment.JUSTIFY);

		ellipse = new Ellipse(origin.getX() + width, origin.getY() + height, width, height);
		ellipse.setFill(Color.WHITE);
		ellipse.setStroke(Color.GRAY);

		shapes[0] = ellipse;
		shapes[1] = name;
		return shapes;
	}

	@Override
	public Shape GetBound() {
		return ellipse;
	}

}
