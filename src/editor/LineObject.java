package editor;

import javafx.scene.canvas.GraphicsContext;

public abstract class LineObject extends Object {

	public LineObject(int depth) {
		super(depth);
	}

	@Override
	public abstract void Draw();

}
