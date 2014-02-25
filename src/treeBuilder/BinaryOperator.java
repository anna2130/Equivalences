package treeBuilder;

import java.util.BitSet;

public class BinaryOperator extends Operator {

	private Operator leftChild;
	private Operator rightChild;
	
	public BinaryOperator(BitSet key, String value) {
		super(key, value);
	}
	
}
