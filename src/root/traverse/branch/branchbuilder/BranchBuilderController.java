package root.traverse.branch.branchbuilder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import application.Controller;
import application.TraverseTree;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import root.traverse.branch.Branch;

public class BranchBuilderController extends Controller<BranchBuilder> {

	@FXML
	private JFXButton backButton, saveButton, addChildButton;
	@FXML
	private HBox childrenHBox;
	@FXML
	private JFXTextField addChildField, titleField;
	@FXML
	private JFXTextArea descriptionArea;
	@FXML
	private JFXComboBox<Branch> addChildMenu;
//	@FXML
//	private JFXToggleButton addToggleButton;

	// ----------------FXML----------------//

	private BranchBuilder view;

	public BranchBuilderController() {

	}

	@FXML
	public void initialize() {
		setListeners();
	}

	public void setView(BranchBuilder view) {
		this.view = view;
		loadData();

	}

	private void loadData() {

		populateChildrenHBox();

		titleField.setText(getView().getTitle());
		descriptionArea.setText(getView().getDescription());
	}

	private void populateMenuBox() {
		String newBranch = "New Branch";
		addChildMenu.getItems().clear();
		addChildMenu = new JFXComboBox<Branch>();
		addChildMenu.setPromptText("Add existing branch to this");
		addChildMenu.setLabelFloat(true);
		addChildField = new JFXTextField(); // Must be added before populateMenuBox since its used in that method
		addChildField.setPromptText("Create new branch");
		addChildField.setLabelFloat(true);
		// Add items to ComboBox
		addChildMenu.getItems().add(new Branch(newBranch));
		addChildMenu.getItems().addAll(TraverseTree.allUniqueBranchesSorted());

		// Disable TextField if "New Branch" is not selected
		addChildMenu.setOnAction(e -> {
			System.out.println(addChildMenu.getSelectionModel().selectedItemProperty().toString());
			if (addChildMenu.getSelectionModel().selectedItemProperty().toString().contains(newBranch)) {
				addChildField.setDisable(false);
			} else {
				addChildField.setDisable(true);
			}
		});
	}

	private void populateChildrenHBox() {

		childrenHBox.setAlignment(Pos.CENTER_LEFT);
		childrenHBox.getChildren().clear();

		populateMenuBox();
		String newBranch = "New Branch";

		addChildButton = new JFXButton("Add child");
		addChildButton.setOnAction(e -> {
			if (addChildMenu.getSelectionModel().getSelectedItem().getTitle().equals(newBranch)) {
				getView().addChild(new Branch(addChildField.getText()));
			} else {
				getView().addChild(new Branch(addChildMenu.getSelectionModel().getSelectedItem()));
				addChildMenu.getSelectionModel().clearSelection();
			}
			System.out.println("addChildButton: Pressed!");
			populateChildrenHBox();
		});

		addChildButton.setStyle("-fx-background-color: lightgray;");
		childrenHBox.getChildren().add(addChildButton);
		childrenHBox.getChildren().add(addChildField);
		childrenHBox.getChildren().add(addChildMenu);

		for (Branch b : getView().getChildren()) {
			JFXButton button = new JFXButton();
			button.setText(b.getTitle());
			button.setOnAction(e -> {
				System.out.println("child pressed");
				// b.loadGUI(getView().getCanvas()); <---------- HEJ
			});

			button.setStyle("-fx-background-color: orange;");
			button.setMaxHeight(Double.MAX_VALUE);
			button.setPrefWidth(100.0);
			childrenHBox.getChildren().add(button);
		}

		for (Node n : childrenHBox.getChildren()) {
			HBox.setMargin(n, new Insets(10));
		}
	}

	private void setListeners() {

		backButton.setOnAction(e -> {
			getView().getParent().loadGUI(view.getCanvas());
		});

		saveButton.setOnMouseClicked(e -> {
			view.getParent().setDescription(descriptionArea.getText());
			loadData();
		});

		titleField.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				System.out.println("Saved ned title");
				view.setTitle(titleField.getText());
				loadData();
			}
		});

	}

	/**
	 * @return the backButton
	 */
	public JFXButton getBackButton() {
		return backButton;
	}

	/**
	 * @param backButton the backButton to set
	 */
	public void setBackButton(JFXButton backButton) {
		this.backButton = backButton;
	}

	/**
	 * @return the saveButton
	 */
	public JFXButton getSaveButton() {
		return saveButton;
	}

	/**
	 * @param saveButton the saveButton to set
	 */
	public void setSaveButton(JFXButton saveButton) {
		this.saveButton = saveButton;
	}

	/**
	 * @return the addChildButton
	 */
	public JFXButton getAddChildButton() {
		return addChildButton;
	}

	/**
	 * @param addChildButton the addChildButton to set
	 */
	public void setAddChildButton(JFXButton addChildButton) {
		this.addChildButton = addChildButton;
	}

	/**
	 * @return the childrenHBox
	 */
	public HBox getChildrenHBox() {
		return childrenHBox;
	}

	/**
	 * @param childrenHBox the childrenHBox to set
	 */
	public void setChildrenHBox(HBox childrenHBox) {
		this.childrenHBox = childrenHBox;
	}

	/**
	 * @return the view
	 */
	public BranchBuilder getView() {
		return view;
	}

}
