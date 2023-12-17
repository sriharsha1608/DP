package assignment01;

import java.util.NoSuchElementException;

public class DequeImpl1<E> implements Deque<E> {
    
    public final DLlist<E> adaptee;

    public DequeImpl1() {
        adaptee = new DLlist<>();
    }

    public void addLast(E e) {
        adaptee.insertList(e, adaptee.length());
    }

    public void addFirst(E e) {
        adaptee.insertList(e, 0);
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return adaptee.get(0);
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E element = adaptee.get(0);
        adaptee.delete(0);
        return element;
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return adaptee.get(adaptee.length() - 1);
    }

    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        E element = adaptee.get(adaptee.length() - 1);
        adaptee.delete(adaptee.length() - 1);
        return element;
    }

    public int size() {
        return adaptee.length();
    }

    public boolean isEmpty() {
        return adaptee.length() == 0;
    }
}
