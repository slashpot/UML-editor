package editor;

import javafx.scene.shape.Shape;

public class GenLine extends LineObject {

	public GenLine() {
		shapes = new Shape[2];
	}

	@Override
	public Shape[] GetShape() {
		shapes[0] = line;
		return shapes;
	}

}
