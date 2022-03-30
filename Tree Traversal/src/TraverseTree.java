/* 
    * Inherit from BinTree.BinaryTree to implement a subclass TraverseTree that implements
    * the following tree traversal subroutines
    * 1. Implement "void printPostorder(Node node)" to realize Postorder traversal. (20 marks)
    * 2. Implement "void printInorder(Node node)" to realize Inorder traversal. (20 marks)
    * 3. Implement "void printPreorder(Node node)" to realize Preorder traversal. (20 marks)
 */

import BinTree.*;

public class TraverseTree extends BinTree.BinaryTree {

    TraverseTree(int key_of_root) {
        super(key_of_root);
    }

    // Given a binary tree, print its nodes according to the "bottom-up" postorder traversal.
    void printPostorder(Node node) {
        if (node != null){                      // if node is empty, do nothing
            printPostorder(node.left);          // start at bottom of left tree
            printPostorder(node.right);         // then go to the right tree after the left tree is done
            System.out.print(node.key + " ");   // works the way up from left -> right -> centre
        }
    }

    // Given a binary tree, print its nodes in inorder
    void printInorder(Node node) {
        if (node != null){                      // if node is empty, do nothing
            printInorder(node.left);            // go through the left tree first
            System.out.print(node.key + " ");   // works the way up from left -> centre -> right
            printInorder(node.right);           // after printing left tree item, go to right tree
        }
    }

    // Given a binary tree, print its nodes in preorder
    void printPreorder(Node node) {
        if (node != null){                      // if node is empty, do nothing
            System.out.print(node.key + " ");   // print center item first
            printPreorder(node.left);           // then traverse and print left tree
            printPreorder(node.right);          // after traversing the left tree, go through the right tree and print
        }                                       // works the way up from middle -> left -> right
    }

    public static void main(String[] args) {
        TraverseTree tree = new TraverseTree(50);
        tree.addChild(tree.root, new Node(30), true);
        tree.addChild(tree.root, new Node(70), false);
        tree.addChild(tree.root.left, new Node(20), true);
        tree.addChild(tree.root.left, new Node(40), false);
        tree.addChild(tree.root.right, new Node(60), true);
        tree.addChild(tree.root.right, new Node(80), false);
        tree.printTree();
        tree.deleteChild(tree.root.left, true);
        tree.printTree();

        System.out.println("printing tree in in-order ...");
        tree.printInorder(tree.root);
        System.out.println();

        System.out.println("printing tree in post-order ...");
        tree.printPostorder(tree.root);
        System.out.println();

        System.out.println("printing tree in pre-order ...");
        tree.printPreorder(tree.root);
        System.out.println();
    }
}
