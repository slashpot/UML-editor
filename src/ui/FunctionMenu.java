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
	private ArrayList<BasicObject> objects = canvas.GetBasicObjs();

	private Menu menuFile;
	private Menu menuEdit;

	private MenuItem groupItem;
	private MenuItem ungroupItem;
	private MenuItem renameItem;

	private FunctionMenu() {
		setMenus();
		setMenuItems();
		addMenus();
	}

	private void setMenus() {
		menuFile = new Menu("File");
		menuEdit = new Menu("Edit");
	}

	private void setMenuItems() {
		groupItem = new MenuItem("Group");
		menuEdit.getItems().add(groupItem);
		groupItem.setOnAction(groupEvent);

		ungroupItem = new MenuItem("UnGroup");
		menuEdit.getItems().add(ungroupItem);
		ungroupItem.setOnAction(unGroupEvent);

		renameItem = new MenuItem("Change Name");
		menuEdit.getItems().add(renameItem);
		renameItem.setOnAction(renameEvent);
	}

	private void addMenus() {
		getMenus().addAll(menuFile, menuEdit);
	}

	private EventHandler<ActionEvent> groupEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
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
	
	private EventHandler<ActionEvent> renameEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			int counter = 0;
			for(BasicObject obj: objects){
				if(obj.checkIfSelected() == true)
					counter++;
			}
			if(counter != 1)
				return;
			
			for(BasicObject obj: objects){
				if(obj.checkIfSelected() == true && obj.checkIfIsGroup() == false){
					obj.SetName();
					break;
				}
			}
		}
	};

	public static FunctionMenu getInstance() {
		return INSTANCE;
	}

}
