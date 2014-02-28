package treeBuilder;

public class UnaryOperator extends Node {
	
	private Node child;

	public UnaryOperator(int key, int depth, String value) {
		super(key, depth, value);
	}
	
	public void setChild(Node n) {
		child = n;
	}
	
	public Node getChild() {
		return child;
	}

	@Override
	public boolean hasChildren() {
		return child != null;
	}

	@Override
	public Node[] getChildren() {
		Node[] children = new Node[1];
		
		if (child != null)
			children[0] = child;
		
		return children;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(getKey());
		sb.append(": ");
		sb.append(getValue());
		sb.append(" (");
		
		if (child != null)
			sb.append(child);
		sb.append(") ");
		
		return sb.toString();
	}
}
