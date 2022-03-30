import mutils.Node;
import mutils.BinarySearchTree;
import java.util.ArrayList;

public class TreeMapByBST {

    // use m_bst as the basic data structure for the treemap of <String, Integer>
    BinarySearchTree m_bst = new BinarySearchTree();

    // implement method size().
    // Returns the number of elements in this map (its cardinality).

    Node root = m_bst.root;     // field variable to be used throughout the methods

    // recursive prototype for size()
    public int size_recursive(Node root, int count) {
        if (root != null) {
            count += size_recursive(root.left, 0);  // add the number of  elements in the left subtree to count
            count += size_recursive(root.right, 0); // add the number of elements in right subtree to count
            count++;    // add the current element (current node) to count
            return count;
        }
        return 0;       // if node is empty, simply do not count it
    }

    public int size() {
        int tree_size = 0;
        return size_recursive(root, tree_size);
    }

    // implement method isEmpty().
    // Returns true if this map contains no elements, otherwise return false.
    public boolean isEmpty() {
        // if the root of the tree is null, the entire tree is null
        // if the root of the tree is not empty, then the tree contains an element
        return (root == null);
    }

    // implement method get().
    // If key is contained in the map, return the value of the key.
    // If key is not contained in the map, return -1.
    public int get(String key) {
        if (root != null){
            if (root.data.compareTo(key) == 0){
                int value = root.freq;
                root = m_bst.root;      // reset the value of root
                return value;
            }

            // if current root data is greater than the new data then now process the left sub-tree
            else if (root.data.compareTo(key) > 0) {
                root = root.left;
                return get(key);
            }

            // if current root data is less than the new data then now process the right sub-tree
            else {
                root = root.right;
                return get(key);
            }
        }
        return -1;
    }

    // implement method put().
    // If the key is already contained in the map, overwrite the old value and return -1;
    // If the key is not contained in the map, add a new <key,value> entry to the map and return 1;

    public int put(String key, int value) {
        if (root != null) {
            // (root.data.compareTo(key) == 0)
            if (root.data.compareTo(key) == 0) {
                root.freq = value;
                root = m_bst.root;      // reset the root node
                return -1;
            }

            // if current root data is greater than the new data then now process the left sub-tree
            else if (root.data.compareTo(key) > 0) {
                root = root.left;
                return put(key, value);
            }

            // if current root data is less than the new data then now process the right sub-tree
            else if (root.data.compareTo(key) < 0) {
                root = root.right;
                return put(key, value);
            }
        }
        // key is not contained in the map
        root = m_bst.root;  // reset the value of root
        Node newnode = new Node(key, value);
        m_bst.insert(newnode);
        return 1;
    }


    // implement method getKeysInAlphabeticalOrder().
    // return the keys of this map as an ArrayList<String> in Alphabetical order.

    // recursive prototype for getKeysInAlphabeticalOrder()
    public void getKeysInAlphabeticalOrderRecursive(Node root, ArrayList<String> keys) {
        if (root != null) {
            getKeysInAlphabeticalOrderRecursive(root.left, keys);
            keys.add(root.data);
            getKeysInAlphabeticalOrderRecursive(root.right, keys);
        }
    }

    public ArrayList<String> getKeysInAlphabeticalOrder() {
        ArrayList<String> keys = new ArrayList<>();
        getKeysInAlphabeticalOrderRecursive(root, keys);
        return keys;
    }
}
