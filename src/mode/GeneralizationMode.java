package mode;

import shape.GeneralizationLine;

public class GeneralizationMode extends LineObjMode{

	public GeneralizationMode() {
		
	}
	
	@Override
	protected void newObj() {
		newLine = new GeneralizationLine();
	}
}
