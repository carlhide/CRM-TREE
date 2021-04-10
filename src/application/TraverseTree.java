package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.json.JSONObject;

import root.traverse.branch.Branch;

/**
 * 
 * @author carlhide
 *
 */

public final class TraverseTree {

	private static Branch root;

	/**
	 * Set first branch in tree
	 * 
	 * @param rootBranch - first branch in tree
	 */
	public static void setRoot(Branch rootBranch) {
		root = rootBranch;
	}
	
	/**
	 *  Return root branch
	 */
	public static Branch getRoot() {
		return root;
	}

	/**
	 * Get first branch in tree
	 * 
	 * @param root
	 * @return root - first branch in tree
	 */

	public Branch getRoot(Branch root) {
		return root;
	}

	/**
	 * Calculate number of branchs including root
	 * 
	 * @return number of branches
	 */

	
	public static int size() {
		return getBranches().size();
	}

	public static List<Branch> allUniqueBranchesSorted() {
		List<Branch> sortedArrayList = new ArrayList<Branch>();
		sortedArrayList = getBranches();
		Collections.sort(sortedArrayList);
		return sortedArrayList;
	}
	
	/** Goes through tree starting with root, queues all children of root, add it to the list
	 *  and removes is from the queue to proceed with next branch in the queue.
	 * 
	 * @return list of all unique branches
	 */
	
	public static List<Branch> getBranches(){
		if(root == null){
			return null;
		}
		Queue<Branch> q = new LinkedList<Branch>();
		List<Branch> l = new ArrayList<Branch>();
		q.add(root);
		l.add(root);
		while(!q.isEmpty()) {
			Branch b = q.peek();
			if(!l.contains(b)) {
				l.add(b);
			}
			q.remove();
			for(Branch branch: b.getChildren()) {
				q.add(branch);
			}
		}
		System.out.println("getBranches: " + l.toString());
		System.out.println("getBranches size: " + l.size());
		return l;
	}
	
	public JSONObject toJson() {
		JSONObject tree = new JSONObject();
		
		for(Branch branch: getBranches()) {
			
		}
		return new JSONObject();
	}

}
