package de.unistuttgart.dsass2022.ex04.p2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class IteratorPostorder<T extends Comparable<T>> implements Iterator<T> {

    IAVLNode<T> root;

    Queue<T> queue = new LinkedList<>();

    public IteratorPostorder(IAVLNode<T> root) {
        this.root = root;
        visit(root);
    }

    private void visit(IAVLNode<T> node) {
        if (node == null) return;
        visit(node.getLeftChild());
        visit(node.getRightChild());
        queue.add(node.getValue());
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
