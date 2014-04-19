package treeManipulation;

import treeBuilder.*;

public class RuleApplicator {

	public FormationTree applyLeftSimplification(FormationTree tree, int key, int depth) {
		Node node = tree.findNode(key, depth);
		assert node instanceof BinaryOperator;
		System.out.println("Assertion passed");
		
		BinaryOperator binary = (BinaryOperator) node;
		System.out.println(binary);
		Node leftChild = binary.getLeftChild();
		System.out.println(leftChild);
		node.setValue(leftChild.getValue());
		System.out.println(node);
		
		return tree;
	}
	
}
