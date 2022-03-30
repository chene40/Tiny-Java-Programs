package BinTree;

// Class containing left and right child of current node and key value
public class Node {
    public int key;
    public Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}