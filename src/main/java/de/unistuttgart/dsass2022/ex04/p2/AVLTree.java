package de.unistuttgart.dsass2022.ex04.p2;


import java.util.Iterator;

import static de.unistuttgart.dsass2022.ex04.p2.TreeTraversalType.INORDER;
import static de.unistuttgart.dsass2022.ex04.p2.TreeTraversalType.LEVELORDER;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

	private volatile IAVLNode<T> root;
	
	
	public AVLTree() {
		this.root = new AVLNode<T>();
	}

	public AVLTree(IAVLNode<T> node) {
		this.root = node;
	}

	@Override
	public void insert(T t) {
		this.root = this.insert(this.root, t);

	}

	private IAVLNode<T> insert(IAVLNode<T> node, T t) {
		if (node == null) {
			IAVLNode<T> newNode = new AVLNode<>();
			newNode.setValue(t);
			return newNode;
		}
		if (t.compareTo(node.getValue()) < 0) {
			node.setLeftChild(this.insert(node.getLeftChild(), t));
		} else if (t.compareTo(node.getValue()) > 0) {
			node.setRightChild(this.insert(node.getRightChild(), t));
		}
		return node;
	}

	@Override
	public IAVLNode<T> getRootNode() {
		return this.root;
	}

	public Iterator<T> iterator(TreeTraversalType traversalType) {
		Iterator<T> iterator = null;
		switch (traversalType) {
			case INORDER :
				iterator = new IteratorInorder<>(root);
				break;
			case PREORDER:
				iterator = new IteratorPreorder<>(root);
				break;
			case POSTORDER:
				iterator = new IteratorPostorder<>(root);
				break;
			case LEVELORDER:
				iterator = new IteratorLevelorder<>(root);
				break;
		}
		return iterator;
	}

	private int getNodeBalance(IAVLNode<T> node) {
		return getHeight(node.getLeftChild()) - getHeight(node.getRightChild());
	}

	private int getHeight(IAVLNode<T> node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = getHeight(node.getLeftChild());
		int rightHeight = getHeight(node.getRightChild());
		return Integer.max(leftHeight, rightHeight) + 1;
	}

	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other instanceof AVLTree) {
			AVLTree<T> otherTree = (AVLTree) other;
			Iterator<T> thisIterator = iterator(LEVELORDER);
			Iterator<T> otherIterator = otherTree.iterator(LEVELORDER);
			while (thisIterator.hasNext()) {
				if (!otherIterator.hasNext()) {
					isEqual = false;
					break;
				} else if (thisIterator.next() != otherIterator.next()) {
						isEqual = false;
						break;
				}
			}
		}
		return isEqual;
	}
}
