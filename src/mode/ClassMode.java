package mode;

import shape.Class;

public class ClassMode extends BasicObjMode {

	public ClassMode() {

	}

	@Override
	protected void newObj() {
		newObject = new Class(); 
	}
}
