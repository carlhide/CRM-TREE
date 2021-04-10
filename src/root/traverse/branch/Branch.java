package root.traverse.branch;

import java.io.IOException;


import java.util.ArrayList;

import org.json.JSONObject;

import application.TraverseTree;
import application.Viewable;
import javafx.scene.layout.Pane;
import root.traverse.Traverse;
import root.traverse.branch.branchbuilder.BranchBuilder;

public class Branch extends Viewable<BranchController> implements Comparable<Branch> {

	
	public static int branchIDs = 0;
	private static final String FXML_PATH = "Branch.fxml";
	private Pane canvas;
	private String title, description;
	private Branch parent;
	private ArrayList<Branch> children;
	private BranchBuilder branchBuilder;
	private Traverse traverseView;
	private int branchID;

	/**
	 *  Constructor, load title from parameter
	 * 	
	 * 
	 * @param title of branch
	 */
	public Branch(String title) {
		this.title = title;
		children = new ArrayList<Branch>();
		parent = null;
		traverseView = null;
		description = null;
		branchID = branchIDs++;
		System.out.println("BranchIDs: " + branchIDs);
	}
	
	/** Clone object
	 * 
	 * @param branch to clone
	 */
	public Branch(Branch b) {
		title = b.getTitle();
		children = b.getChildren();
		parent = b.getParent();
		traverseView = b.geTraverseView();
		description = b.getDescription();
		branchID = branchIDs++;

	}

	/**
	 * Loads this branch GUI on Pane
	 * 
	 * @param parent - to load FXML on
	 * @throws IOException
	 */
	public void loadGUI(Pane parent) {
		this.canvas = parent;
		loadGUI(parent, FXML_PATH);
		getController().setView(this);
	}

	/**
	 * Set this branch parent (another branch)
	 * 
	 * @param parent - this branch parent
	 */
	public void setParent(Branch parent) {
		this.parent = parent;
	}

	public ArrayList<Branch> getChildren() {
		return children;
	}

	public void addChild(Branch c) {
		children.add(c);
		c.setParent(this);
		System.out.println("TreeSize: " + TraverseTree.size());
	}
	
	public void removeChild(String title) {
		for (int i = children.size(); i >=0; i--) {
			if (children.get(i).getTitle().equals(title)) {
				children.remove(i);
				System.out.println("TreeSize: " + TraverseTree.size());
				return;
			}
		}
		System.out.println("Couldnt find child with title: " + title);
	}

	public Pane getCanvas() {
		return canvas;
	}

	public BranchBuilder getBranchBuilder() {
		return new BranchBuilder(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Branch getParent() {
		return parent;
	}

	public void setTraverseView(Traverse view) {
		traverseView = view;
	}

	public Traverse geTraverseView() {
		return traverseView;
	}
	
	public int getBranchID() {
		return branchID;
	}
	
	public boolean equals(Object o) {
		
		return o instanceof Branch && ((Branch) o).getBranchID()==branchID;
	
	}
	
	public String toString() {
		return "Branch ID: " + branchID + ", " + title;
	}
	
	public String toStringReadable() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nBranch: " + title);
		sb.append("\n\tDescription: " + description);
		if(parent == null) {
			sb.append("\n\tParent: null");
		}else {
			sb.append("\n\tParent: " + parent.getTitle());
		}
		sb.append("\n\tChildren: ");
		for(Branch c: children) {
			sb.append("\n\t\tChild: " + c.getTitle());
		}
		return sb.toString();
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("title", title);
		json.put("description", description);
		json.put("parent", title);

		
		return new JSONObject();
	}

	/**
	 * Descending
	 */
	@Override
	public int compareTo(Branch o) {
		return title.compareTo(o.getTitle());
	}

}
