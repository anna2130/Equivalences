package treeManipulation;

import java.util.BitSet;

import treeBuilder.BinaryOperator;
import treeBuilder.FormationTree;
import treeBuilder.Node;

public class RuleSelector {

	/* The BitSet returns the rules applicable to a node in the order below:
	 * 0. Commutativity of &
	 * 1. Idempotence of & 
	 * 2. Left Associativity of &
	 * 3. Right Associativity of &
	 * 4. Commutativity of |
	 * 5. Idempotence of |
	 * 6. Left Associativity of |
	 * 7. Right Associativity of |
	 * 
	 */
	
	private int noRules = 8;
	
	public BitSet getApplicableRules(FormationTree tree, Node node) {
		BitSet bs = new BitSet(noRules);
		bs.set(0, isAndOperator(tree, node));
		bs.set(1, isAndOperator(tree, node) && isIdempotent(tree, (BinaryOperator) node));
		bs.set(2, isAndOperator(tree, node) && isLeftAssociative(tree, (BinaryOperator) node, "&"));
		bs.set(3, isAndOperator(tree, node) && isRightAssociative(tree, (BinaryOperator) node, "&"));
		bs.set(4, isOrOperator(tree, node));
		bs.set(5, isOrOperator(tree, node) && isIdempotent(tree, (BinaryOperator) node));
		bs.set(6, isOrOperator(tree, node) && isLeftAssociative(tree, (BinaryOperator) node, "|"));
		bs.set(7, isOrOperator(tree, node) && isRightAssociative(tree, (BinaryOperator) node, "|"));
		
		return bs;
	}
	
	public String rulesToString(BitSet bs, FormationTree tree, Node node) {
		StringBuilder sb = new StringBuilder();
		RuleApplicator ra = new RuleApplicator();
		
		String s = node.toString();
		String nodeBefore = s.substring(1, s.length() - 1);
		
		if (bs.get(0))
			sb.append("Commutativity of ^ :		" + nodeBefore + " |- " + 
					ra.viewCommutativity((BinaryOperator) node) + "\n");
		if (bs.get(1))
			sb.append("Idempotence of ^ :		" + nodeBefore + " |- " + 
					ra.viewIdempotence((BinaryOperator) node) + "\n");
		if (bs.get(2))
			sb.append("Left Associativity of ^ :	" + nodeBefore + " |- " + 
					ra.viewLeftAssociativity((BinaryOperator) node) + "\n");
		if (bs.get(3))
			sb.append("Right Associativity of ^ :	" + nodeBefore + " |- " + 
					ra.viewRightAssociativity((BinaryOperator) node) + "\n");
		if (bs.get(4))
			sb.append("Commutativity of v:		" + nodeBefore + " |- " + 
					ra.viewCommutativity((BinaryOperator) node) + "\n");
		if (bs.get(5))
			sb.append("Idempotence of v:		" + nodeBefore + " |- " + 
					ra.viewIdempotence((BinaryOperator) node) + "\n");
		if (bs.get(6))
			sb.append("Left Associativity of v:	" + nodeBefore + " |- " + 
					ra.viewLeftAssociativity((BinaryOperator) node) + "\n");
		if (bs.get(7))
			sb.append("Right Associativity of v:	" + nodeBefore + " |- " + 
					ra.viewRightAssociativity((BinaryOperator) node) + "\n");
		
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
