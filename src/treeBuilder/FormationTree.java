package treeBuilder;

public class FormationTree {

	private Node root;
	
	public FormationTree() {
		this.root = null;
	}
	
	public FormationTree(Node root) {
		this.root = root;
	}
	
	private boolean addRoot(Node n) {
		boolean rootExists = root != null;
		if (!rootExists) {
			root = n;
			rootExists = true;
		}
		
		return rootExists;
	}
	
	public boolean addNode(Node n) {
		boolean result;
		boolean rootExists = root != null;
		
		System.out.println("Adding node... " + rootExists);
		if (root == null)
			result = addRoot(n);
		else {
			
			
			result = false;
		}
		System.out.println(result);
		return result;
	}
	
	@Override
	public String toString() {
		return root.getValue();
	}
	
}
