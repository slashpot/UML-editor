package editor;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Class extends BasicObject {
	private Rectangle rect;
	private String text;

	public Class(int depth) {
		super(depth);
		width = 100;
		height = 60;
	}

	protected void SetPort() {
		ports = new Point2D[4];
		ports[0] = new Point2D(origin.getX() + width / 2, origin.getY());
		ports[1] = new Point2D(origin.getX() + width, origin.getY() + height / 2);
		ports[2] = new Point2D(origin.getX() + width / 2, origin.getY() + height);
		ports[3] = new Point2D(origin.getX(), origin.getY() + height / 2);
	}

	public Rectangle GetShape() {
		SetPort();
		rect = new Rectangle(origin.getX(), origin.getY(), width, height);
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.GRAY);
		return rect;
	}

}
