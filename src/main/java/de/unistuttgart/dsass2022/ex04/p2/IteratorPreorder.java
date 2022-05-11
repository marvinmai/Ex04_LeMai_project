package de.unistuttgart.dsass2022.ex04.p2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class IteratorPreorder<T extends Comparable<T>> implements Iterator<T> {

    IAVLNode<T> root;

    Queue<T> queue = new LinkedList<>();

    public IteratorPreorder(IAVLNode<T> root) {
        this.root = root;
        visit(root);
    }

    private void visit(IAVLNode<T> node) {
        if (node == null) return;
        queue.add(node.getValue());
        visit(node.getLeftChild());
        visit(node.getRightChild());
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
