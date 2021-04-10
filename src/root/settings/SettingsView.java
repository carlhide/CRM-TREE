package root.settings;

import java.io.IOException;

import application.Viewable;
import javafx.scene.layout.Pane;

public class SettingsView extends Viewable<SettingsController>{

	private static final String FXML_PATH = "Settings.fxml";
	private Pane parent;
	
	public SettingsView() throws IOException {
		
	}
	public void loadGUI(Pane parent) throws IOException {
		this.parent = parent;
		loadGUI(parent, FXML_PATH);
	}
	
}
