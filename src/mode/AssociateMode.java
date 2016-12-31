package mode;

import shape.AssociateLine;

public class AssociateMode extends LineObjMode {

	public AssociateMode() {
		
	}
	
	@Override
	protected void newObj() {
		newLine = new AssociateLine();
	}
	
}
