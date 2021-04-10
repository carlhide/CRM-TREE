package root.traverse;

import java.io.IOException;

import application.TraverseTree;
import application.Viewable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import root.traverse.branch.Branch;

public class Traverse extends Viewable<TraverseController> {

	private static final String FXML_PATH = "Traverse.fxml";
	private Pane parent;

	public Traverse() throws IOException {

	}

	public void loadGUI(Pane parent) throws IOException {
		this.parent = parent;
		loadGUI(parent, FXML_PATH);
		getController().setView(this);
		System.out.println(this);
		getController().getNbrOfBranchesLabel().setText("Branches: " + TraverseTree.size());

	}

	public void beginTraverse() throws IOException {
		
		TraverseTree.getRoot().loadGUI(parent);
	}
	
	

}
