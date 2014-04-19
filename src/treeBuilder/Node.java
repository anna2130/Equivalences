package treeBuilder;

public abstract class Node {

	private int key;
	private int depth;
	private String value;
	
	public Node(int key, int depth, String value) {
		this.setKey(key);
		this.setDepth(depth);
		this.setValue(value);
	}
	
	public int getKey() {
		return key;
	}

	public void setKey(int currentIndex) {
		this.key = currentIndex;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public boolean hasChildren() {
		return false;
	}

	public Node[] getChildren() {
		return null;
	}
	
	public void reduceDepth() {
		setDepth(getDepth() - 1);
		
		if (hasChildren()) {
			Node[] children = getChildren();
			for (int i = 0; i < children.length; ++i) {
				children[i].reduceDepth();
			}
		}
	}
	
	public boolean isRoot() {
		return key == 0 && depth == 0;
	}
	
	@Override
	public abstract String toString();

}
