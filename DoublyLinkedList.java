import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Claire Gribbin 16325614
 *  @version 13/10/16 18:15
 */

/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * 
 * @param <T>
 *            This is a type parameter. T is used as a class name in the
 *            definition of this class.
 *
 *            When creating a new DoublyLinkedList, T should be instantiated
 *            with an actual class name that extends the class Comparable. Such
 *            classes include String and Integer.
 *
 *            For example to create a new DoublyLinkedList class containing
 *            String data: DoublyLinkedList<String> myStringList = new
 *            DoublyLinkedList<String>();
 *
 *            The class offers a toString() method which returns a
 *            comma-separated sting of all elements in the data structure.
 * 
 *            This is a bare minimum class you would need to completely
 *            implement. You can add additional methods to support your code.
 *            Each method will need to be tested by your jUnit tests -- for
 *            simplicity in jUnit testing introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {
	static int lengthOfLinkedList;

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode {
		public T data; // this field should never be updated. It gets its
								// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * 
		 * @param theData
		 *            : data of type T, to be stored in the node
		 * @param prevNode
		 *            : the previous Node in the Doubly Linked List
		 * @param nextNode
		 *            : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor
	 * 
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic runtime cost: 1
	 *
	 *         Justification: One operation
	 */
	public boolean isEmpty() {
		// TODO
		return head == null;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * 
	 * @param pos
	 *            : The integer location at which the new data should be inserted in
	 *            the list. We assume that the first position in the list is 0
	 *            (zero). If pos is less than 0 then add to the head of the list. If
	 *            pos is greater or equal to the size of the list then add the
	 *            element at the end of the list.
	 * @param data
	 *            : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *         Worst-case asymptotic runtime cost:O(N)
	 *
	 *         Justification: The while loop is O(N), it is not nested so it will
	 *         run count(N) times, no nested loops.
	 */
	public void insertBefore(int pos, T data) {
		DLLNode new_Node = new DLLNode(data, null, null);
		int count = 0;
		int length = 0;
		DLLNode current = head;
		while (current != null) // until end of DLL
		{
			current = current.next;
			length++;
		}

		if (isEmpty()) { // DLL is empty
			tail = new_Node;
			head = new_Node;
			length++;
		} else if (pos <= 0) // if pos <= 0, must be first in list
		{
			head.prev = new_Node;
			new_Node.next = head;
			head = new_Node;
			length++;
		}

		else if (pos >= length) // node must be at tail
		{
			tail.next = new_Node;
			new_Node.prev = tail;
			tail = new_Node;
			length++;
		}
		// must find position and insert before next node
		else {
			current = head;
			while (count != pos) {
				current = current.next;
				count++;
			}
			DLLNode prevNode = current.prev;
			prevNode.next = new_Node;
			new_Node.prev = prevNode;
			new_Node.next = current;
			current.prev = new_Node;
			length++;
		}
		lengthOfLinkedList = length;
	}

	// TODO

	/**
	 * Returns the data stored at a particular position
	 * 
	 * @param pos
	 *            : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null
	 *         otherwise.
	 *
	 *         Worst-case asymptotic runtime cost: O(N)
	 *
	 *         Justification: One while loop, runs to i
	 *
	 *         Worst-case precise runtime cost: TODO
	 *
	 *         Justification: TODO
	 */
	public T get(int pos) {

		int i = 0;
		DLLNode current = head;
		if (i >= 0) {
			if (i < lengthOfLinkedList) {
				while (i != pos) {
					current = current.next;
					i++;
				}
				return current.data;
			}
		}
		return null;
	}

	/**
	 * Deletes the element of the list at position pos. First element in the list
	 * has position 0. If pos points outside the elements of the list then no
	 * modification happens to the list.
	 * 
	 * @param pos
	 *            : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified.
	 *
	 *         Worst-case asymptotic runtime cost: O(N)
	 *
	 *         Justification: While loop, non nested. Runs N times.
	 */
	public boolean deleteAt(int pos) {
		int nodePos = 0;
		DLLNode current = head;

		if (pos >= 0 && pos < lengthOfLinkedList) { // only one element in DLL
			if (pos == 0 && lengthOfLinkedList == 1) {
				head = null;
				tail = null;
				lengthOfLinkedList--;
				return true;
			} else if (pos == 0) { // must remove head but more than one element in DLL
				head = current.next;// new head
				head.prev = null;
				lengthOfLinkedList--;
				return true;
			} else { // Must find position at which to remove node
				while (nodePos != pos) // ensures that the position of node to be deleted is available
				{
					current = current.next;
					nodePos++;
				}
				if (current == tail) { // new tail node
					tail = tail.prev;
					tail.next = null;
				} else {
					DLLNode prevNode = current.prev;
					DLLNode nextNode = current.next;
					prevNode.next = nextNode;
					nextNode.prev = prevNode;
				}
				lengthOfLinkedList--;
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the method
	 * is called Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic runtime cost: O(N/2)
	 *
	 * Justification: One while loop, runs N times, no nested loop.
	 */
	public void reverse() {
		DLLNode index = head;
		DLLNode index2 = tail;
		DLLNode tempA;
		DLLNode tempB;
		int i = 0;
		while (i < lengthOfLinkedList / 2) {
			tempA = index;
			tempB = index2;
			index.data = tempB.data;
			index2.data = tempA.data;
			i++;
		}

	}

	/*----------------------- STACK */
	/**
	 * This method should behave like the usual push method of a Stack ADT. If only
	 * the push and pop methods are called the data structure should behave like a
	 * stack. How exactly this will be represented in the Doubly Linked List is up
	 * to the programmer.
	 * 
	 * @param item
	 *            : the item to push on the stack
	 *
	 *            Worst-case asymptotic runtime cost: O(1)
	 *
	 *            Justification: No loops, each operation is O(1).
	 */
	public void push(T item) {

		DLLNode new_Node = new DLLNode(item, null, null);
		if (isEmpty()) {
			head = new_Node;
			tail = new_Node;
		} else {
			new_Node.prev = null;
			head.prev = new_Node;
			new_Node.next = head;
			head = new_Node;
		}
		lengthOfLinkedList++;
	}

	/**
	 * This method should behave like the usual pop method of a Stack ADT. If only
	 * the push and pop methods are called the data structure should behave like a
	 * stack. How exactly this will be represented in the Doubly Linked List is up
	 * to the programmer.
	 * 
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 *         Worst-case asymptotic runtime cost: O(1)
	 *
	 *         Justification: No loops, each op is O(1).
	 */
	public T pop() {
		if (isEmpty()) {
			return null;
		} else {
			T popped = head.data;
			if (head.next == null) {
				head = null;
				tail = null;
			} else {
				head = head.next;
				head.prev = null;
			}
			lengthOfLinkedList--;
			return popped;
		}

	}

	/*----------------------- QUEUE */

	/**
	 * This method should behave like the usual enqueue method of a Queue ADT. If
	 * only the enqueue and dequeue methods are called the data structure should
	 * behave like a FIFO queue. How exactly this will be represented in the Doubly
	 * Linked List is up to the programmer.
	 * 
	 * @param item
	 *            : the item to be enqueued to the stack
	 *
	 *            Worst-case asymptotic runtime cost: O(1)
	 *
	 *            Justification: No loops, each operation is O(1), O(1) + O(1) =
	 *            O(1)
	 */
	public void enqueue(T item) {
		DLLNode the_Node = new DLLNode(item, null, null);
		if (isEmpty()) {
			head = the_Node;
			tail = the_Node;
		} else {
			the_Node.next = null;
			tail.next = the_Node;
			the_Node.prev = null;
			tail = the_Node;
		}
	}

	/**
	 * This method should behave like the usual dequeue method of a Queue ADT. If
	 * only the enqueue and dequeue methods are called the data structure should
	 * behave like a FIFO queue. How exactly this will be represented in the Doubly
	 * Linked List is up to the programmer.
	 * 
	 * @return the earliest item inserted with a push; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic runtime cost: O(1)
	 *
	 *         Justification: No loops, each operation is O(1), O(1) + O(1) = O(1)
	 */
	public T dequeue() {
		if (isEmpty()) {
			return null;
		} else {
			T popped = tail.data;
			if (tail.prev == null) {
				head = null;
				tail = null;
			} else {
				tail = tail.prev;
				tail.next = null;
			}
			lengthOfLinkedList--;
			return popped;

		}

	}

	/**
	 * @return a string with the elements of the list as a comma-separated list,
	 *         from beginning to end
	 *
	 *         Worst-case asymptotic runtime cost: Theta(n)
	 *
	 *         Justification: We know from the Java documentation that
	 *         StringBuilder's append() method runs in Theta(1) asymptotic time. We
	 *         assume all other method calls here (e.g., the iterator methods above,
	 *         and the toString method) will execute in Theta(1) time. Thus, every
	 *         one iteration of the for-loop will have cost Theta(1). Suppose the
	 *         doubly-linked list has 'n' elements. The for-loop will always iterate
	 *         over all n elements of the list, and therefore the total cost of this
	 *         method will be n*Theta(1) = Theta(n).
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next) {
			if (!isFirst) {
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

	public int size() {
		DLLNode temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}
}
