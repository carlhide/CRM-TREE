package root.traverse;

import java.io.IOException;

import application.TraverseTree;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TraverseController {
	
	@FXML private Button traverse_btn;
	@FXML private Label nbrOfBranchesLabel;
	public Label getNbrOfBranchesLabel() {
		return nbrOfBranchesLabel;
	}

	public void setNbrOfBranchesLabel(Label nbrOfBranchesLabel) {
		this.nbrOfBranchesLabel = nbrOfBranchesLabel;
	}

	private Traverse view;
	private int size;
	
	public TraverseController() {
		size = 0;
	}
	
	@FXML
	public void initialize() {
		initiateListeners();
	}
	
	public void initiateListeners() {
		System.out.println("*   ");
		
		traverse_btn.setOnAction(e->{
			System.out.println("Action: Traverse Button pressed");
			System.out.println("Test: " + view);

			try {
				view.beginTraverse();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	public Button getTraverse_btn() {
		return traverse_btn;
	}
	
	public void setView(Traverse view) {
		this.view = view;
		initiateListeners();
	}

}
