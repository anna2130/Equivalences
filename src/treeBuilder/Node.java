package treeBuilder;

import java.util.BitSet;

public class Node {

	private BitSet key;
	private String value;
	
	public Node(BitSet key, String value) {
		this.setKey(key);
		this.setValue(value);
	}

	public BitSet getKey() {
		return key;
	}

	public void setKey(BitSet key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
