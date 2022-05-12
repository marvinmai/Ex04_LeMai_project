package de.unistuttgart.dsass2022.ex04.p2;

public class AVLNode<T extends Comparable<T>> implements IAVLNode<T> {

	private IAVLNode<T> left;
	private IAVLNode<T> right;

	private T value;
	private int balance;

	public AVLNode() {
		this.left = null;
		this.right = null;
		this.value = null;
	}

	public AVLNode(T value) {
		this.left = null;
		this.right = null;
		this.value = value;
	}

	@Override
	public void setValue(T val) {
		this.value = val;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setLeftChild(IAVLNode<T> left) {
		this.left = left;
	}

	@Override
	public IAVLNode<T> getLeftChild() {
		return left;
	}
	
	@Override
	public void setRightChild(IAVLNode<T> right) {
		this.right = right;
	}

	@Override
	public IAVLNode<T> getRightChild() {
		return right;
	}

	@Override
	public void setBalance(int bal) {
		this.balance = bal;
	}

	@Override
	public int getBalance() {
		return getHeight(left) - getHeight(right);
	}

	@Override
	public int getHeight() {
		return getHeight(this);
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
	public boolean isBiggerThan(T other) {
		return value.compareTo(other) > 0;
	}

	@Override
	public boolean isSmallerThan(T other) {
		return value.compareTo(other) < 0;
	}
}
