package treeBuilder;

public class FormationTree {

	private Node root;
	
	public FormationTree() {
		this.root = null;
	}
	
	public FormationTree(Node root) {
		this.root = root;
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
			int k = key >> depth - i - 1;
			n = n.getChildren()[k];
		}
		
		return n;
	}
	
//	public void replaceNode(Node before, Node after) {
//		if (after instanceof BinaryOperator) {
//			BinaryOperator binary = new BinaryOperator(before.getKey(), 
//					before.getDepth(), after.getValue());
//			binary.setLeftChild(((BinaryOperator) after).getLeftChild());
//		} else {
//			
//		}
//	}
	
	@Override
	public String toString() {
		return root.toString();
	}
}
