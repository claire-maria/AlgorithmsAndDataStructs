import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @author Claire Gribbin
 * @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new DoublyLinkedList<Integer>();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check if the insertBefore works
	 */
	@Test
	public void testInsertBefore() {
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);

		testDLL.insertBefore(0, 4);
		assertEquals("Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(1, 5);
		assertEquals("Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(2, 6);
		assertEquals("Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(-1, 7);
		assertEquals(
				"Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list",
				"7,4,5,6,1,2,3", testDLL.toString());
		testDLL.insertBefore(7, 8);
		assertEquals(
				"Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8", testDLL.toString());
		testDLL.insertBefore(700, 9);
		assertEquals(
				"Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8,9", testDLL.toString());

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 0 - expected the element at the head of the list",
				"1", testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 10 - expected the element at the head of the list",
				"1", testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position -10 - expected the element at the head of the list",
				"1", testDLL.toString());
	}

	@Test
	public void testGet() {

		{
			DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
			testDLL.isEmpty();
			assertEquals("Check if get any node [" + testDLL + "] from empty stack", null, testDLL.get(0));

			testDLL.insertBefore(0, 1);
			assertSame("Check if get any node [" + testDLL + "] with one item", 1, testDLL.get(0));

			testDLL.insertBefore(-1, 7);
			assertEquals("Check if get any node [" + testDLL + "] while node is not in stack", null, testDLL.get(-1));

			testDLL.insertBefore(700, 7);
			assertEquals("Check if get any node while [" + testDLL + "] node is not in stack", null, testDLL.get(700));

			testDLL = new DoublyLinkedList<Integer>();
			testDLL.insertBefore(0, 1);
			testDLL.insertBefore(1, 2);
			testDLL.insertBefore(2, 3);
			assertNull("Check if get node in position 0 [" + testDLL + "] of length 3", testDLL.get(3));
			assertEquals("Check if get node in position 2 [" + testDLL + "] of length 3", Integer.valueOf(3),
					testDLL.get(2));
			assertEquals("Check if get node in position 1 [" + testDLL + "] of length 3", Integer.valueOf(2),
					testDLL.get(1));
		}
	}

	@Test
	public void deleteAt() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		assertTrue("Testing when deleting [" + testDLL + "] the only node in linked list", testDLL.deleteAt(0));
		assertFalse("Testing when deleting [" + testDLL + "] when no links in list", testDLL.deleteAt(0));
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 4);
		testDLL.insertBefore(2, 3);
		assertTrue("Testing when deleting one node [" + testDLL + "] in linked list", testDLL.deleteAt(0));
		assertFalse("Testing when deleting one node [" + testDLL + "] that isn't in the linked list (below list)",
				testDLL.deleteAt(-1));
		assertFalse("Testing when deleting one node [" + testDLL + "] that isn't in the linked list (above list)",
				testDLL.deleteAt(700));
		assertTrue("Testing when deleting one node in linked list of two links, [" + testDLL
				+ "] after failing to delete nodes that aren't present", testDLL.deleteAt(0));
		assertTrue("Testing when deleting the final node [" + testDLL + "] in the list", testDLL.deleteAt(0));

		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 4);
		assertTrue("Testing when deleting one node [" + testDLL + "] in linked list at head", testDLL.deleteAt(0));
		assertTrue("Testing when deleting the last node [" + testDLL + "] in linked list at head", testDLL.deleteAt(0));

		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 4);
		assertTrue("Testing when deleting one node [" + testDLL + "] in linked list, also tail", testDLL.deleteAt(1));
		assertTrue("Testing when deleting the last node [" + testDLL + "] in linked list at head", testDLL.deleteAt(0));

		testDLL = new DoublyLinkedList<Integer>();
		assertFalse("Testing when deleting [" + testDLL + "] when no links present", testDLL.deleteAt(0));

		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 4);
		testDLL.insertBefore(2, 3);
		assertFalse("Testing when deleting one node of three element list, [" + testDLL + "] at position -3",
				testDLL.deleteAt(-3));
		assertFalse("Testing when deleting one node of three element list, [" + testDLL + "] at position -2",
				testDLL.deleteAt(-2));
		assertFalse("Testing when deleting one node of three element list, [" + testDLL
				+ "] in linked list in the negative version of the position of head, -1", testDLL.deleteAt(-1));
		assertTrue("Testing when deleting one node of two element list, [" + testDLL + "] at position 0",
				testDLL.deleteAt(0));
		assertTrue("Testing when deleting one nodeof one element list, [" + testDLL + "] at position 1",
				testDLL.deleteAt(1));
		assertFalse("Testing when deleting one node of an empty list, [" + testDLL + "] at position 2",
				testDLL.deleteAt(2));
		assertFalse("Testing when deleting one node of an empty list, [" + testDLL + "] at position 3",
				testDLL.deleteAt(3));

		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 4);
		assertFalse("Testing when deleting one node of three element list, [" + testDLL + "] at position -2",
				testDLL.deleteAt(-2));
		assertFalse("Testing when deleting one node of three element list, [" + testDLL + "] at position -1",
				testDLL.deleteAt(-1));
		assertTrue("Testing when deleting one node of three element list, [" + testDLL + "] at position 0",
				testDLL.deleteAt(0));
		assertFalse("Testing when deleting one nodeof three element list, [" + testDLL + "] at position 1",
				testDLL.deleteAt(1));
		assertFalse("Testing when deleting one node of three element list, [" + testDLL + "] at position 2",
				testDLL.deleteAt(2));

		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);
		testDLL.insertBefore(3, 4);
		testDLL.insertBefore(4, 5);
		assertTrue("Testing when deleting one node of five element list, [" + testDLL + "] at position 3",
				testDLL.deleteAt(3));

	}

	@Test
	public void reverse() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();

		testDLL.insertBefore(1, 1);
		testDLL.insertBefore(2, 2);
		testDLL.insertBefore(3, 3);

		DoublyLinkedList<Integer> expectedList = new DoublyLinkedList<Integer>();
		expectedList.insertBefore(3, 1);
		expectedList.insertBefore(2, 2);
		expectedList.insertBefore(1, 3);

		for (int i = 0; i < expectedList.size() + 1; i++)
			assertEquals("Checking item with index " + i, testDLL.get(i), expectedList.get(i));
		assertEquals(testDLL.size(), expectedList.size());

	}

	@Test
	public void push() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(1);
		assertEquals("Pushing one node into empty linked list", "1", testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);
		assertEquals("Pushing a new node, 3, into occupied link list", "3,2,1", testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(1);
		testDLL.push(2);
		testDLL.insertBefore(2, 4);
		testDLL.push(4);
		assertEquals("Pushing a new node into position 3, into occupied link list of length 2", "4,2,1,4",
				testDLL.toString());

	}

	@Test
	public void pop() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		assertNull("Attempting to pop [" + testDLL + "] while there is nothing in the Linked list", testDLL.pop());

		testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(1);
		assertEquals("Popping the head/tail [" + testDLL + "] of the linked list of length one", Integer.valueOf(1),
				testDLL.pop());

		testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);
		assertEquals("popping the head [" + testDLL + "] off the linked list of length 3", Integer.valueOf(3),
				testDLL.pop());

		testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(1);
		assertEquals("popping the head [" + testDLL + "] off the linked list of length 1", Integer.valueOf(1),
				testDLL.pop());

		testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);
		assertEquals("popping position 3 [" + testDLL + "] off the linked list of length 3", Integer.valueOf(3),
				testDLL.pop());
		assertEquals("popping position 2 [" + testDLL + "] off the linked list of length 2", Integer.valueOf(2),
				testDLL.pop());
		assertEquals("popping position 1 [" + testDLL + "] off the linked list of length 1", Integer.valueOf(1),
				testDLL.pop());
	}

	@Test
	public void enqueue() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.enqueue(1);
		assertEquals("Trying to enqueue a node [" + testDLL + "] of value 1 into empty linked list", "1",
				testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);
		testDLL.push(4);
		testDLL.enqueue(5);
		assertEquals("Trying to enqueue a node [" + testDLL + "] of value 5 into linked list of length 4", "4,3,2,1,5",
				testDLL.toString());
	}

	@Test
	public void dequeue() {
		{
			DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
			assertNull("Attempting to dequeue [" + testDLL + "] and empty linkedList", testDLL.dequeue());

			testDLL = new DoublyLinkedList<Integer>();
			testDLL.push(1);
			assertEquals("Popping the head(/tail) [" + testDLL + "] of the linked list of length one",
					Integer.valueOf(1), testDLL.dequeue());

			testDLL = new DoublyLinkedList<Integer>();
			testDLL.push(1);
			testDLL.push(2);
			testDLL.push(3);
			assertEquals("Dequeueing the head [" + testDLL + "] off of the linked list of length three",
					Integer.valueOf(1), testDLL.dequeue());
			assertEquals("Dequeueing the head [" + testDLL + "] off of the linked list of length two",
					Integer.valueOf(2), testDLL.dequeue());
			assertEquals("Dequeueing the head [" + testDLL + "] off of the linked list of length one",
					Integer.valueOf(3), testDLL.dequeue());
		}

	}

}
