package root.chat;

import java.io.IOException;

import application.Viewable;
import javafx.scene.layout.Pane;

public class Chat extends Viewable<ChatController>{

	private static final String FXML_PATH = "Chat.fxml";
	private Pane parent;
	
	public Chat() throws IOException {
		
	}
	public void loadGUI(Pane parent) throws IOException {
		this.parent = parent;
		loadGUI(parent, FXML_PATH);
	}
	
}
