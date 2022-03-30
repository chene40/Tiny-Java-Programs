import java.util.*;

class m_SetByArrayList<E> implements Set<E> {

    // Use m_array to store all the items in the set.
    // This is a strict mandatory requirement of this assignment.
    ArrayList<E> m_arraylist = new ArrayList<E>();

    // implement method size().
    // Returns the number of elements in this set (its cardinality).
    @Override
    public int size() {
        return m_arraylist.size();          // use pre-defined ArrayList method
    }

    // implement method isEmpty()
    // Returns true if this set contains no elements, otherwise return false.
    @Override
    public boolean isEmpty() {
        return (m_arraylist.size() == 0);
    }

    // implement method contains()
    /* Returns true if this set contains the specified element. More formally,
    returns true if and only if this set contains an element e such that Objects.equals(o, e).
    Parameters:
        o – element whose presence in this set is to be tested
    Returns:
        true if this set contains the specified element
    */
    @Override
    public boolean contains(Object o) {
        E object_in_class_E = (E) o; // Hint for the assignment, this converts class Object to a class E
        for (E element : m_arraylist){
            if (element.equals(object_in_class_E))  return true;
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
    @Override
    public boolean add(E e) {
        for (E element : m_arraylist){
            if (element.equals(e))  return false;
        }
        m_arraylist.add(e);
        return true;
    }

    // implement method remove()
    /* Removes the specified element from this set if it is present.
     * Returns true if this set contained the element.
     * Returns false if this set does not contain the element
     */
    @Override
    public boolean remove(Object o) {
        E object_in_class_E = (E) o; // Hint for the assignment, this converts class Object to a class E
        for (E element : m_arraylist){
            if (element.equals(object_in_class_E)){
                m_arraylist.remove(element);
                return true;
            }
        }
        return false;
    }

    // implement method clear()
    /* Removes all of the elements from this set. The set will be empty after this call returns. */
    @Override
    public void clear() {
        m_arraylist.clear();
    }

    // *********************************************************
    // * Not implementing the rest of these methods. *
    // *********************************************************
    @Override
    public Object[] toArray() {
        return m_arraylist.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}

public class Q1_SetByArrayList {
    public static void printSetInteger(Set<Integer> m_set){
        System.out.println("Print set as: ");
        System.out.print("[");
        for (Object i:m_set.toArray()){
            System.out.print("" + (Integer) i + ", ");
        }
        System.out.println("]");
    }

    public static void printSetString(Set<String> m_set){
        System.out.println("Print set as: ");
        System.out.print("[");
        for (Object i:m_set.toArray()){
            System.out.print("" + (String) i + ", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        System.out.println("******************************");
        System.out.println("Start testing with Integer ...");
        m_SetByArrayList<Integer> test_integer = new m_SetByArrayList<Integer>();
        System.out.println("Is empty? " + test_integer.isEmpty());
        System.out.println("Size is: " + test_integer.size());
        test_integer.add(1);
        test_integer.add(2);
        test_integer.add(3);
        printSetInteger(test_integer);
        System.out.println("Testing on adding duplicate items");
        System.out.println("Can we add a duplicate item valued 2? " + test_integer.add(2));
        System.out.println("Can we remove value 2? " + test_integer.remove(2));
        printSetInteger(test_integer);
        System.out.println("Can we add an old item valued 2 after removing? " + test_integer.add(2));
        printSetInteger(test_integer);
        System.out.println("Can we clear all? ");
        test_integer.clear();
        printSetInteger(test_integer);

        System.out.println("\n******************************");
        System.out.println("Start testing with String ...");
        m_SetByArrayList<String> test_string = new m_SetByArrayList<String>();
        System.out.println("Is empty? " + test_string.isEmpty());
        System.out.println("Size is: " + test_string.size());
        test_string.add("jack");
        test_string.add("rose");
        test_string.add("james bond");
        printSetString(test_string);
        System.out.println("Testing on adding duplicate items");
        System.out.println("Can we add a duplicate item valued \"rose\"? " + test_string.add("rose"));
        System.out.println("Can we remove value \"rose\"? " + test_string.remove("rose"));
        printSetString(test_string);
        System.out.println("Can we add an old item valued 2 after removing? " + test_string.add("rose"));
        printSetString(test_string);
        System.out.println("Can we clear all? ");
        test_string.clear();
        printSetString(test_string);
    }
}