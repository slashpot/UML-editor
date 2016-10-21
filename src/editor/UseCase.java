package editor;

import javafx.geometry.Point2D;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class UseCase extends BasicObject {
	private int width = 100;
	private int height = 60;
	private Ellipse ellipse;
	private Rectangle[] rectports;
	private String text;

	public UseCase(int depth) {
		super(depth);
	}

	@Override
	public Ellipse GetShape() {
		SetPort();
		ellipse = new Ellipse(origin.getX() + width, origin.getY() + height, width, height);
		return ellipse;
	}

	@Override
	public void SetPort() {
		ports = new Point2D[4];
		ports[0] = new Point2D(origin.getX(), origin.getY() + height);
		ports[1] = new Point2D(origin.getX() + width, origin.getY());
		ports[2] = new Point2D(origin.getX(), origin.getY() - height);
		ports[3] = new Point2D(origin.getX() - width, origin.getY());
	}

	public void DrawPort() {
		rectports = new Rectangle[4];
		for (int i = 0; i < 4; i++) {
			rectports[i] = new Ractangle()
		}
	}
}
