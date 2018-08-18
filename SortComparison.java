// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author
 * @version HT 2018
 */

// Lecture notes used when I ran into issues

class SortComparison {
	private static double[] numbers;
	private static double[] temp = null;
	private static int number;

	/**
	 * Sorts an array of doubles using InsertionSort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	// taken from notes

	static double[] insertionSort(double[] input) {
		double temp = input.length;
		for (int i = 0; i < input.length; i++) {
			for (int j = i; j > 0; j--) {
				if (input[j] < input[j - 1]) {
					temp = input[j];
					input[j] = input[j - 1];
					input[j - 1] = temp;
				}
			}
		}
		return input;
	}

	/**
	 * Sorts an array of doubles using Quick Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	public static double[] quickSort(double[] a) {
		// Base case!
		if (a.length <= 1) {
			return a;
		}

		// pick random pivot
		int pivotIndex = (int) (Math.random() * a.length);
		double pivot = a[pivotIndex];
		// get number of doubles that are less than the pivot
		int lessThanCount = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < pivot)
				lessThanCount++;
		}

		// count up the number of elements that are greater than the pivot
		int greaterThanCount = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > pivot)
				greaterThanCount++;
		}

		// numbers < pivot get put into a new array
		double[] lesser = new double[lessThanCount];
		int indexToPut = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < pivot) {
				lesser[indexToPut] = a[i];
				indexToPut++;
			}
		}

		// numbers > pivot get put in a new array
		double[] greater = new double[greaterThanCount];
		indexToPut = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > pivot) {
				greater[indexToPut] = a[i];
				indexToPut++;
			}
		}

		// quicksort the new arrays using recursion
		quickSort(lesser);
		quickSort(greater);

		// prepare arrays for printing
		int i;
		for (i = 0; i < lesser.length; i++) {
			a[i] = lesser[i];
		}
		int j = greater.length - 1;
		int k;
		for (k = a.length - 1; j >= 0; k--) {
			a[k] = greater[j];
			j--;
		}

		// put in pivot @ appropriate location
		for (int h = i; h <= k; h++) {
			a[h] = pivot;
		}
		return a;

	}

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] mergeSort(double a[]) { // used notes for this
		int length = a.length;
		if (length <= 1) {
			return a;
		}
		double[] temp1 = new double[length / 2];
		double[] temp2 = new double[length - (length / 2)];
		for (int i = 0; i < temp1.length; i++)
			temp1[i] = a[i];
		for (int i = 0; i < temp2.length; i++)
			temp2[i] = a[i + length / 2];
		return merge(mergeSort(temp1), mergeSort(temp2));
	}// end mergesort

	static double[] merge(double[] temp1, double[] temp2) {
		double[] merged = new double[temp1.length + temp2.length];
		int i = 0;
		int j = 0;
		for (int k = 0; k < merged.length; k++) {
			if (i >= temp1.length) {
				merged[k] = temp2[j++];
			} else if (j >= temp2.length) {
				merged[k] = temp1[i++];
			} else if (temp1[i] <= temp2[j]) {
				merged[k] = temp1[i++];
			} else {
				merged[k] = temp2[j++];
			}
		}
		return merged;
	}

	/**
	 * Sorts an array of doubles using Shell Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/* function to sort arr using shellSort */
	// end shellsort

	public static double[] shellSort(double[] array) {
		int inner, outer;
		double temp;

		int h = 1;
		while (h <= array.length / 3) {
			h = h * 3 + 1;
		}
		while (h > 0) {
			for (outer = h; outer < array.length; outer++) {
				temp = array[outer];
				inner = outer;

				while (inner > h - 1 && array[inner - h] >= temp) {
					array[inner] = array[inner - h];
					inner -= h;
				}
				array[inner] = temp;
			}
			h = (h - 1) / 3;
		}
		return array;
	}

