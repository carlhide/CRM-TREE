package root;

import java.io.IOException;

import application.Viewable;
import javafx.scene.layout.Pane;
import root.chat.Chat;
import root.dashboard.DashboardView;
import root.settings.SettingsView;
import root.traverse.Traverse;

public class RootView extends Viewable<RootController> {
	
	private static final String FXML_PATH = "Root.fxml";
	private Pane parent;
	private DashboardView dbv;
	private SettingsView sv;
	private Traverse tv;
	private Chat cv;


	
	public RootView() throws IOException{
		dbv = new DashboardView();
		sv = new SettingsView();
		tv = new Traverse();
		cv = new Chat();

	}
	
	public void loadGUI(Pane parent) throws IOException {
		this.parent = parent;
		loadGUI(parent, FXML_PATH);
		getController().setRootView(this);
		System.out.println();
		dbv.loadGUI(getController().getCanvas());
	}
	
	/** Handles Root Menu presses
	 * 
	 * @param input - action event source
	 * @throws IOException
	 */
	
	public void menuHandler(String input) throws IOException {
		if(input.contains("sett")) {
			sv.loadGUI(getController().getCanvas());
		}else if(input.contains("dash")) {
			dbv.loadGUI(getController().getCanvas());
		}else if(input.contains("trav")) {
			tv.loadGUI(getController().getCanvas());
		}else if(input.contains("chat")) {
			cv.loadGUI(getController().getCanvas());
		}
	}
	
	
	
	
	

}
