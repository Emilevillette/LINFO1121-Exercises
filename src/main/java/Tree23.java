public class Tree23 {

    Node root;

    Tree23(){
        root = null;
    }

    public static Node2 carry;      // can be used to make a key go up in the tree
    public static Node temp, temp2; // can be used to save temporary sub trees

    public void put(Integer key, Double value){
        //TODO Implement the method
        if(this.root == null) {
            this.root = new Node2(key,value,null,null);
            return;
        }
        if (this.root.isLeaf()) {
            if (this.root instanceof Node2) {
                Node2 curr = (Node2) this.root;
                if (key < curr.key) {
                    curr.left.put(key, value);
                } else {
                    curr.right.put(key, value);
                }
            } else {
                Node3 curr = (Node3) this.root;
                if (key < curr.k1) {
                    curr.left.put(key, value);
                } else if (key > curr.k2) {
                    curr.right.put(key, value);
                } else {
                    curr.middle.put(key, value);
                }
            }
        } else {
            if(this.root instanceof Node2) {
                this.root = new Node3(this)
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
