class m_SetByArray {
    // Use m_array to store all the items in the set.
    // This is a strict mandatory requirement of this assignment.
    // To keep it simple, we use a fixed length array with 100 items.
    Integer[] m_array = new Integer[100];
    int number_of_items = 0; // this variable keep track of how many items are in the set.

    // implement method size().
    // Returns the number of elements in this set (its cardinality).
    public int size() {
        int count = 0;
        for (Integer number : m_array){
            if (number != null) count++;
        }
        number_of_items = count;
        return number_of_items;
    }

    // implement method isEmpty()
    // Returns true if this set contains no elements, otherwise return false.
    public boolean isEmpty() {
        for (Integer number : m_array) {
            if (number != null) { return false; }
        }
        return true;
    }

    // implement method contains()
    /* Returns true if this set contains the specified element. More formally,
    returns true if and only if this set contains an element e such that Objects.equals(o, e).
    Parameters:
        o – element whose presence in this set is to be tested
    Returns:
        true if this set contains the specified element
    */
    public boolean contains(Integer o) {
        for (Integer number : m_array){
            if (number.equals(o)) return true;
        }
        return false;
    }

    // implement method add()
    /* Adds the specified element to this set if it is not already present.
     * If this set does not contain the element, add the element to this set
     * and return true.
     * If this set already contains the element, the call leaves the set
     * unchanged and return false.
     */
    public boolean add(Integer e) {

        for (Integer number : m_array){
            if (number != null && number.equals(e)) return false;
        }

        for (int i = 0; i < m_array.length; i++){
            if (m_array[i] == null) {
                m_array[i] = e;
                return true;
            }
        }
        return false;
    }

    // implement method remove()
    /* Removes the specified element from this set if it is present.
     * Returns true if this set contained the element.
     * Returns false if this set does not contain the element
     */
    public boolean remove(Integer o) {
        int j = m_array.length;
        boolean present = false;

        for (int i = 0; i < m_array.length; i++){
            if (m_array[i].equals(o)) {
                m_array[i] = null;
                present = true;
                j = i;
                break;
            }
        }

        // can use System.arraycopy but I found this to be easier to understand
        // move all the elements to the right of the removed element 1 index to the left to fill the empty space
        for (int k = j; k < m_array.length-1; k++){
            m_array[k] = m_array[k+1];
        }
        m_array[99] = null; // last element should now be null
        return present;
    }

    // implement method clear()
    /* Removes all of the elements from this set. The set will be empty after this call returns. */
    public void clear() {
        for (int i = 0; i < m_array.length; i++){
            if (m_array[i] != null){
                m_array[i] = null;
            }
        }
    }

    // print the set items
    public void printSet() {
        System.out.print("[");
        for (Integer number : m_array){
            if (number != null) System.out.print(number + ", ");
        }
        System.out.println("]");
    }
}

public class Q2_SetByArray {

    public static void main(String[] args) {
        System.out.println("******************************");
        System.out.println("Start testing with Integer ...");
        m_SetByArray test_integer = new m_SetByArray();
        System.out.println("Is empty? " + test_integer.isEmpty());
        System.out.println("Size is: " + test_integer.size());
        test_integer.add(1);
        test_integer.add(2);
        test_integer.add(3);
        test_integer.printSet();
        System.out.println("Testing on adding duplicate items");
        System.out.println("Can we add a duplicate item valued 2? " + test_integer.add(2));
        System.out.println("Can we remove value 2? " + test_integer.remove(2));
        test_integer.printSet();
        System.out.println("Can we add an old item valued 2 after removing? " + test_integer.add(2));
        test_integer.printSet();
        System.out.println("Can we clear all? ");
        test_integer.clear();
        test_integer.printSet();
    }
}
