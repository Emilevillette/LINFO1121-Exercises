public class Tree23 {

    Node root;

    Tree23(Node2 root){
        this.root = root;
    }

    public static Node2 carry;      // can be used to make a key go up in the tree
    public static Node2 temp, temp2; // can be used to save temporary subtrees

    public static void put(Integer key, Double value, Node root_node){
        //TODO Implement the method
        if (this.root == null) {
            this.root = new Node2(key, value, null, null);
            return;
        }

        if (carry != null) {
            if (this.root instanceof Node2) {
                Node2 curr = (Node2) this.root;
                if (carry.key < curr.key) {
                    this.root = new Node3(carry.key, curr.key, carry.value, curr.value, temp, temp2, curr.right);
                    carry = null;
                    temp = null;
                    temp2 = null;
                } else {
                    this.root = new Node3(carry.key, curr.key, carry.value, curr.value, curr.left, temp, temp2);
                    carry = null;
                    temp = null;
                    temp2 = null;
                }
            } else {
                Node3 curr = (Node3) this.root;
                if (key < curr.k1) {
                    temp = new Node2(key, value, temp, temp2);
                    temp2 = new Node2(curr.k2, curr.v2, curr.middle, curr.right);
                    carry = new Node2(curr.k1, curr.v1, temp, temp2);
                } else if (key > curr.k2) {
                    temp2 = new Node2(key, value, temp, temp2);
                    temp = new Node2(curr.k1, curr.v1, curr.left, curr.middle);
                    carry = new Node2(curr.k2, curr.v2, temp, temp2);
                } else {
                    temp = new Node2(curr.k1, curr.v1, curr.left, temp);
                    temp2 = new Node2(curr.k2, curr.v2, temp2, curr.right);
                    carry = new Node2(key, value, temp, temp2);
                }
            }
        }
        if (this.root.isLeaf()) {
            if (this.root instanceof Node2) {
                Node2 curr = (Node2) this.root;
                if (key < curr.key) {
                    this.root = new Node3(key, curr.key, value, curr.value, null, null, null);
                } else {
                    this.root = new Node3(curr.key, key, curr.value, value, null, null, null);
                }
            } else {
                Node3 curr = (Node3) this.root;
                if (key < curr.k1) {
                    temp = new Node2(key, value, null, null);
                    temp2 = new Node2(curr.k2, curr.v2, null, null);
                    carry = new Node2(curr.k1, curr.v1, temp, temp2);

                } else if (key > curr.k2) {
                    temp = new Node2(curr.k1, curr.v2, null, null);
                    temp2 = new Node2(key, value, null, null);
                    carry = new Node2(curr.k2, curr.v2, temp, temp2);
                } else {
                    temp = new Node2(curr.k1, curr.v1, null, null);
                    temp = new Node2(curr.k2, curr.v2, null, null);
                    carry = new Node2(key, value, temp, temp2);
                }
            }
        } else {
            if(this.root instanceof Node2) {
                Node2 curr = (Node2) this.root;
                if (key < curr.key) {

                }
            }
        }

    }

    public Double search(Integer k){
        return Node.search(root, k);
    }

    public boolean isBalanced(){
        if (root == null) return true;
        return root.isBalanced() >= 0;
    }

    public boolean isBst(){
        if (root == null) return true;
        return root.isBst(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        Tree23 t = new Tree23();
        for (int i = 0; i < 10; i++) {
            t.put(i, (double) i);
        }
        System.out.println(t.search(4));
        System.out.println("Ok");
    }
}
