

public class Node3 extends Node {

    Integer k1, k2;  // k1 < k2
    Double v1, v2;
    Node left, middle, right;

    Node3(Integer k1, Integer k2, Double v1, Double v2, Node left, Node middle, Node right) {
        this.k1 = Math.min(k1, k2);
        this.k2 = Math.max(k1, k2);
        this.v1 = k1 < k2 ? v1 : v2;
        this.v2 = k1 < k2 ? v2 : v1;
        this.left = left;
        this.right = right;
        this.middle = middle;
    }

    /**
     * @param k : a key to compare
     * @return 0 if k1 < k < k2
     * -1 if k < k1
     * 1 if k > k2
     */
    public int compare(Integer k) {
        if (k.compareTo(k1) > 0 && k.compareTo(k2) < 0) return 0;
        if (k.compareTo(k1) < 0) return -1;
        return 1;
    }

    @Override
    public boolean isLeaf() {
        return left == null && middle == null && right == null;
    }

    @Override
    public Double search(Integer k) {
        if (k1.equals(k)) return v1;
        if (k2.equals(k)) return v2;

        if (k.compareTo(k1) > 0 && k.compareTo(k2) < 0) return Node.search(middle, k);
        else if (k.compareTo(k1) < 0) return Node.search(left, k);
        else return Node.search(right, k);
    }


    @Override
    public Node put(Integer k, Double v) {
        //TODO Implement the method
        if (this.isLeaf()) {
            if (this.compare(k) < 0) {
                Tree23.temp = new Node2(k, v, null, null);
                Tree23.temp2 = new Node2(this.k2, this.v2, null, null);
                Tree23.carry = new Node2(this.k1, this.v1, null, null);
            } else if (this.compare(k) > 0) {
                Tree23.temp = new Node2(this.k1, this.v1, null, null);
                Tree23.temp2 = new Node2(k, v, null, null);
                Tree23.carry = new Node2(this.k2, this.v2, null, null);
            } else {
                Tree23.temp = new Node2(this.k1, this.v1, null, null);
                Tree23.temp2 = new Node2(this.k2, this.v2, null, null);
                Tree23.carry = new Node2(k, v, null, null);
            }
            return null;
        }
        if (this.compare(k) < 0) {
            Node check_rec = this.left.put(k, v);
            if (Tree23.carry != null) {
                Tree23.temp = new Node2(Tree23.carry.key, Tree23.carry.value, Tree23.temp, Tree23.temp2);
                Tree23.temp2 = new Node2(this.k2, this.v2, this.middle, this.right);
                Tree23.carry = new Node2(this.k1, this.v1, null, null);
                return null;
            } else {
                this.left = check_rec;
            }
        } else if (this.compare(k) > 0) {
            Node check_rec = this.right.put(k, v);
            if (Tree23.carry != null) {
                Tree23.temp2 = new Node2(Tree23.carry.key, Tree23.carry.value, Tree23.temp, Tree23.temp2);
                Tree23.temp = new Node2(this.k1, this.v1, this.left, this.middle);
                Tree23.carry = new Node2(this.k2, this.v2, null, null);
                return null;
            } else {
                this.right = check_rec;
            }
        } else {
            Node check_rec = this.middle.put(k, v);
            if (Tree23.carry != null) {
                Tree23.temp = new Node2(this.k1, this.v1, Tree23.temp, Tree23.temp2);
                Tree23.temp2 = new Node2(this.k2, this.v2, this.middle, this.right);
                Tree23.carry = new Node2(Tree23.carry.key, Tree23.carry.value, null, null);
                return null;
            } else {
                this.middle = check_rec;
            }
        }
        return this;
    }

    @Override
    public int isBalanced() {
        if (isLeaf()) return 1;
        int m = middle.isBalanced();
        int l = left.isBalanced();
        int r = right.isBalanced();
        if (l < 0 || l != r || r != m) return -1;
        return l + 1;
    }

    @Override
    public boolean isBst(int lower, int upper) {
        if (isLeaf()) return true;
        if (k1 < lower || k2 < lower || k1 > upper || k2 > upper || k1 > k2) return false;
        return left.isBst(lower, k1) && middle.isBst(k1, k2) && right.isBst(k2, upper);
    }

}
