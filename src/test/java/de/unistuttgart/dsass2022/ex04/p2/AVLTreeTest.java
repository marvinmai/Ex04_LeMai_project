package de.unistuttgart.dsass2022.ex04.p2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import de.unistuttgart.dsass2022.ex04.p2.AVLNode;
import de.unistuttgart.dsass2022.ex04.p2.AVLTree;
import de.unistuttgart.dsass2022.ex04.p2.IAVLNode;

public class AVLTreeTest {

    /**
     * Builds tree from Exercise 04 - 1).
     * @return tree AVL1.
     */
    private AVLTree<Integer> getAVL1() {
        AVLNode<Integer> node21 = new AVLNode<>(21);
        AVLNode<Integer> node22 = new AVLNode<>(22);
        AVLNode<Integer> node34 = new AVLNode<>(34);
        AVLNode<Integer> node35 = new AVLNode<>(35);
        AVLNode<Integer> node37 = new AVLNode<>(37);
        AVLNode<Integer> node55 = new AVLNode<>(55);
        AVLNode<Integer> node60 = new AVLNode<>(60);
        AVLNode<Integer> node70 = new AVLNode<>(70);
        AVLNode<Integer> node77 = new AVLNode<>(77);
        AVLNode<Integer> node80 = new AVLNode<>(80);

        node55.setLeftChild(node34);
        node34.setLeftChild(node22);
        node22.setLeftChild(node21);
        node34.setRightChild(node37);
        node37.setLeftChild(node35);

        node55.setRightChild(node70);
        node70.setLeftChild(node60);
        node70.setRightChild(node77);
        node77.setRightChild(node80);

        return new AVLTree<>(node55);
    }

    /**
     * Builds result tree from Exercise 04 - 1b).
     * @return tree AVL2.
     */
    private AVLTree<Integer> getAVL2() {
        AVLNode<Integer> node21 = new AVLNode<>(21);
        AVLNode<Integer> node22 = new AVLNode<>(22);
        AVLNode<Integer> node34 = new AVLNode<>(34);
        AVLNode<Integer> node35 = new AVLNode<>(35);
        AVLNode<Integer> node37 = new AVLNode<>(37);
        AVLNode<Integer> node55 = new AVLNode<>(55);
        AVLNode<Integer> node60 = new AVLNode<>(60);
        AVLNode<Integer> node70 = new AVLNode<>(70);
        AVLNode<Integer> node77 = new AVLNode<>(77);
        AVLNode<Integer> node80 = new AVLNode<>(80);

        AVLNode<Integer> node64 = new AVLNode<>(64);

        node55.setLeftChild(node34);
        node34.setLeftChild(node22);
        node22.setLeftChild(node21);
        node34.setRightChild(node37);
        node37.setLeftChild(node35);

        node55.setRightChild(node70);
        node70.setLeftChild(node60);
        node60.setRightChild(node64);
        node70.setRightChild(node77);
        node77.setRightChild(node80);

        return new AVLTree<>(node55);
    }

    @Test
    public void testTreeEquality() {
        AVLTree<Integer> tree1 = getAVL1();
        AVLTree<Integer> tree2 = getAVL2();
        assertEquals(tree1, tree1);
        assertNotEquals(tree1, tree2);
    }

    /**
     * Exercise 1b: Tests insertion of 64 into AVL1, results in AVL2;
     */
    @Test
    public void insertTestWithoutRebalancing() {
        AVLTree<Integer> treeAVL1 = getAVL1();
        AVLTree<Integer> treeAVL2 = getAVL2();

        treeAVL1.insert(64);
        assertEquals(treeAVL2, treeAVL1);
    }

    /**
     * Builds result tree from Exercise 04 - 1b) after insertion of 20.
     * @return tree AVL3.
     */
    private AVLTree<Integer> getAVL3() {
        AVLNode<Integer> node21 = new AVLNode<>(21);
        AVLNode<Integer> node22 = new AVLNode<>(22);
        AVLNode<Integer> node34 = new AVLNode<>(34);
        AVLNode<Integer> node35 = new AVLNode<>(35);
        AVLNode<Integer> node37 = new AVLNode<>(37);
        AVLNode<Integer> node55 = new AVLNode<>(55);
        AVLNode<Integer> node60 = new AVLNode<>(60);
        AVLNode<Integer> node70 = new AVLNode<>(70);
        AVLNode<Integer> node77 = new AVLNode<>(77);
        AVLNode<Integer> node80 = new AVLNode<>(80);
        AVLNode<Integer> node64 = new AVLNode<>(64);

        AVLNode<Integer> node20 = new AVLNode<>(20);

        node55.setLeftChild(node34);
        node34.setLeftChild(node21);
        node21.setLeftChild(node20);
        node21.setRightChild(node22);
        node34.setRightChild(node37);
        node37.setLeftChild(node35);

        node55.setRightChild(node70);
        node70.setLeftChild(node60);
        node60.setRightChild(node64);
        node70.setRightChild(node77);
        node77.setRightChild(node80);

        return new AVLTree<>(node55);
    }

    /**
     * Exercise 1b: Tests insertion of 20 into AVL2, results in AVL3.
     */
    @Test
    public void insertTestWithRebalancing_insert20() {
        AVLTree<Integer> treeAVL2 = getAVL2();
        AVLTree<Integer> treeAVL3 = getAVL3();

        treeAVL2.insert(20);
        assertEquals(treeAVL3, treeAVL2);
    }

}
