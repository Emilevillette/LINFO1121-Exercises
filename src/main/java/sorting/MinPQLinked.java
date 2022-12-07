package sorting;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Author: Pierre Schaus
 * <p>
 * Generic min priority queue implementation with a linked-data-structure
 * The heap-tree is internally represented with Node's that store the keys
 * Each node
 * <p>
 * - has a pointer its children and parent:
 * - a null pointer indicates the absence of child/parent
 * - a key (the values stored in the heap)
 * - a size that is equal to the number of descendant nodes
 * <p>
 * A heap is an essentially an almost complete tree
 * that satisfies the heap property:
 * for any given node the key is less than the ones in the descendant nodes
 * Here is an example of a heap:
 * <p>
 *          3
 *      /      \
 *     5         4
 *   /  \      /  \
 *  6    7    8    5
 * / \  / \  /
 *7  8 8  9 9
 * <p>
 * <p>
 * <p>
 * The insert and min methods are already implemented maintaining the heap property
 * Your task is to implement the delMin() method.
 * This method should execute in O(log(n)) where n is the number of elements in the priority queue
 * <p>
 * You can add any method that you want but leave the instance variable
 * and public API untouched since it used by the tests
 * <p>
 * Hint: use the unit tests to debug your code, you might get some inspiration from the insert method
 *
 * @param <Key> the generic type of key on this priority queue
 */
public class MinPQLinked<Key> {

    // class used to implement the Nodes in the heap
    public class Node {
        public Key value;
        public Node left;
        public Node right;
        public Node parent;
        public int size;

        public Node(Node parent) {
            this.parent = parent;
            this.size = 1;
        }
    }

    public Node root; // number of items on priority queue
    public Comparator<Key> comparator;  // comparator used to compare the keys


    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator the order in which to compare the keys
     */
    public MinPQLinked(Comparator<Key> comparator) {
        this.comparator = comparator;
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        if (root == null) {
            return 0;
        } else {
            return root.size;
        }
    }

    /**
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return root.value;
    }


    /**
     * Adds a new key to this priority queue.
     *
     * @param x the key to add to this priority queue
     */
    public void insert(Key x) {
        if (root == null) {
            root = new Node(null);
            root.value = x;
        } else {
            Node n = createNodeInLastLayer(); // create the new node in last layer
            n.value = x; // store x in this node
            swim(n); // restore the heap property from this leaf node to the root
        }
    }


    // maintains the heap invariant
    private void swim(Node n) {
        while (n != root && greater(n.parent, n)) {
            exch(n, n.parent);
            n = n.parent;
        }
    }

    // Creates a new empty node in last layer (ensuring it stay essentially an almost complete tree)
    // and returns the node
    private Node createNodeInLastLayer() {
        Node current = root;
        current.size++;
        while (current.left != null && current.right != null) {
            // both left and right are not null
            if (isPowerOfTwo(current.left.size + 1) && current.right.size < current.left.size) {
                // left is complete and there is fewer in right subtree
                current = current.right; // follow right direction
            } else {
                current = current.left; // follow left direction
            }
            current.size++; // augment size sisnce this node will have new descendant
        }
        // hook up the new node
        if (current.left == null) {
            current.left = new Node(current);
            return current.left;
        } else {
            assert (current.right == null);
            current.right = new Node(current);
            return current.right;
        }
    }


    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMin() {
        // TODO (unfold the comment on top of the file to read the instructions)
        if (this.isEmpty()) throw new NoSuchElementException("PQ is empty.");
        Key retval = this.root.value;
        Node greatest = get_magic_node(this.root);
        this.root.value = greatest.value;
        sink(this.root);
        return retval;
    }
    public void sink(Node n) {
        if (n.right == null && n.left == null) {
            return;
        } else if (n.left == null) {
            if (greater(n, n.right)) {
                exch(n.right, n);
                sink(n.right);
                n.size--;
            }
        } else if (n.right == null) {
            if (greater(n, n.left)) {
                exch(n.left, n);
                sink(n.left);
                n.size--;
            }
        } else {
            Node smallest;
            if(greater(n.left,n.right)) {
                smallest = n.right;
            } else {
                smallest = n.left;
            }

            if(greater(n, smallest)) {
                exch(n,smallest);
                sink(smallest);
                n.size--;
            }
        }
    }

    public Node get_greater(Node n) {
        if (n.left == null && n.right == null) {
            return n;
        } else if (n.left == null) {
            return get_greater(n.right);
        } else if (n.right == null) {
            return get_greater(n.left);
        }
        if (greater(n.left, n.right)) {
            return get_greater(n.left);
        } else {
            return get_greater(n.right);
        }
    }

    public Node get_magic_node(Node n) {
        Node current = n;
        String binrepr = Integer.toBinaryString(n.size - (int) Math.ceil(Math.pow(2, (Math.log(n.size + 1) - 1))) - 1);
        System.out.println(binrepr);
        for (int i = 1; i < binrepr.length(); i++) {
            System.out.println(binrepr.charAt(i));
            if (binrepr.charAt(i) == '1') {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return current;
    }


    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/


    // check if x = 2^n for some x>0
    private boolean isPowerOfTwo(int x) {
        return (x & (x - 1)) == 0;
    }

    // Check if node i > j
    private boolean greater(Node i, Node j) {
        return comparator.compare(i.value, j.value) > 0;
    }

    // exchange the values in two nodes
    private void exch(Node i, Node j) {
        Key swap = i.value;
        i.value = j.value;
        j.value = swap;
    }


}