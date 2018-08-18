import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//program ran 3 times, average time

//
//Insert 					insert	| Quick | Merge | Shell | Selection | Bubble
//							
//10 Random					5994	|532804 |23626	| 4232	|  8815     | 7052
//
//100 Random			    197818	|868144 |119890	| 25331 |  239780   | 176662
//
//1000 Random				5768819	|2226073|797621 | 214392|  4275132  | 3575537
//
//1000 few unique			4680994 |1755681|1008133| 325113|  7380632  | 5632355
//
//1000 nearly ordered       4566041 |2346315|918920 | 302193|  7293888  | 5596389
//
//1000 reverse order		12870177|2381577|855097 | 326876|  6612632  | 5709578
//
//1000 ascending order		5029380 |3244079|811543 | 811543|  7359123  | 3135485
//

//		q1)Which of the sorting algorithms does the order of input have an impact on? Why? 
//		 Insertion sort, worst case is when the numbers are in reverse order.
//		 Shell sort is also affected, best case O(n log n); worst case O(n^2)
//		 Bubble sort’s best case is when the array is presorted, worst case is when it is in reverse order, 
//		 in best case it does not need to swap anything. 
//		 Merge sort, is comparison based so when less comparisons are needed




//		q2) Which algorithm has the biggest difference between the best and worst performance, based on the type of input, for the input of size 1000? Why?
//		Insertion Sort, because it has to swap everything. 


//		q3) Which algorithm has the best/worst scalability, ie,
//      the difference in performance time based on the input size? 
//      Please consider only input files with random order for this answer. 
//		Shell Sort. 
//		q4) Which algorithm is the fastest for each of the 7 input files? 
//		Shell sort, in all cases.

























//-------------------------------------------------------------------------
/**
 * Test class for SortComparison.java
 *
 * @author
 * @version HT 2018
 */
@RunWith(JUnit4.class)
public class SortComparisonTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new SortComparison();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for empty arrays
	 */
	@Test
	public void testEmpty() {
		double[] orig = new double[0];
		double[] testEmptyArr = orig.clone();
		SortComparison.quickSort(testEmptyArr);
		assertTrue(Arrays.equals(orig, testEmptyArr));
		SortComparison.insertionSort(testEmptyArr);
		assertTrue(Arrays.equals(orig, testEmptyArr));
		SortComparison.mergeSort(testEmptyArr);
		assertTrue(Arrays.equals(orig, testEmptyArr));
		SortComparison.shellSort(testEmptyArr);
		assertTrue(Arrays.equals(orig, testEmptyArr));
		SortComparison.selectionSort(testEmptyArr);
		assertTrue(Arrays.equals(orig, testEmptyArr));
	}

	@Test
	public void testInsertionSort() {
		// test usual
		double[] preSortedArr = { 1, 2, 3, 4, 5 };
		double[] array = { 3, 4, 5, 2, 1 };
		double[] sortedArr = SortComparison.insertionSort(array);
		for (int i = 0; i < array.length - 1; i++) {
			assertEquals(preSortedArr[i], sortedArr[i], 0);
		}
	}

	@Test
	public void testQuickSort() {

		// test three
		double[] expect = new double[] { 1, 2, 2 };
		double[] testThreeArr = new double[] { 2, 1, 2 };
		SortComparison.quickSort(testThreeArr);
		assertTrue(Arrays.equals(expect, testThreeArr));

		// Test usual

		double[] preSortedArr = { 1, 2, 3, 4, 5 };
		double[] array = { 3, 4, 5, 2, 1 };
		SortComparison.quickSort(array);
		assertTrue(Arrays.equals(preSortedArr, array));

	}

	@Test
	public void testMergeSort() {
		double[] preSortedArray = { 1, 2, 3 };
		double[] a = { 1, 3, 2 };
		a = SortComparison.mergeSort(a);
		for (int i = 0; i < a.length; i++) {
			assertEquals(preSortedArray[i], a[i], 0);
		}

	}

	@Test
	public void testShellSort() {
		double[] preSortedArray = { 1, 2, 3 };
		double[] a = { 1, 3, 2 };
		a = SortComparison.shellSort(a);
		for (int i = 0; i < a.length; i++) {
			assertEquals(preSortedArray[i], a[i], 0);
		}

	}

	@Test
	public void testSelectionSort() {
		double[] preSortedArray = { 1, 2, 3 };
		double[] a = { 1, 3, 2 };
		a = SortComparison.selectionSort(a);
		assertTrue(Arrays.equals(a, preSortedArray));

	}

	@Test
	public void testBubbleSort() {
		double[] preSortedArray = { 1, 2, 3 };
		double[] a = { 1, 3, 2 };
		a = SortComparison.bubbleSort(a);
		for (int i = 0; i < a.length; i++) {
			assertEquals(preSortedArray[i], a[i], 0);
		}

	}
	// TODO: add more tests here. Each line of code and ech decision in
	// Collinear.java should
	// be executed at least once from at least one test.

	// ----------------------------------------------------------
	/**
	 * Main Method. Use this main method to create the experiments needed to answer
	 * the experimental performance questions of this assignment.
	 *
	 */
	public static void main(String[] args) {
		// TODO: implement this method
	}

}
