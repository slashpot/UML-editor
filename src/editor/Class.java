package editor;

import javafx.scene.shape.Rectangle;

public class Class extends BasicObject {
	private int width = 60;
	private int height = 60;
	private Rectangle rect;
	private String text;

	public Class(int depth) {
		super(depth);

	}

	public Rectangle GetShape() {
		rect = new Rectangle(origin.getX(), origin.getY(), width, height);
		return rect;
	}

	@Override
	public void SetPort() {

	}

}
