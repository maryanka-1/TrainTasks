package org.example.LinkedListHandMade;
//
//В пиратской версии языка Java, которую скачал ваш коллега, забыли добавить класс LinkedList<T>!
// Вам нужно реализовать его самостоятельно.
//Часть кода уже представлена в классе HandMadeLinkedList<T>.


import org.w3c.dom.Node;

import java.util.NoSuchElementException;

public class HandMadeLinkedList<T> {

    public static class Node<E> {
        public E value;
        public Node<E> prev;
        public Node<E> next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public void addFirst(T element) {
        Node<T> oldHead = head;
        Node<T> newNode = new Node<>(element, null, oldHead);
        head = newNode;
        if (oldHead == null) {
            tail = newNode;
        } else {
            oldHead.prev = newNode;
        }
        size++;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    public void addLast(T element) {
        Node<T> newNode = new Node<>(element, tail, null);
        Node<T> oldTail = tail;
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = tail;
            tail.prev = oldTail;
        }
        size++;
    }

    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.value;
    }

    public int size() {
        return this.size;
    }

    public void add(int index, T element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index >= (size / 2)) {
            int diff = size - index;
            Node<T> oldLast = tail;
            for (int i = diff; diff > 0; diff--) {
                if (diff == 1) {
                    Node<T> newNode = new Node<>(element, oldLast.prev, oldLast);
                    oldLast.prev = newNode;
                    newNode.prev.next = newNode;
                    size++;
                }
                oldLast = oldLast.prev;
            }
        } else {
            Node<T> oldFirst = head;
            for (int i = 0; i < index; i++) {
                if (i == index - 1) {
                    Node<T> newNode = new Node<>(element, oldFirst, oldFirst.next);
                    oldFirst.next = newNode;
                    newNode.next.prev = newNode;
                    size++;
                }
                oldFirst = oldFirst.prev;
            }
        }
    }

    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> getNode = head;
        for (int i = 0; i < index; i++) {
            getNode = getNode.next;
        }
        return getNode.value;
    }

    public static void main(String[] args) {
        HandMadeLinkedList<Integer> integers = new HandMadeLinkedList<>();

        integers.addFirst(1);
        integers.addFirst(2);
        integers.addFirst(3);
        integers.addLast(4);
        integers.addLast(5);
        integers.addFirst(1);
        integers.add(5, 8);

        System.out.println(integers.getFirst());
        System.out.println(integers.size());
        System.out.println(integers.getLast());
        System.out.println(integers.size());
        System.out.println(integers.get(5));
    }
}