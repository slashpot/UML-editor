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
		shapes = new Shape[4];
		width = 100;
		height = 100;
	}

	protected void SetPort() {
		ports = new Point2D[4];
		ports[0] = new Point2D(origin.getX() + width / 2, origin.getY());
		ports[1] = new Point2D(origin.getX() + width, origin.getY() + height / 2);
		ports[2] = new Point2D(origin.getX() + width / 2, origin.getY() + height);
		ports[3] = new Point2D(origin.getX(), origin.getY() + height / 2);
	}

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

	@Override
	public Shape GetBound() {
		return rect;
	}

}
