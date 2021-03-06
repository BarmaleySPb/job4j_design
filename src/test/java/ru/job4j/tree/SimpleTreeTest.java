package ru.job4j.tree;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd2() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        assertTrue(tree.add(2, 4));
        assertFalse(tree.add(8, 7));
        assertTrue(tree.add(4, 5));
        assertFalse(tree.add(2, 3));
        assertFalse(tree.add(1, 2));

        assertThat(
                tree.findBy(6).isPresent(),
                is(false)
        );
        assertThat(
                tree.findBy(3).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindLastThen62() {
        Tree<String> tree = new SimpleTree<>("first");
        tree.add("first", "second");
        tree.add("first", "third");
        tree.add("second", "fourth");
        tree.add("fourth", "fifth");
        tree.add("fourth", "sixth");
        tree.add("second", "ixth");
        assertFalse(tree.add("eighth", "tenth"));
        assertTrue(tree.add("ixth", "tnth"));
        assertThat(
                tree.findBy("second").isPresent(),
                is(true)
        );
    }

    @Test
    public void isBinaryFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.isBinary());
    }

    @Test
    public void isBinaryTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(4, 5);
        tree.add(5, 6);
        assertTrue(tree.isBinary());
    }
}