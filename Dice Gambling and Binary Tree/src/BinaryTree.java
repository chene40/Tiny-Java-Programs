/* 
    Use Java to implement a binary tree and implement the following classes and methods.
    * 1. Design the class for the binary tree
    * 2. addChild: adds a child to a node if the node does not have two children already.
    * 3. deleteChild: delete a child from a node if the child is a leaf node.
    * 4. In the main() method, use your code to build a tree A in the following example.
    * 5. Delete the node with key=20 in tree A, to get the Tree B in the following example.

* Example:
           50                            50
        /     \         delete(20)      /   \
      30      70       --------->    30     70
     /  \    /  \                     \    /  \
   20   40  60   80                   40  60   80
         (tree A)                       (Tree B)

 */

// Class containing left and right child of current node and key value
class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

// A Java program to introduce Binary Tree
class BinaryTree {
    // Root of Binary Tree
    Node root; // The root node of the binary tree.

    // Design the constructor of the class 'BinaryTree'

    // Initialize a binary tree with a root item
    BinaryTree (int rootItem){
        this.root = new Node(rootItem);
    }

    // addChild() adds 'child_node' as a child of the 'parent_node'.
    // if 'is_left' is true, then add the 'child_node' as a left child of the 'parent node'
    // if 'is_left' is false, then add the 'child_node' as a right child of the 'parent node'
    // if the target position of the child is already taken, then we should stop adding.
    public void addChild(Node parent_node, Node child_node, boolean is_left){
        /* place your code here */

        // if is_left is true, add child_node to left node only if the left node of the parent node is not taken
        if (is_left){
            if (parent_node.left == null) {
                parent_node.left = child_node;
            }
            // else if left node is taken (not null), then stop adding
        }

        // else, add child_node to right node only if the right node of the parent node is not taken
        else{
            if (parent_node.right == null) {
                parent_node.right = child_node;
            }
            // else if right node is taken (not null), then stop adding
        }
    }

    // deleteChild() deletes a child of teh 'parent_node'.
    // if 'is_left' is true, then delete the left child of the 'parent_node'
    // if 'is_left' is false, then delete the right child of the 'parent_node'
    // if the target child to delete is null, then the deletion should stop.
    // (20 marks)
    public void deleteChild(Node parent_node, boolean is_left) {
        /* place your code here */

        // if is_left is true, delete the left node of the parental node only if it is not empty (i,e not null)
        if (is_left){
            if (parent_node.left != null){
                parent_node.left = null;
            }
            // else if the left node is empty (null), then stop the deletion
        }

        // else (i.e. is_left == false), delete the right node of the parental node only if it is not empty (i,e not null)
        else{
            if (parent_node.right != null){
                parent_node.right = null;
            }
            // else if the right node is empty (null), then stop the deletion
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
        BinaryTree TreeA = new BinaryTree(50);

        // create all the nodes to be added to the binary tree
        // Left_Subtree represents the left and Right_Subtree represents right nodes of parent node
        Node Left_Subtree = new Node(30);
        Node Right_Subtree = new Node(70);

        // L_LeftNode1 and L_RightNode1 represents the left and right node of the Left_Subtree
        Node L_LeftNode1 = new Node(20);
        Node L_RightNode1 = new Node(40);

        // R_LeftNode1 and R_RightNode1 represents the left and right node of the Right_Subtree
        Node R_LeftNode1 = new Node(60);
        Node R_RightNode1 = new Node(80);

        // add the nodes into TreeA
        TreeA.addChild(TreeA.root, Left_Subtree, true);
        TreeA.addChild(TreeA.root, Right_Subtree, false);
        TreeA.addChild(TreeA.root.left, L_LeftNode1, true);     // Left_Subtree
        TreeA.addChild(TreeA.root.left, L_RightNode1, false);
        TreeA.addChild(TreeA.root.right, R_LeftNode1, true);    // Right_Subtree
        TreeA.addChild(TreeA.root.right, R_RightNode1, false);
        System.out.println("Tree A:");
        TreeA.printTree();
        System.out.println();

        /* place your code here to convert Tree A into Tree B (5 marks) */
        // TreeB is TreeA - the node with key=20
        TreeA.deleteChild(TreeA.root.left, true);
        System.out.println("Tree B:");
        TreeA.printTree();

    }
}
