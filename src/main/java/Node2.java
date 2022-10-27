

public class Node2 extends Node {

    Integer key;
    Double value;
    Node left, right;

    Node2(Integer k, Double v, Node l, Node r){
        key = k; value = v; left = l; right = r;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public Node put(Integer k, Double v){
        //TODO Implement the method
        if (k < this.key) {
            if (this.left == null) {
                this.left = new Node2(k, v, null, null);
            } else {
                this.left.put(k, v);
            }
        } else if(k > this.key) {
            if(this.right == null) {
                this.right = new Node2(k, v, null, null);
            } else {
                this.right.put(k,v);
            }
        } else {
            throw new IllegalArgumentException("key already exists");
        }
        return null;
    }

    @Override
    public int isBalanced() {
        if (isLeaf()) return 1;
        int l = left.isBalanced();
        int r = right.isBalanced();
        if (l == -1 || r != l) return -1;
        return l + 1;
    }

    @Override
    public boolean isBst(int lower, int upper) {
        if (isLeaf()) return true;
        if (key < lower || key > upper) return false;
        return left.isBst(lower, key) && right.isBst(key, upper);
    }

    @Override
    public Double search(Integer k) {
        if (key.equals(k)) return value;
        if (k.compareTo(key) < 0) return Node.search(left, k);
        else return Node.search(right, k);
    }
}
