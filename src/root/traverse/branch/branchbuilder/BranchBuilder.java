package root.traverse.branch.branchbuilder;

import java.util.ArrayList;

import application.Viewable;
import javafx.scene.layout.Pane;
import root.traverse.branch.Branch;


/**
 * 
 * @author carlhide
 *
 */

public class BranchBuilder extends Viewable<BranchBuilderController> {

	private static final String FXML_PATH = "BranchBuilder.fxml";
	private Pane canvas;
	private Branch parent;
	private BranchBuilderController controller;

	/** Constructor
	 * 
	 * 	Load attributes from parent which is parameter
	 * 	-title
	 * 	-children
	 * 	-description
	 * 
	 * @param parent      - branch parent
	 */
	public BranchBuilder(Branch parent) {
		this.parent = parent;
	}

	/**
	 * Load GUI and set View to loaded controller.
	 * 
	 * @param canvas - Pane to load GUI on.
	 */
	public void loadGUI(Pane canvas) {
		this.canvas = canvas;
		loadGUI(canvas, FXML_PATH);
		controller = getController();
		controller.setView(this);

	}

	public void setParent(Branch parent) {
		this.parent = parent;
	}

	/**
	 * Load data to branchbuilder
	 * 
	 * 
	 * @param title       - title of branch
	 * @param description - description of branch
	 * @param children    - ArrayList with children<branch>
	 */
	public void loadData() {

	}

	public ArrayList<Branch> getChildren() {
		return parent.getChildren();
	}

	/**
	 * Add child to branch (parent)
	 * 
	 * @param b - branch child
	 */
	public void addChild(Branch b) {
		for (int i = 0; i < parent.getChildren().size(); i++) {
			if (parent.getChildren().get(i).getTitle().equals(b.getTitle())) {
				System.out.println("Child with that title already exists");
				return;
			}
		}
		parent.addChild(b);
	}

	public void removeChild(String title) {
		parent.removeChild(title);
	}

	public String getTitle() {
		return parent.getTitle();
	}

	public void setTitle(String title) {
		parent.setTitle(title);
	}

	public String getDescription() {
		return getParent().getDescription();
	}

	public void setDescription(String description) {
		parent.setDescription(description);
	}

	public Branch getParent() {
		return parent;
	}

	public Pane getCanvas() {
		return canvas;
	}
}
