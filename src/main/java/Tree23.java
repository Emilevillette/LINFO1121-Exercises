public class Tree23 {

    Node root;

    Tree23(){
        root = null;
    }

    public static Node2 carry;      // can be used to make a key go up in the tree
    public static Node temp, temp2; // can be used to save temporary sub trees

    public void put(Integer key, Double value){
        //TODO Implement the method
        carry = null;
        root = Node.put(root, key, value);
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
