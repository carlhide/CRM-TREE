package login;

import java.io.IOException;

import application.Viewable;
import javafx.scene.layout.Pane;

public class Login extends Viewable<LoginController>{

	private static final String FXML_PATH = "Login.fxml";
	private Pane parent;
	
	public Login() throws IOException {
		
	}
	public void loadGUI(Pane parent) throws IOException {
		this.parent = parent;
		loadGUI(parent, FXML_PATH);
	}
	
	public boolean login(String username, String password) {
		return true;
	}
	
}