	/**
	 * Sorts an array of doubles using Selection Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {
		int first;
		double tmp;
		int count = 1;
		for (int i = a.length - 1; i > 0; i--, count++) {
			first = 0;
			for (int j = 1; j <= i; j++) {
				if (a[j] > a[first])
					first = j;
			}
			tmp = a[first];
			a[first] = a[i];
			a[i] = tmp;

		}
		return a;
	}
	// end selectionsort

	/**
	 * Sorts an array of doubles using Bubble Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] bubbleSort(double a[]) {
		int n = a.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (a[j] > a[j + 1]) {
					// swap temp and arr[i]
					double temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
		return a;
	}

}
// end bubblesort
//
// public static void main(String[] args) {
//
// In numbers10 = new In("numbers10.txt");
// double[] numbers10Array = numbers10.readAllDoubles();
// In numbers100 = new In("numbers100.txt");
// double[] numbers100Array = numbers100.readAllDoubles();
// In numbers1000 = new In("Numbers1000.txt");
// double[] numbers1000Array = numbers1000.readAllDoubles();
// In numbers1000Dup = new In("numbers1000Duplicates.txt");
// double[] numbers1000DupArray = numbers1000Dup.readAllDoubles();
// In numbers1000MostInOrder = new In("numbersNearlyOrdered1000.txt");
// double[] nearlyOrdered1000 = numbers1000MostInOrder.readAllDoubles();
// In worstCase1000 = new In("numbersReverse1000.txt");
// double[] numbersRev1000 = worstCase1000.readAllDoubles();
// In bestCase = new In("numbersSorted1000.txt");
// double[] numbersSorted = bestCase.readAllDoubles();
// insertionSort for 10
// long start = System.nanoTime();
// insertionSort(numbers10Array);
// long end = System.nanoTime();
// System.out.println("time in nanoseconds for insertion sort, for array of size
// 10 = " + ((end - start)));
// // quick sort for 10
// long start1 = System.nanoTime();
// quickSort(numbers10Array);
// long end1 = System.nanoTime();
// System.out.println("time in nanoseconds for quick sort, for array of size 10
// = " + ((end1 - start1)));
// // merge sort for 10
// long start2 = System.nanoTime();
// mergeSort(numbers10Array);
// long end2 = System.nanoTime();
// System.out.println("time in nanoseconds for merge sort, for array of size 10
// = " + ((end2 - start2)));
// // shell sort for 10
// long start3 = System.nanoTime();
// shellSort(numbers10Array);
// long end3 = System.nanoTime();
// System.out.println("time in nanoseconds for shell sort, for array of size 10
// = " + ((end3 - start3)));
// // selection sort for 10
// long start4 = System.nanoTime();
// selectionSort(numbers10Array);
// long end4 = System.nanoTime();
// System.out.println("time in nanoseconds for selection sort, for array of size
// 10, = " + ((end4 - start4)));
// // bubblesort for 10
// long start5 = System.nanoTime();
// bubbleSort(numbers10Array);
// long end5 = System.nanoTime();
// System.out.println("time in nanoseconds for bubble sort, for array of size 10
// = " + ((end5 - start5)));
// }
// }

// for array of size 100
// insertionSort for 100
// long start = System.nanoTime();
// insertionSort(numbers100Array);
// long end = System.nanoTime();
// System.out
// .println("Time taken in nanoseconds for array of 100 random numbers insertion
// " + ((end - start)));
// // quick sort for 100
// long start1 = System.nanoTime();
// quickSort(numbers100Array);
// long end1 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for array of 100 random
// numbers, quick " + ((end1 - start1)));
// // merge sort for 100
// long start2 = System.nanoTime();
// mergeSort(numbers100Array);
// long end2 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for array of 100 random
// numbers, merge "+ ((end2 - start2)));
// // shell sort for 100
// long start3 = System.nanoTime();
// shellSort(numbers100Array);
// long end3 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for array of 100 random shell"
// + ((end3 - start3)));
// // selection sort for 100
// long start4 = System.nanoTime();
// selectionSort(numbers100Array);
// long end4 = System.nanoTime();
// System.out.println(
// "Time taken in nanoseconds for array of 100 random numbers, selection sort" +
// ((end4 - start4)));
// // bubblesort for 100
// long start5 = System.nanoTime();
// bubbleSort(numbers100Array);
// long end5 = System.nanoTime();
// System.out
// .println("Time taken in nanoseconds for array of 100 random numbers, bubble "
// + ((end5 - start5)));
// }
// }

// // for 1000 size
// // insertion
// long start = System.nanoTime();
// insertionSort(numbers1000Array);
// long end = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles,
// insertion" + ((end - start)));
// // quick sort for 1000
// long start1 = System.nanoTime();
// quickSort(numbers1000Array);
// long end1 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles, quick"
// + ((end1 - start1)));
// // merge sort for 1000
// long start2 = System.nanoTime();
// mergeSort(numbers1000Array);
// long end2 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles, merge"
// + ((end2 - start2)));
// // shell sort for 1000
// long start3 = System.nanoTime();
// shellSort(numbers1000Array);
// long end3 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles, shell"
// + ((end3 - start3)));
// // selection sort for 1000
// long start4 = System.nanoTime();
// selectionSort(numbers1000Array);
// long end4 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles,
// selection " + ((end4 - start4)));
// // bubblesort for 1000
// long start5 = System.nanoTime();
// bubbleSort(numbers1000Array);
// long end5 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles,
// bubble" + ((end5 - start5)));
// }
// }

// 1000 with duplicated numbers
// insertion sort
// long start = System.nanoTime();
// insertionSort(numbers1000DupArray);
// long end = System.nanoTime();
// System.out.println(
// "Time taken in nanoseconds for 1000 doubles, where only 100 are unique,
// insertion" + ((end - start)));
// // quick sort for 1000
// long start1 = System.nanoTime();
// quickSort(numbers1000DupArray);
// long end1 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles, quick"
// + ((end1 - start1)));
// // merge sort for 1000
// long start2 = System.nanoTime();
// mergeSort(numbers1000DupArray);
// long end2 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles, merge"
// + ((end2 - start2)));
// // shell sort for 1000
// long start3 = System.nanoTime();
// shellSort(numbers1000DupArray);
// long end3 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles, shell"
// + ((end3 - start3)));
// // selection sort for 100
// long start4 = System.nanoTime();
// selectionSort(numbers1000DupArray);
// long end4 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles,
// selection" + ((end4 - start4)));
// // bubblesort for 100
// long start5 = System.nanoTime();
// bubbleSort(numbers1000DupArray);
// long end5 = System.nanoTime();
// System.out.println("Time taken in nanoseconds for 1000 random doubles,
// bubble" + ((end5 - start5)));
// //
// 1000 nearly ordered
// Long start = System.nanoTime();
// insertionSort(nearlyOrdered1000);
// long end = System.nanoTime();
// System.out.println("Time taken for 1000 nearly ordered doubles, insertion" +
// (end - start));
// // quick sort for 1000
// long start1 = System.nanoTime();
// quickSort(nearlyOrdered1000);
// long end1 = System.nanoTime();
// System.out.println("Time taken for 1000 nearly ordered doubles, quick" +
// (end1 - start1));
// // merge sort for 1000
// long start2 = System.nanoTime();
// mergeSort(nearlyOrdered1000);
// long end2 = System.nanoTime();
// System.out.println("Time taken for 1000 nearly ordered doubles, merge" +
// (end2 - start2));
// // shell sort for 1000
// long start3 = System.nanoTime();
// shellSort(nearlyOrdered1000);
// long end3 = System.nanoTime();
// System.out.println("Time taken for 1000 nearly ordered doubles, shell" +
// (end3 - start3));
// // selection sort for 100
// long start4 = System.nanoTime();
// selectionSort(nearlyOrdered1000);
// long end4 = System.nanoTime();
// System.out.println("Time taken for 1000 nearly ordered doubles, selection" +
// (end4 - start4));
// // bubblesort for 100
// long start5 = System.nanoTime();
// bubbleSort(nearlyOrdered1000);
// long end5 = System.nanoTime();
// System.out.println("Time taken for 1000 nearly ordered doubles, bubble" +
// (end5 - start5));

// inverse 1000 numbers
// long start = System.nanoTime();
// insertionSort(numbersRev1000);
// long end = System.nanoTime();
// System.out.println("Time taken for 1000 reverse ordered doubles, insertion" +
// (end - start));
// // quick sort for 1000
// long start1 = System.nanoTime();
// quickSort(numbersRev1000);
// long end1 = System.nanoTime();
// System.out.println("Time taken for 1000 reverse ordered doubles, quick" +
// (end1 - start1));
// // merge sort for 1000
// long start2 = System.nanoTime();
// mergeSort(numbersRev1000);
// long end2 = System.nanoTime();
// System.out.println("Time taken for 1000 reverse ordered doubles, merge" +
// (end2 - start2));
// // shell sort for 1000
// long start3 = System.nanoTime();
// shellSort(numbersRev1000);
// long end3 = System.nanoTime();
// System.out.println("Time taken for 1000 reverse ordered doubles, shell" +
// (end3 - start3));
// // selection sort for 100
// long start4 = System.nanoTime();
// selectionSort(numbersRev1000);
// long end4 = System.nanoTime();
// System.out.println("Time taken for 1000 reverse ordered doubles, selection" +
// (end4 - start4));
// // bubblesort for 100
// long start5 = System.nanoTime();
// bubbleSort(numbersRev1000);
// long end5 = System.nanoTime();
// System.out.println("Time taken for 1000 reverse ordered doubles, bubble" +
// (end5 - start5));
//
// sorted numbers
// long start = System.nanoTime();
// insertionSort(numbersSorted);
// long end = java.lang.System.nanoTime();
// System.out.println("Time taken for 1000 ordered doubles, insertion" + (end -
// start));
// // quick sort for 1000
// long start1 = System.nanoTime();
// quickSort(numbersSorted);
// long end1 = System.nanoTime();
// System.out.println("Time taken for 1000 ordered doubles, quick" + (end1 -
// start1));
// // merge sort for 1000
// long start2 = System.nanoTime();
// mergeSort(numbersSorted);
// long end2 = System.nanoTime();
// System.out.println("Time taken for 1000 ordered doubles, merge" + (end2 -
// start2));
// }
// }
// // shell sort for 1000
// long start3 = System.nanoTime();
// shellSort(numbersSorted);
// long end3 = System.nanoTime();
// System.out.println("Time taken for 1000 ordered doubles, shell" + (end3 -
// start3));
// // selection sort for 1000
// long start4 = System.nanoTime();
// selectionSort(numbersSorted);
// long end4 = System.nanoTime();
// System.out.println("Time taken for 1000 ordered doubles, selection" + (end4 -
// start4));
// // bubblesort for 1000/
// long start5 = System.nanoTime();
// bubbleSort(numbersSorted);
// long end5 = System.nanoTime();
// System.out.println("Time taken for 1000 ordered doubles, bubble" + (end5 -
// start5));
//
// }
// }
//
// }
// end class
