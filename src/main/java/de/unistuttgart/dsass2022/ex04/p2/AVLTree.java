package de.unistuttgart.dsass2022.ex04.p2;


import java.util.Iterator;

import static de.unistuttgart.dsass2022.ex04.p2.TreeTraversalType.INORDER;

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
		AVLNode<T> newNode = new AVLNode<>();
		
	}

	@Override
	public IAVLNode<T> getRootNode() {
		return this.root;
	}

	private boolean isBalanced() {

		return false;
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


	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other instanceof AVLTree) {
			AVLTree otherTree = (AVLTree) other;
			Iterator thisIterator = iterator(INORDER);
			Iterator otherIterator = otherTree.iterator(INORDER);
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
