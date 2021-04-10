package root.traverse.branch;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import application.Controller;
import application.Viewable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import root.traverse.branch.branchbuilder.BranchBuilder;

public class BranchController extends Controller<Branch> {

	@FXML
	private VBox children_vbox;
	@FXML
	private JFXButton parent_btn, edit_btn;
	@FXML
	private Label titleLabel, descriptionLabel;
	
	//----------------FXML----------------//
	
	private Branch view;



	public BranchController() {

	}

	@FXML
	public void initialize() {

	}

	public void setView(Branch view) {
		this.view = view;
		populateChildrenVBox();
		setListeners();
		loadData();

	}

	public Branch getView() {
		return view;
	}

	private void loadData() {
		titleLabel.setText(getView().getTitle());
		descriptionLabel.setText(getView().getDescription());
	}

	private void populateChildrenVBox() {
		for (Branch b : getView().getChildren()) {
			JFXButton button = new JFXButton();
			button.setText(b.getTitle());
			button.setOnAction(e -> {
				b.loadGUI(getView().getCanvas());
			});

			button.setStyle("-fx-background-color: orange;");
			button.setMaxHeight(Double.MAX_VALUE);
			button.setPrefWidth(100.0);
			HBox.setMargin(button, new Insets(10));
			children_vbox.getChildren().add(button);
		}
	}

	private void setListeners() {

		if (getView().getParent() == null) {
			parent_btn.setVisible(false);
		} else {
			parent_btn.setText("Back to: " + getView().getParent().getTitle());
			parent_btn.setOnAction(e -> {
				System.out.println(getView());
				System.out.println(getView().getParent());
				getView().getParent().loadGUI(getView().getCanvas());
			});
		}

		edit_btn.setOnAction(e -> {
			BranchBuilder branchBuilder = getView().getBranchBuilder();
			branchBuilder.loadGUI(getView().getCanvas());
			String title = getView().getTitle();
			String description = getView().getDescription();
			ArrayList<Branch> children = getView().getChildren();
		});
	}

}
