package editor;

import javafx.geometry.Insets;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;

public final class ButtonPanel extends GridPane{
	private static final ButtonPanel INSTANCE = new ButtonPanel();
	
	private ToggleGroup group = new ToggleGroup();
	
	private SelectButton selectButton = new SelectButton();
	private AssociationLineButton associationLineButton = new AssociationLineButton();
	private GeneralizationLineButton generalizationLineButton = new GeneralizationLineButton();
	private CompositionLineButton compositionLineButton = new CompositionLineButton();
	private ClassButton classButton = new ClassButton();
	private UseCaseButton useCaseButton = new UseCaseButton();
	
	private ButtonPanel(){
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(5, 3, 5, 3));
		SetButtons();
	}
	
	private void SetButtons() {
		add(selectButton, 0, 0);
		add(associationLineButton, 0, 1);
		add(generalizationLineButton, 0, 2);
		add(compositionLineButton, 0, 3);
		
		classButton.setUserData(classButton.GetMode());
		classButton.setToggleGroup(group);
		add(classButton, 0, 4);
		
		add(useCaseButton, 0, 5);
		
		group.selectedToggleProperty().addListener(ButtonListener);
	}
	
	private ChangeListener<Toggle> ButtonListener = new ChangeListener<Toggle>() {
		@Override
		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
			if (newValue != null) {
				Mode SelectMode = (Mode) newValue.getUserData();
				Canvas.getInstance().SetMode(SelectMode);
			}
		}
	};
	
	public static ButtonPanel getInstance(){
		return INSTANCE;
	}
}
