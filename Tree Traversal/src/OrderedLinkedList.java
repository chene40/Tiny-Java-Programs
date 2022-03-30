/* 
    1. Inherit from LKList.LinkedList to implement a subclass OrderedLinkedList that overrides the sortLinkedList()
    method by using insertion sort instead of the original bubble sort in LKList.LinkedList. Please sort all nodes in
    ascending order of data values.
    2. Implement an orderedInsert() method that inserts a new node in an ordered linked list without breaking the order of
    nodes. Please use one or multiple of the methods of the super class LKList.LinkedList such as insertAtBeginning(),
    insertAtEnd(int new_data) and insertAfter().
*/

import LKList.*;

public class OrderedLinkedList extends LKList.LinkedList{

    // Use the following method to override the original sortLinkedList() method.
    // The following method should implement insertion sort.

    public void sortLinkedList(Node headref) {
        System.out.println("Running insertion sort of A1_OrderedLinkedList ...");

        // if linked list is empty, stop the sort
        if (headref == null || headref.next == null) {
            return;
        }

        Node current = headref;                 // to be used to traverse the linked list

        while (current != null) {               // when value is null, we are at end of the list
            Node next = current.next;
            Node start = headref;
            Node prev = headref;                // to be used to represent the previous node (links to the node after the current node)

            while (start != next){                  // compares every node before current node with current node
                // if start equals the next node, then this means that we have not came across a value larger than our current value (i.e. just leave it as is)
                if (start.data > current.data){
                    current.next = start;           // current will point to this node if its data is smaller (i.e. current goes before start)

                    if (start == headref){          // if start is the first node in the linked list, then also update the head node
                        headref = current;
                    }

                    else {                          // if it is not, then we just have to link the previous node with to the current node
                        prev.next = current;
                    }

                    while (start.next != current){  // keep moving the starting node until the next node is the current node
                        start = start.next;
                    }
                    start.next = next;              // start will now point to the next node in line
                }
                prev = start;                       // the previous node is the node before the start node
                start = start.next;                 // move start down the list
            }
            current = next;                         // move current node down the list
        }
        head = headref;                             // update the head to be the sorted list
    }

    // Use the following method to implement orderedInsert().
    // The 'newnode' should be inserted without breaking the order of a LinkedList in ascending order.
    // Only the methods insertAtBeginning(), insertAtEnd(int new_data) and insertAfter() of the superclass LKList.A2_LinkedList can be used.

    public void orderedInsert(Node newnode) {

        Node current = head;

        // if head node is not initialized or if the data to be inserted is the smallest element, then insert the data at the beginning of the list
        if (head == null || newnode.data <= head.data){
            insertAtBeginning(newnode.data);
        }

        // otherwise we can check where else in the list the data should be inserted
        else{
            Node previous = head;
            while (current != null){

                // keep moving down the list until the data is no longer greater than the current element
                // (i.e. the data to be inserted is greater than the previous element but smaller than the current element
                if (newnode.data > current.data){
                        previous = current;
                        current = current.next;
                    }

                // we have reached the node where the new data is no longer greater than the current node's data
                // data is greater than previous data but smaller than current data
                // insert new node after the previous node and before the current data
                else{
                    insertAfter(previous, newnode.data);
                }
            }
            // at this point we have traversed the entire list (i.e. the data value to be inserted is greater than all data values in the list)
            // add the data to the end of the list
            insertAtEnd(newnode.data);
        }
    }

    public static void main (String[] args) {
        // test A1_OrderedLinkedList
        OrderedLinkedList llist1 = new OrderedLinkedList();

        llist1.insertAtEnd(1);
        llist1.insertAtBeginning(3);

        llist1.insertAtBeginning(5);

        llist1.insertAtEnd(8);
        llist1.insertAfter(llist1.head.next, 9);
        llist1.printList();

        llist1.sortLinkedList(llist1.head);
        llist1.printList();

        Node newnode = new Node(10);
        llist1.orderedInsert(newnode);
        llist1.printList();

        // test A2_LinkedList
        A2_LinkedList llist2 = new LinkedList();

        llist2.insertAtEnd(2);
        llist2.insertAtBeginning(4);
        llist2.insertAtBeginning(6);
        llist2.insertAtEnd(8);
        llist2.insertAfter(llist2.head.next, 10);
        llist2.printList();

        llist2.sortLinkedList(llist2.head);
        llist2.printList();
    }
}
