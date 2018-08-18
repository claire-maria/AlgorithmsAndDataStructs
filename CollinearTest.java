import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

// -------------------------------------------------------------------------
/**
 * Test class for Collinear.java
 *
 * @author Claire Gribbin
 * @version 05/10/17 9:15:35
 */
@RunWith(JUnit4.class)
public class CollinearTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new Collinear();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the two methods work for empty arrays
	 */
	@Test
	public void testEmpty() {
		int expectedResult = 0;

		assertEquals("countCollinear failed with 3 empty arrays", expectedResult,
				Collinear.countCollinear(new int[0], new int[0], new int[0]));
		assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult,
				Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
	}

	// ----------------------------------------------------------
	/**
	 * Check for no false positives in a single-element array
	 */
	@Test
	public void testSingleFalse() {
		int[] a3 = { 15 };
		int[] a2 = { 5 };
		int[] a1 = { 10 };

		int expectedResult = 0;

		assertEquals("countCollinear({10}, {5}, {15})", expectedResult, Collinear.countCollinear(a1, a2, a3));
		assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
	}

	// ----------------------------------------------------------
	/**
	 * Check for no false positives in a single-element array
	 */
	@Test
	public void testSingleTrue() {
		int[] a3 = { 15, 5 };
		int[] a2 = { 5 };
		int[] a1 = { 10, 15, 5 };

		int expectedResult = 1;

		assertEquals(
				"countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",
				expectedResult, Collinear.countCollinear(a1, a2, a3));
		assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3)
				+ ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
	}

	@Test
	public void testSorted() {
		int[] a = { 5, 3, 4, 1, 2 };
		Collinear.sort(a);
		int[] b = { 1, 2, 3, 4, 5 };
		assertEquals(Arrays.toString(a), Arrays.toString(b));

	}

	@Test
	public void testBinarySearch() {
		int[] a = { 5, 4, 3, 2, 1 };
		int x = 3;
		assertEquals(Collinear.binarySearch(a, x), true);
		x = 6;
		assertEquals(Collinear.binarySearch(a, x), false);

	}
}

// @Test
// public void checkIsFast() {
//
// }

// TODO: add more tests here. Each line of code and each decision in //
// Collinear.java should // be executed at least once from at least one test.

// ----------------------------------------------------------
//
// public static void main(String[] args) {
// In lowf1 = new In("r01000-1");
// In lowf2 = new In("r01000-2");
// In lowf3 = new In("r01000-3");
// In midf1 = new In("r02000-1");
// In midf2 = new In("r02000-2");
// In midf3 = new In("r02000-3");
// In hiLof1 = new In("r04000-1");
// In hiLof2 = new In("r04000-2");
// In hiLof3 = new In("r04000-3");
// In hiHif1 = new In("r05000-1");
// In hiHif2 = new In("r05000-2");
// In hiHif3 = new In("r05000-3");
//
// int a1[] = lowf1.readAllInts();
// int a2[] = lowf2.readAllInts();
// int a3[] = lowf3.readAllInts();
// Stopwatch timer = new Stopwatch();
// Collinear.countCollinear(a1, a2, a3);
// System.out.println(timer.elapsedTime());
//
// }
// }
