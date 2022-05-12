package de.unistuttgart.dsass2022.ex04.p2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class IteratorLevelorder<T extends Comparable<T>> implements Iterator<T> {

    IAVLNode<T> root;

    Queue<T> queue = new LinkedList<>();

    public IteratorLevelorder(IAVLNode<T> root) {
        if (root == null) return;
        this.root = root;
        queue.add(root.getValue());
        visit(root);
    }

    private void visit(IAVLNode<T> node) {
        if (node == null) return;
        boolean hasLeft = node.getLeftChild() != null;
        boolean hasRight = node.getRightChild() != null;

        if (hasLeft) queue.add(node.getLeftChild().getValue());
        if (hasRight) queue.add(node.getRightChild().getValue());
        if (hasLeft) visit(node.getLeftChild());
        if (hasRight) visit(node.getRightChild());
    }

    @Override
    public boolean hasNext() {
        return queue.peek() != null;
    }

    @Override
    public T next() {
        return queue.poll();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        Iterator.super.forEachRemaining(action);
    }
}
