package ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public final class FunctionMenu extends MenuBar {
	private static final FunctionMenu INSTANCE = new FunctionMenu();
	private Menu[] menus;
	private int menuNumber;

	private FunctionMenu() {
		setId("menu-bar");
		setMenus();
		addMenus();
	}

	private void setMenus() {
		menuNumber = 1;
		menus = new Menu[menuNumber];
		
		menus[0] = new EditMenu("Edit");
	}

	private void addMenus() {
		for(int i = 0; i <menuNumber; i++) {
			getMenus().add(menus[i]);
		}
	}

	public static FunctionMenu getInstance() {
		return INSTANCE;
	}

}
