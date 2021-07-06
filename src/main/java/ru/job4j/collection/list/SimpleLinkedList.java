package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size = 0;
    private int modCount = 0;

    public SimpleLinkedList() {
        lastNode = new Node<E>(firstNode, null, null);
        firstNode = new Node<E>(null, null, lastNode);
    }

    @Override
    public void add(E value) {
        Node<E> newNode = lastNode;
        newNode.setValue(value);
        lastNode = new Node<E>(newNode, null, null);
        newNode.setNextNode(lastNode);
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> targetNode = firstNode.getNextNode();
        for (int i = 0; i < index; i++) {
            targetNode = getNextElement(targetNode);
        }
        return targetNode.getValue();
    }

    private Node<E> getNextElement(Node<E> currentNode) {
        return currentNode.getNextNode();
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;
        private Node<E> previousNode;

        private Node(Node<E> previousNode, E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
            this.previousNode = previousNode;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public Node<E> getPreviousNode() {
            return previousNode;
        }

        public void setPreviousNode(Node<E> previousNode) {
            this.previousNode = previousNode;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> target = firstNode.getNextNode();
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return target.getNextNode() != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                target = target.getNextNode();
                return target.previousNode.getValue();
            }
        };
    }
}