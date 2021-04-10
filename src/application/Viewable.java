package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Viewable<T> {

	private T controller;
	private Pane parent;

	public Viewable() {

	}

	public void loadGUI(Pane parent, String FXMLPath) {
		System.out.println("2. Launching " + this.getClass().getSimpleName() + " \t - \t loadGUI()");

		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPath));
		AnchorPane child;
		try {
			child = (AnchorPane) loader.load();
			controller = loader.getController();
			parent.getChildren().clear();

			parent.getChildren().add(child);
			setAllAnchors(child);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setAllAnchors(Node child) {
		AnchorPane.setBottomAnchor(child, 0.0);
		AnchorPane.setRightAnchor(child, 0.0);
		AnchorPane.setTopAnchor(child, 0.0);
		AnchorPane.setLeftAnchor(child, 0.0);
	}

	public T getController() {
		return controller;
	}

}
