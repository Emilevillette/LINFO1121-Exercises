package strings;


import java.util.Arrays;
import java.util.PriorityQueue;
/**
 * This class is used to construct a Huffman trie from frequencies of letters (in unicode or ASCII).
 * As a reminder, in a Huffman trie nodes are weighted (see the `HuffmanNode` class) by
 * the frequencies of the character (if lead node) or the sum of the frequencies of its children
 * (if internal node).
 * For example, let us assume that we have the following letters with their associated frequencies:
 *  (t, 1), (m, 2), (z, 3), (a, 4), (g, 5)
 *
 *  The the following Huffman trie can be constructed
 *
 *
 *                      (_, 15)
 *                         |
 *         (_, 9) -------------------- (_, 6)
 *           |                           |
 *  (a, 4)------(g, 5)        (z, 3)----------(_, 3)
 *                                              |
 *                                     (t, 1)------(m, 2)
 *
 * In practice you are given an array of frequencies for each of the 256 ASCII code or 65536 unicode characters.
 * The goal is to construct the Huffman trie from this array of frequencies.
 */
public class Huffman {

    private static PriorityQueue<HuffmanNode> buildPQ(int [] freq) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
        for (int i = 0; i< freq.length;i++) {
            pq.add(new HuffmanNode(i, freq[i], null,null));
        }
        return pq;
    }

    /**
     * Constructs a Huffman trie for the frequencies of the characters given in arguments.
     * The character are implicitly defined by the `freq` array (ranging from 0 to freq.length -1)
     *
     * @param freq the frequencies of the characters
     */
    public static HuffmanNode buildTrie(int[] freq) {
        PriorityQueue<HuffmanNode> pq = buildPQ(freq);
        while(pq.size() > 1) {
            HuffmanNode R1 = pq.remove();
            HuffmanNode R2 = pq.remove();

            HuffmanNode to_add = new HuffmanNode(0, R1.getFrequency() + R2.getFrequency(), R1, R2);
            pq.add(to_add);
        }
        return pq.remove();
    }
}

class HuffmanNode implements Comparable<HuffmanNode> {

    private final int ch;
    private final int freq;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(int ch, int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode getLeft() {
        return this.left;
    }

    @SuppressWarnings("unchecked")
    public void setLeft(HuffmanNode node) {
        this.left = node;
    }

    public HuffmanNode getRight() {
        return this.right;
    }

    @SuppressWarnings("unchecked")
    public void setRight(HuffmanNode node) {
        this.right = node;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.freq - node.freq;
    }

    public int getFrequency() {
        return this.freq;
    }

    public int getChar() {
        return this.ch;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
