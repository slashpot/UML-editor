package editor;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class UseCase extends BasicObject {
	private Ellipse ellipse;
	private String text;

	public UseCase(int depth) {
		super(depth);
		width = 100;
		height = 60;
	}

	protected void SetPort() {
		ports = new Point2D[4];
		ports[0] = new Point2D(origin.getX() + width, origin.getY() + height * 2);
		ports[1] = new Point2D(origin.getX() + width * 2, origin.getY() + height);
		ports[2] = new Point2D(origin.getX() + width, origin.getY());
		ports[3] = new Point2D(origin.getX(), origin.getY() + height);
	}

	@Override
	public Ellipse GetShape() {
		SetPort();
		ellipse = new Ellipse(origin.getX() + width, origin.getY() + height, width, height);
		ellipse.setFill(Color.WHITE);
		ellipse.setStroke(Color.GRAY);
		return ellipse;
	}

}
