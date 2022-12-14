package fundamentals;

import java.util.EmptyStackException;

/**
 * Author: Pierre Schaus
 * <p>
 * You have to implement the interface using
 * - a simple linkedList as internal structure
 * - a growing array as internal structure
 */
public interface Stack<E> {

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack
     */
    public boolean empty();

    /**
     * Returns the first element of the stack, without removing it from the stack
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E peek() throws EmptyStackException;

    /**
     * Remove the first element of the stack and returns it
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E pop() throws EmptyStackException;

    /**
     * Adds an element to the stack
     *
     * @param item the item to add
     */
    public void push(E item);

}

/**
 * Implement the Stack interface above using a simple linked list.
 * You should have at least one constructor withtout argument.
 * You are not allowed to use classes from java.util
 *
 * @param <E>
 */
class LinkedStack<E> implements Stack<E> {

    private Node<E> top;        // the node on the top of the stack
    private int size;        // size of the stack

    // helper linked list class
    private class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> previous;

        public Node(E element, Node<E> next, Node<E> previous) {
            this.item = element;
            this.next = next;
            this.previous = previous;
        }
    }

    @Override
    public boolean empty() {
        // TODO Implement empty method
        return this.size == 0;
    }

    @Override
    public E peek() throws EmptyStackException {
        if (this.empty()) throw new EmptyStackException();
        // TODO Implement peek method
        return this.top.item;
    }

    @Override
    public E pop() throws EmptyStackException {
        if (this.empty()) throw new EmptyStackException();
        // TODO Implement pop method
        this.size--;
        E temp = this.top.item;
        this.top = this.top.previous;
        if (this.top != null) {
            this.top.next = null;
        }
        return temp;
    }

    @Override
    public void push(E item) {
        // TODO Implement push method
        this.size++;
        if (this.top == null) {
            this.top = new Node<>(item, null, null);
            return;
        }
        this.top.next = new Node<>(item, null, this.top);
        this.top = this.top.next;
    }
}


/**
 * Implement the Stack interface above using an array as internal representation
 * The capacity of the array should double when the number of elements exceed its length.
 * You should have at least one constructor without argument.
 * You are not allowed to use classes from java.util
 *
 * @param <E>
 */
class ArrayStack<E> implements Stack<E> {

    private E[] array;        // array storing the elements on the stack
    private int size;        // size of the stack

    public ArrayStack() {
        array = (E[]) new Object[10];
    }

    @Override
    public boolean empty() {
        // TODO Implement empty method
        return this.size == 0;
    }

    @Override
    public E peek() throws EmptyStackException {
        if (this.empty()) throw new EmptyStackException();
        // TODO Implement peek method
        return this.array[this.size - 1];
    }

    @Override
    public E pop() throws EmptyStackException {
        if (this.empty()) throw new EmptyStackException();
        // TODO Implement pop method
        this.size--;
        E temp = this.array[this.size];
        this.array[this.size] = null;
        return temp;
    }

    @Override
    public void push(E item) {
        // TODO Implement push method

        if (this.size >= this.array.length) {
            E[] temp = (E[]) new Object[this.array.length * 2];
            System.arraycopy(this.array, 0, temp, 0, this.array.length);
            this.array = temp;
        }
        this.array[this.size] = item;
        this.size++;
    }
}

