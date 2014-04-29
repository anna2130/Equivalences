package treeBuilder;

public class FormationTree {

	private Node root;
	
	public FormationTree() {
		this.root = null;
	}
	
	public FormationTree(Node root) {
		this.root = root;
	}
	
	public void setRoot(Node n) {
		root = n;
		root.setKey(0);
		root.setDepth(0);
	}
	
	public void addNode(Node n) {
		boolean rootExists = root != null;
		
		if (rootExists) {
			int parentKey = n.getKey() >> 1;
			int depth = n.getDepth() - 1;
			Node parent = findNode(parentKey, depth);
			
			if (parent instanceof UnaryOperator) {
				((UnaryOperator) parent).setChild(n);
			} else {
				if (n.getKey() % 2 == 0)
					((BinaryOperator) parent).setLeftChild(n);
				else
					((BinaryOperator) parent).setRightChild(n);
			}
			
		} else {
			root = n;
		}
	}
	
	public Node findNode(int key, int depth) {
		Node n = root;
		
		for (int i = 0; i < depth; i++) {
			int leftMostBit = key >> depth - i - 1;
		
			// Remove left most bit from key
			if (leftMostBit == 1) {
				int mask = 1 << depth - i - 1;
				key = key & ~mask;
			}
			
			n = n.getChildren()[leftMostBit];
		}
		
		return n;
	}
	
	public Node findParent(int key, int depth) {
		int parentKey = key >> 1;
		int parentDepth = depth - 1;
		
		return findNode(parentKey, parentDepth);
	}
	
	@Override
	public String toString() {
		return root.toString();
	}
}
