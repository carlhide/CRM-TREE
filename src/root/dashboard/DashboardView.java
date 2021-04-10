package root.dashboard;

import java.io.IOException;

import application.Viewable;
import javafx.scene.layout.Pane;

public class DashboardView extends Viewable<DashboardController>{

	private static final String FXML_PATH = "Dashboard.fxml";
	private Pane parent;
	
	public DashboardView() throws IOException {
		
	}
	public void loadGUI(Pane parent) throws IOException {
		this.parent = parent;
		loadGUI(parent, FXML_PATH);
	}
	
}
