package BinTree;

// A Java program to introduce Binary Tree
public class A2_BinaryTree {
    // Root of Binary Tree
    public Node root; // The root node of the binary tree.

    // Constructor of the binary tree.
    public A2_BinaryTree(int key_of_root) {
        root = new Node(key_of_root);
    }


    // addChild adds 'child_node' as a child of the 'parent_node'.
    // if 'is_left' is true, then add the 'child_node' as a left child of the 'parent node'
    // if 'is_left' is false, then add the 'child_node' as a right child of the 'parent node'
    // if the target position of the child is already taken, then we should stop adding.
    public void addChild(Node parent_node, Node child_node, boolean is_left){
        if (is_left) {
            if (parent_node.left == null) {
                parent_node.left = child_node;
            } else{
                System.out.println("Left child is not null, stop adding!");
            }
        } else{
            if (parent_node.right == null) {
                parent_node.right = child_node;
            } else{
                System.out.println("Right child is not null, stop adding!");
            }
        }
    }

    // deleteChild deletes a child of teh 'parent_node'.
    // if 'is_left' is true, then delete the left child of the 'parent_node'
    // if 'is_left' is false, then delete the right child of the 'parent_node'
    // if the target child to delete is null, then the deletion should stop.
    public void deleteChild(Node parent_node, boolean is_left) {
        if (is_left) {
            if (parent_node.left == null) {
                System.out.println("Left child is null, stop deletion!");
            } else{
                parent_node.left = null;
            }
        } else{
            if (parent_node.right == null) {
                System.out.println("Right child is null, stop deletion!");
            } else{
                parent_node.right = null;
            }
        }
    }

    public void printTree() {
        String prefix = "";
        printTreeCore(prefix, this.root, false);
    }

    private void printTreeCore(String prefix, Node n, boolean isLeft) {
        if (n != null) {
            System.out.println (prefix + (isLeft ? "L-- " : "R-- ") + n.key);
            printTreeCore(prefix + (isLeft ? "|   " : "    "), n.right, false);
            printTreeCore(prefix + (isLeft ? "|   " : "    "), n.left, true);
        }
    }

    public static void main(String[] args) {
        A2_BinaryTree tree = new A2_BinaryTree(50);
        tree.addChild(tree.root, new Node(30), true);
        tree.addChild(tree.root, new Node(70), false);
        tree.addChild(tree.root.left, new Node(20), true);
        tree.addChild(tree.root.left, new Node(40), false);
        tree.addChild(tree.root.right, new Node(60), true);
        tree.addChild(tree.root.right, new Node(80), false);
        tree.printTree();
        tree.deleteChild(tree.root.left, true);
        tree.printTree();
    }
}