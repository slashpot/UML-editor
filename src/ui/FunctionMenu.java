package ui;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import shape.BasicObject;
import shape.Group;

public final class FunctionMenu extends MenuBar {
	private static final FunctionMenu INSTANCE = new FunctionMenu();
	private Canvas canvas = Canvas.getInstance(); 

	private Menu menuFile;
	private Menu menuEdit;

	private MenuItem group;
	private MenuItem ungroup;
	private MenuItem changeName;

	private FunctionMenu() {
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
		group.setOnAction(GroupEvent);

		ungroup = new MenuItem("UnGroup");
		menuEdit.getItems().add(ungroup);

		changeName = new MenuItem("Change Name");
		menuEdit.getItems().add(changeName);
	}

	private void AddMenus() {
		getMenus().addAll(menuFile, menuEdit);
	}

	private EventHandler<ActionEvent> GroupEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			ArrayList<BasicObject> objects = canvas.GetBasicObjs();
			Group newGroup = new Group();
			
			for(Iterator<BasicObject> it = objects.iterator(); it.hasNext();){
				BasicObject obj = it.next();
				
				if(obj.GetSelect() == true){
					newGroup.AddMember(obj);
					it.remove();
				}
			}

			if(newGroup.GetMembers().size() != 0 )
				objects.add(newGroup);
		}
	};

	public static FunctionMenu getInstance() {
		return INSTANCE;
	}

}
