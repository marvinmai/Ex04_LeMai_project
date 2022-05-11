package de.unistuttgart.dsass2022.ex04.p2;

import static org.junit.Assert.*;
import org.junit.Test;

public class AVLNodeTest {

    @Test
    public void testBalance() {
        AVLNode<Integer> node1 = new AVLNode<>();
        AVLNode<Integer> node2 = new AVLNode<>();
        AVLNode<Integer> node3 = new AVLNode<>();
        node2.setRightChild(node3);
        node1.setLeftChild(node2);
        assertEquals(2, node1.getBalance());

        AVLNode<Integer> node4 = new AVLNode<>();
        node1.setRightChild(node4);
        assertEquals(1, node1.getBalance());

        AVLNode<Integer> node5 = new AVLNode<>();
        node4.setRightChild(node5);
        assertEquals(0, node1.getBalance());

        AVLNode<Integer> node6 = new AVLNode<>();
        node5.setRightChild(node6);
        assertEquals(-1, node1.getBalance());

        AVLNode<Integer> node7 = new AVLNode<>();
        node6.setRightChild(node7);
        assertEquals(-2, node1.getBalance());
    }
}
