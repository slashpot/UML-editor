package mode;

import shape.CompositionLine;

public class CompositionMode extends LineObjMode{
	
	public CompositionMode() {
		
	}
	
	@Override
	protected void newObj() {
		newLine = new CompositionLine();
	}
}
