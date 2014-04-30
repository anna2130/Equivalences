package treeManipulation;

import java.util.BitSet;

import treeBuilder.BinaryOperator;
import treeBuilder.FormationTree;
import treeBuilder.Node;

public class RuleSelector {

	/* The BitSet returns the rules applicable to a node in the order below:
	 * 1. Commutativity of &
	 * 2. Idempotence of & (Simplification)
	 * 3. Left Associativity of &
	 * 4. Right Associativity of &
	 */
	
	private int noRules = 4;
	
	public BitSet getApplicableRules(FormationTree tree, Node node) {
		BitSet bs = new BitSet(noRules);
		bs.set(0, isAndOperator(tree, node));
		bs.set(1, isAndOperator(tree, node) && isIdempotent(tree, (BinaryOperator) node));
		bs.set(2, isAndOperator(tree, node) && isLeftAssociative(tree, (BinaryOperator) node, "&"));
		bs.set(3, isAndOperator(tree, node) && isRightAssociative(tree, (BinaryOperator) node, "&"));
		
		return bs;
	}
	
	public String rulesToString(BitSet bs, Node node) {
		StringBuilder sb = new StringBuilder();
		
		if (bs.get(0))
			sb.append("Commutativity of ^ \n");
		if (bs.get(1))
			sb.append("Idempotence of ^ \n");
		if (bs.get(2))
			sb.append("Left Associativity of ^ \n");
		if (bs.get(3))
			sb.append("Right Associativity of ^ \n");
		
		return sb.toString();
	}
	
	private boolean isAndOperator(FormationTree tree, Node node) {
		return node.getValue().equals("&");
	}
	
	private boolean isOrOperator(FormationTree tree, Node node) {
		return node.getValue().equals("|");
	}
	
	private boolean isIdempotent(FormationTree tree, BinaryOperator node) {
		Node leftChild = node.getChildren()[0];
		Node rightChild = node.getChildren()[1];
			
		return tree.equalSubTrees(leftChild, rightChild);
	}
	
	private boolean isLeftAssociative(FormationTree tree, BinaryOperator node, String op) {
		return node.getValue().equals(op) && node.getChildren()[1].getValue().equals(op);
	}
	
	private boolean isRightAssociative(FormationTree tree, BinaryOperator node, String op) {
		return node.getValue().equals(op) && node.getChildren()[0].getValue().equals(op);
	}
}
