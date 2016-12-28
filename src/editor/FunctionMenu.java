package editor;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public final class FunctionMenu extends MenuBar{
	private static final FunctionMenu INSTANCE = new FunctionMenu();
	
	private Menu menuFile;
	private Menu menuEdit;

	private MenuItem group;
	private MenuItem ungroup;
	private MenuItem changeName;
	
	private FunctionMenu(){
		SetMenus();
		SetMenuItems();
		AddMenus();
	}
	
	private void SetMenus() {
		menuFile = new Menu("File");
		menuEdit = new Menu("Edit");
	}

	private void SetMenuItems() {
		group = new MenuItem("Group");
		menuEdit.getItems().add(group);

		ungroup = new MenuItem("UnGroup");
		menuEdit.getItems().add(ungroup);

		changeName = new MenuItem("Change Name");
		menuEdit.getItems().add(changeName);
	}

	private void AddMenus() {
		getMenus().addAll(menuFile, menuEdit);
	}
	
	
	public static FunctionMenu getInstance(){
		return INSTANCE;
	}

}
