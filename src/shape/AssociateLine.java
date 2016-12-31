package shape;

import javafx.scene.shape.Shape;

public class AssociateLine extends LineObject {

	public AssociateLine() {
		shapes = new Shape[1];
	}

	@Override
	public Shape[] getShape() {
		shapes[0] = line;
		return shapes;
	}
}
