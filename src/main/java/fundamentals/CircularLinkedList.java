package fundamentals;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Author Pierre Schaus
 * <p>
 * We are interested in the implementation of a circular simply linked list,
 * i.e. a list for which the last position of the list refers, as the next position,
 * to the first position of the list.
 * <p>
 * The addition of a new element (enqueue method) is done at the end of the list and
 * the removal (remove method) is done at a particular index of the list.
 * <p>
 * A (single) reference to the end of the list (last) is necessary to perform all operations on this queue.
 * <p>
 * You are therefore asked to implement this circular simply linked list by completing the class see (TODO's)
 * Most important methods are:
 * <p>
 * - the enqueue to add an element;
 * - the remove method [The exception IndexOutOfBoundsException is thrown when the index value is not between 0 and size()-1];
 * - the iterator (ListIterator) used to browse the list in FIFO.
 *
 * @param <Item>
 */
public class CircularLinkedList<Item> implements Iterable<Item> {

    private long nOp = 0; // count the number of operations
    private int n;          // size of the stack
    private Node last;   // trailer of the list

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public CircularLinkedList() {
        // TODO initialize instance variables
        this.n = 0;
        this.last = null;
    }

    public boolean isEmpty() {
        return this.last == null;
    }

    public int size() {
        return this.n;
    }

    private long nOp() {
        return nOp;
    }


    /**
     * Append an item at the end of the list
     *
     * @param item the item to append
     */
    public void enqueue(Item item) {
        if (this.isEmpty()) {
            this.last = new Node();
            this.last.item = item;
            this.last.next = this.last;
            this.n++;
            this.nOp++;
            return;
        }
        Node temp = new Node();
        temp.item = item;
        temp.next = this.last.next;
        this.last.next = temp;
        this.last = temp;
        this.n++;
        this.nOp++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size() - 1) {
            throw new IndexOutOfBoundsException("invalid size");
        }
        Node current;
        current = this.last.next;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Item temp = current.next.item;
        current.next = current.next.next;
        this.nOp++;
        this.n--;
        return temp;
    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     *
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator(this);
    }

    /**
     * Implementation of an iterator that iterates through the items in FIFO order.
     * The iterator should implement a fail-fast strategy, that is ConcurrentModificationException
     * is thrown whenever the list is modified while iterating on it.
     * This can be achieved by counting the number of operations (nOp) in the list and
     * updating it everytime a method modifying the list is called.
     * Whenever it gets the next value (i.e. using next() method), and if it finds that the
     * nOp has been modified after this iterator has been created, it throws ConcurrentModificationException.
     */
    private class ListIterator implements Iterator<Item> {

        // TODO You probably need a constructor here and some instance variables
        Node current;
        long initnOp;
        CircularLinkedList<Item> cll;
        int pos;
        private ListIterator(CircularLinkedList<Item> list) {
            if (list.last != null) {
                current = list.last.next; //position in the beginning
            }
            this.initnOp = list.nOp();
            this.cll = list;
            this.pos = 0;
        }

        @Override
        public boolean hasNext() {
            this.checkConcurrentModification();
            return this.pos <= cll.size() - 1;
        }

        @Override
        public Item next() {
            this.checkConcurrentModification();
            Node temp = this.current;
            this.current = this.current.next;
            this.pos++;
            return temp.item;
        }

        private void checkConcurrentModification() throws ConcurrentModificationException {
            if (this.initnOp != this.cll.nOp()) {
                throw new ConcurrentModificationException("CircularLinkedList was modified during the iteration.");
            }
        }

    }

}