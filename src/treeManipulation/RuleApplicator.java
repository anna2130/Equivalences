package treeManipulation;

import treeBuilder.*;

public class RuleApplicator {

	private void applyAndSimplification(FormationTree tree, Node node, Node child) {		
		if (node.isRoot())
			tree.setRoot(child);
		else {
			int parentKey = node.getKey() >> 1;
			int parentDepth = node.getDepth() - 1;
			Node parent = tree.findNode(parentKey, parentDepth);
			
			if (parent instanceof BinaryOperator) {
				if (node.getKey() % 2 == 0)
					((BinaryOperator) parent).setLeftChild(child);
				else
					((BinaryOperator) parent).setRightChild(child);
			} else
				((UnaryOperator) parent).setChild(child);				
		}
		
		child.reduceDepth();
	}
	
	public void applyLeftAndSimplification(FormationTree tree, int key, int depth) {
		Node node = tree.findNode(key, depth);
		assert node instanceof BinaryOperator;
		Node leftChild = ((BinaryOperator) node).getLeftChild();
		applyAndSimplification(tree, node, leftChild);
	}
	
	public void applyRightAndSimplification(FormationTree tree, int key, int depth) {
		Node node = tree.findNode(key, depth);
		assert node instanceof BinaryOperator;
		Node rightChild = ((BinaryOperator) node).getRightChild();
		applyAndSimplification(tree, node, rightChild);
	}
}
