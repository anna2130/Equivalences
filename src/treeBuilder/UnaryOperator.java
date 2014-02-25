package treeBuilder;

import java.util.BitSet;

public class UnaryOperator extends Operator {
	
	private Operator child;

	public UnaryOperator(BitSet key, String value) {
		super(key, value);
	}
}
