import java.util.ArrayList;
import java.util.HashMap;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap() {

        if (size < 2) {
            return;
        }

        ArrayList<Node<E>> original = new ArrayList<>(size);
        Node<E> current = head;
        while (current != null) {
            original.add(current);
            current = current.getNext();
        }

        // sort from smallest to biggest
        ArrayList<Node<E>> sorted = new ArrayList<>(original);
        sorted.sort((a, b) -> a.getElement().compareTo(b.getElement()));

        HashMap<Node<E>, Node<E>> partner = new HashMap<>();
        int n = sorted.size();
        for (int i = 0; i < n; i++) {
            partner.put(sorted.get(i), sorted.get(n - 1 - i));
        }

        head = partner.get(original.get(0));
        Node<E> prev = head;
        for (int i = 1; i < n; i++) {
            Node<E> next = partner.get(original.get(i));
            prev.setNext(next);
            prev = next;
        }

        prev.setNext(null);
        tail = prev;
    }

}
