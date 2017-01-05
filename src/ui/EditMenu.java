package ui;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import shape.BasicObject;
import shape.Group;

public class EditMenu extends Menu{
	private Canvas canvas = Canvas.getInstance(); 
	private ArrayList<BasicObject> objects = canvas.getBasicObjs();
	
	private MenuItem groupItem;
	private MenuItem ungroupItem;
	private MenuItem renameItem;
	
	public EditMenu(String name) {
		super(name);
		setMenuItems();
	}
	
	private void setMenuItems() {
		groupItem = new MenuItem("Group");
		getItems().add(groupItem);
		groupItem.setOnAction(groupEvent);

		ungroupItem = new MenuItem("UnGroup");
		getItems().add(ungroupItem);
		ungroupItem.setOnAction(unGroupEvent);

		renameItem = new MenuItem("Rename");
		getItems().add(renameItem);
		renameItem.setOnAction(renameEvent);
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

			if(newGroup.GetMembers().size() != 0 ){
				objects.add(newGroup);
				newGroup.setDepth(99 - objects.size());
			}

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
					obj.setName();
					break;
				}
			}
		}
	};
}
