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
		group.setOnAction(groupEvent);

		ungroup = new MenuItem("UnGroup");
		menuEdit.getItems().add(ungroup);
		ungroup.setOnAction(unGroupEvent);

		changeName = new MenuItem("Change Name");
		menuEdit.getItems().add(changeName);
	}

	private void AddMenus() {
		getMenus().addAll(menuFile, menuEdit);
	}

	private EventHandler<ActionEvent> groupEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			ArrayList<BasicObject> objects = canvas.GetBasicObjs();
			Group newGroup = new Group();
			
			int counter = 0;
			for(BasicObject obj: objects){
				if(obj.checkIfSelected() == true)
					counter++;
			}
			if(counter <= 1)
				return;
			
			for(Iterator<BasicObject> it = objects.iterator(); it.hasNext();){
				BasicObject obj = it.next();
				
				if(obj.checkIfSelected() == true){
					newGroup.AddMember(obj);
					it.remove();
				}
			}

			if(newGroup.GetMembers().size() != 0 )
				objects.add(newGroup);
		}
	};
	
	private EventHandler<ActionEvent> unGroupEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			ArrayList<BasicObject> objects = canvas.GetBasicObjs();

			int counter = 0;
			for(BasicObject obj: objects){
				if(obj.checkIfSelected() == true)
					counter++;
			}
			if(counter != 1)
				return;
			
			for(BasicObject obj: objects){
				if(obj.checkIfSelected() == true && obj.checkIfIsGroup() == true){
					obj.unGroup();
					break;
				}
			}
			
		}
	};

	public static FunctionMenu getInstance() {
		return INSTANCE;
	}

}
