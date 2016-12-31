package mode;

import shape.UseCase;

public class UseCaseMode extends BasicObjMode {
	public UseCaseMode() {
		
	}

	@Override
	protected void newObj() {
		newObject = new UseCase(); 
	}
}
