package editor;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class FuncMenu extends MenuBar {
	// used for store CanvasPanel objects
	private CanvasPanel canvaspane;
	private ArrayList<BasicObject> objects;
	private ArrayList<CompositeNode> nodes;
	private CompositeNode node;

	private Menu menuFile;
	private Menu menuEdit;

	private MenuItem group;
	private MenuItem ungroup;
	private MenuItem changeName;

	public FuncMenu() {
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
		ungroup.setOnAction(UnGroupEvent);

		changeName = new MenuItem("Change Name");
		menuEdit.getItems().add(changeName);
		changeName.setOnAction(ChangeNameEvent);
	}

	private void AddMenus() {
		getMenus().addAll(menuFile, menuEdit);
	}

	public void SetCanvasPane(CanvasPanel panel) {
		canvaspane = panel;
	}

	private EventHandler<ActionEvent> GroupEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			objects = canvaspane.GetObjects();
			node = new CompositeNode();
			for (int i = 0; i < objects.size(); i++) {
				if (objects.get(i).GetSelect() == true) {
					node.AddData(i);
				}
			}
			canvaspane.AddNode(node);
		}
	};

	private EventHandler<ActionEvent> UnGroupEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			objects = canvaspane.GetObjects();
			nodes = canvaspane.GetNodes();

			for (int node = nodes.size() - 1; node >= 0; node--) {
				ArrayList<Integer> data = nodes.get(node).GetData();
				int dataNumber = 0;
				for (int i = 0; i < data.size(); i++) {
					int index = data.get(i);
					if (objects.get(index).isSelect == true) {
						dataNumber++;
					}
				}
				if (dataNumber == data.size()) {
					for (int i = 0; i < data.size(); i++) {
						int index = data.get(i);
						objects.get(index).SetSelect(false);
						objects.get(index).SetPortsVisible(false);
					}
					canvaspane.RemoveNode(node);
					break;
				}
			}
		}
	};

	private EventHandler<ActionEvent> ChangeNameEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			objects = canvaspane.GetObjects();
			int number = 0;
			int index = -1;
			for (int i = 0; i < objects.size(); i++) {
				if (objects.get(i).GetSelect() == true) {
					number++;
					index = i;
				}
			}

			if (number == 1) {
				objects.get(index).SetName();
			}
		}
	};
}
