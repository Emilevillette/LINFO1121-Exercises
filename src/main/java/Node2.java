

public class Node2 extends Node {

    Integer key;
    Double value;
    Node left, right;

    Node2(Integer k, Double v, Node l, Node r) {
        key = k;
        value = v;
        left = l;
        right = r;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public Node put(Integer k, Double v) {
        //TODO Implement the method
        if (this.isLeaf()) return new Node3(this.key, k, this.value, v, null, null, null);
        if (k.compareTo(key) < 0) {
            Node check_rec = this.left.put(k,v);
            Node2 tree_carry = Tree23.carry;
            if (tree_carry != null) {
                Node3 retval = new Node3(this.key, tree_carry.key, this.value, tree_carry.value, Tree23.temp, Tree23.temp2, this.right);
                Tree23.carry = null;
                return retval;
            } else {
                this.left = check_rec;
            }
        } else {
            Node check_rec = this.right.put(k,v);
            Node2 tree_carry = Tree23.carry;
            if (tree_carry != null) {
                Node3 retval = new Node3(this.key, tree_carry.key, this.value, tree_carry.value, this.left, Tree23.temp, Tree23.temp2);
                Tree23.carry = null;
                return retval;
            } else {
                this.right = check_rec;
            }
        }
        return this;
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
