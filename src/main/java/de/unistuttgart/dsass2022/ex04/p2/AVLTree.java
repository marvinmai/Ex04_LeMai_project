package de.unistuttgart.dsass2022.ex04.p2;


import java.util.Iterator;
import java.util.LinkedList;

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

	private boolean rebalance = false;
	private IAVLNode<T> insert(IAVLNode<T> n, T k) {
		IAVLNode<T> tmp;
		if (n.getValue().compareTo(k) == 0){ // schon vorhanden?
			rebalance = false; // globale Variable
			return n;
		} else if (n.getValue().compareTo(k) < 0) { // nach rechts gehen
			if (n.getRightChild() != null) {
				// rechts einfügen (rekursiv):
				n.setRightChild(insert(n.getRightChild(), k));
				// ggf. re-balancieren
				if (n != root && rebalance){
					switch (n.getBalance()) {
						case -1: // Re-balancieren
							if (n.getRightChild().getBalance() == -1) {
							// einfache Rotation nach links
							tmp = rotateLeft(n);
							tmp.getLeftChild().setBalance(0);
						} else {
							// doppelte Rotation (rechts, links)
							int b = n.getRightChild().getLeftChild().getBalance();
							n.setRightChild(rotateRight(n.getRightChild()));
							tmp = rotateLeft(n);
							tmp.getRightChild().setBalance((b == 1) ? -1 : 0);
							tmp.getLeftChild().setBalance((b == -1) ? 1 : 0);
						}
							tmp.setBalance(0);
							rebalance = false;
							return tmp;
						case 0:
							// Balance neu berechnen
							n.setBalance(-1);
							return n;
							// Rebalance bleibt true
						case 1:
							// Balance neu berechnen
							n.setBalance (0);
							rebalance = false;
							return n;
					}
				}
				else {
					return n;
				}
			} else {
				// neuen Blattknoten rechts erzeugen
				AVLNode<T> newNode = new AVLNode<T>(k);
				newNode.setLeftChild(null);
				newNode.setRightChild(null);
				n.setRightChild(newNode);
				n.setBalance(n.getBalance() - 1);
				rebalance = (n.getBalance() <= -1);
				return n;
			}
		} else {
			// links einfügen (symmetrisch)
			n.setBalance(n.getBalance() + 1);
			rebalance = (n.getBalance() >= 1);
		}
		return n;
	}

	private IAVLNode<T> rotateLeft(IAVLNode<T> n) {
		IAVLNode<T> tmp = n.getRightChild();
		n.setRightChild(n.getRightChild().getLeftChild());
		tmp.setLeftChild(n);
		return tmp;
	}
	private IAVLNode<T> rotateRight(IAVLNode<T> n) {
		IAVLNode<T> tmp = n.getLeftChild();
		n.setLeftChild(n.getLeftChild().getRightChild());
		tmp.setRightChild(n);
		return tmp;
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

	public void print() {
		print(root, 0);
	}

	private void print(IAVLNode<T> node, int space) {
		LinkedList<IAVLNode<T>> treeLevel = new LinkedList<>();
		treeLevel.add(node);
		LinkedList<IAVLNode<T>> temp = new LinkedList<>();
		int counter = 0;
		int height = node.getHeight() -1;
		double numberOfElements = (Math.pow(2 , (height + 1)) - 1);
		while (counter <= height) {
			IAVLNode<T> removed = treeLevel.removeFirst();
			if (temp.isEmpty()) {
				printSpace(numberOfElements / Math.pow(2 , counter + 1), removed);
			} else {
				printSpace(numberOfElements / Math.pow(2 , counter), removed);
			}
			if (removed == null) {
				temp.add(null);
				temp.add(null);
			} else {
				temp.add(removed.getLeftChild());
				temp.add(removed.getRightChild());
			}

			if (treeLevel.isEmpty()) {
				System.out.println("");
				System.out.println("");
				treeLevel = temp;
				temp = new LinkedList<>();
				counter++;
			}

		}
	}

	public void printSpace(double n, IAVLNode<T> removed){
		for(;n>0;n--) {
			System.out.print("\t");
		}
		if(removed == null){
			System.out.print(" ");
		}
		else {
			System.out.print(removed.getValue());
		}
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
				}
				T otherVal = otherIterator.next();
				T thisVal = thisIterator.next();
				if (thisVal != otherVal) {
					isEqual = false;
					System.out.println("Expected: " + thisVal + " Actual: " + otherVal);
					break;
				}
			}
		}
		return isEqual;
	}
}
