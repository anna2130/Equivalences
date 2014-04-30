package treeManipulation;

import treeBuilder.*;

public class RuleApplicator {
	
	private void relabelTree(FormationTree tree, Node node) {
		Node[] children = node.getChildren();
		int key = node.getKey();
		int depth = node.getDepth();

		if (children != null) {
			if (children.length > 0) {
				children[0].setKey(key << 1);
				children[0].setDepth(depth + 1);
				
				if (children[0].hasChildren())
					relabelTree(tree, children[0]);
			}
			if (children.length > 1) {
				children[1].setKey((key << 1) + 1);
				children[1].setDepth(depth + 1);
				
				if (children[1].hasChildren())
					relabelTree(tree, children[1]);
			}
		}
	}
	
	public Node applyCommutativity(FormationTree tree, BinaryOperator node) {
		Node leftChild = node.getLeftChild();
		node.setLeftChild(node.getRightChild());
		node.setRightChild(leftChild);
		
		relabelTree(tree, node);
		return node;
	}
	
	public Node applyAndSimplification(FormationTree tree, BinaryOperator node) {
		Node child = node.getLeftChild();
		
		Node parent = child;
		if (node.isRoot())
			tree.setRoot(child);
		else {
			int parentKey = node.getKey() >> 1;
			int parentDepth = node.getDepth() - 1;
			parent = tree.findNode(parentKey, parentDepth);
			
			if (parent instanceof BinaryOperator) {
				if (node.getKey() % 2 == 0)
					((BinaryOperator) parent).setLeftChild(child);
				else
					((BinaryOperator) parent).setRightChild(child);
			} else
				((UnaryOperator) parent).setChild(child);				
		}
		
		relabelTree(tree, parent);
		return child;
	}
	
//	public void applyAndLeftSimplification(FormationTree tree, int key, int depth) {
//		BinaryOperator node = (BinaryOperator) tree.findNode(key, depth);
//		Node leftChild = node.getLeftChild();
//		applyAndSimplification(tree, node, leftChild);
//	}
//	
//	public void applyAndRightSimplification(FormationTree tree, int key, int depth) {
//		BinaryOperator node = (BinaryOperator) tree.findNode(key, depth);
//		Node rightChild = node.getRightChild();
//		applyAndSimplification(tree, node, rightChild);
//	}

	public Node applyAndRightAssociativity(FormationTree tree, BinaryOperator node) {
		return applyRightRotation(tree, node);
	}

	public Node applyAndLeftAssociativity(FormationTree tree, BinaryOperator node) {
		return applyLeftRotation(tree, node);
	}
	
	public Node applyRightRotation(FormationTree tree, BinaryOperator node) {
		BinaryOperator leftChild = (BinaryOperator) node.getLeftChild();

		Node parent = leftChild;
		Node[] grandChildren = leftChild.getChildren();
		if (grandChildren != null && grandChildren.length == 2)
			node.setLeftChild(grandChildren[1]);
		
		if (node.isRoot())
			tree.setRoot(leftChild);
		else {
			parent = tree.findParent(node.getKey(), node.getDepth());
			System.out.println("parent: " + parent);
			
			if (parent instanceof UnaryOperator)
				((UnaryOperator) parent).setChild(leftChild);
			else {
				// Check if left or right child
				if (node.getKey() >> (node.getDepth() - 1) == 0) 
					((BinaryOperator) parent).setLeftChild(leftChild);
				else 
					((BinaryOperator) parent).setRightChild(leftChild);
			}
		}
		leftChild.setRightChild(node);
		relabelTree(tree, parent);
		return leftChild;
	}
	
	public Node applyLeftRotation(FormationTree tree, BinaryOperator node) {
		BinaryOperator rightChild = (BinaryOperator) node.getRightChild();

		Node parent = rightChild;
		Node[] grandChildren = rightChild.getChildren();
		if (grandChildren != null && grandChildren.length == 2)
			node.setRightChild(grandChildren[0]);
		
		if (node.isRoot())
			tree.setRoot(rightChild);
		else {
			parent = tree.findParent(node.getKey(), node.getDepth());
			
			if (parent instanceof UnaryOperator)
				((UnaryOperator) parent).setChild(rightChild);
			else {
				// Check if left or right child
				if (node.getKey() >> (node.getDepth() - 1) == 0) 
					((BinaryOperator) parent).setLeftChild(rightChild);
				else 
					((BinaryOperator) parent).setRightChild(rightChild);
			}
		}
		rightChild.setLeftChild(node);
		relabelTree(tree, parent);
		return rightChild;
	}
}






