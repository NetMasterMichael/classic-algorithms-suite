package com.github.netmastermichael.algorithmsuitex.algorithms.classic.sorting;

import java.util.Arrays;

/**
 * MergeSort is a class that implements the merge sort algorithm for sorting an array of integers.
 * It implements the SortingAlgorithm interface and is designed to primarily be used with a
 * controller, but can be used independently.
 * <p>
 * Time Complexity Best Case: O(n log n)<br>
 * </p>
 * <p>
 * Time Complexity Average Case: O(n log n)
 * </p>
 * <p>
 * Time Complexity Worst Case: O(n log n)<br>
 * </p>
 * 
 * @author Michael Goodwin (NetMasterMichael)
 */
public class MergeSort implements SortingAlgorithm {

  /** Array that is currently being worked on by the sorting algorithm. */
  private int[] mainArray;

  /** SortingAlgorithmMetrics object for tracking metrics of the algorithm. */
  private SortingAlgorithmMetrics metrics;

  /**
   * ManualSorter object for sorting an array step by step.
   * <p>
   * Unlike the other sorting algorithm classes, the manualSorter object is a class-level field in
   * the MergeSort object, due to the recursive nature of the mergeSort and merge methods. This does
   * not change how it works functionally, and like the other classes, there are no getter or setter
   * methods, except for preComputeManualSort().
   * </p>
   */
  private ManualSorter manualSorter;

  /**
   * Array that is currently being worked on by preComputeManualSort().
   * <p>
   * Unlike the other sorting algorithm classes, the mainArrayDuplicate array is a class-level field
   * in the MergeSort object, due to the recursive nature of the mergeSort and merge methods. This
   * does not change how it works functionally, and like the other classes, there are no getter or
   * setter methods.
   * </p>
   */
  private int[] mainArrayDuplicate;

  /**
   * Constructor for creating a MergeSort object to sort an array of integers.
   * 
   * @param inputArray Array to initialise into mainArray for sorting
   */
  public MergeSort(int[] inputArray) {
    setMainArray(inputArray);
  }

  /**
   * Gets the array currently held inside the MergeSort object.
   *
   * @return Array currently inside of MergeSort instance
   */
  @Override
  public int[] getMainArray() {
    return mainArray;
  }

  /**
   * Sets the array inside the MergeSort object to the provided array.
   * 
   * @param inputArray New array to initialise into mainArray for sorting
   */
  @Override
  public void setMainArray(int[] inputArray) {
    if (inputArray == null) {
      this.mainArray = new int[] {};
    } else {
      this.mainArray = inputArray;
    }
    this.metrics = new SortingAlgorithmMetrics();
  }

  /**
   * Gets the SortingAlgorithmMetrics object containing performance metrics from this MergeSort
   * object.
   * 
   * @return SortingAlgorithmMetrics object containing performance metrics
   */
  @Override
  public SortingAlgorithmMetrics getMetrics() {
    return metrics;
  }

  /**
   * Sorts the array inside mainArray using the merge sort algorithm with optimisations. Use this
   * method when assessing raw algorithm performance. Acts as a wrapper for the mergeSort() method.
   */
  @Override
  public void sort() {
    mergeSort(0, mainArray.length - 1);
  }

  private void mergeSort(int left, int right) {
    if (left < right) {
      // Find the midpoint in the array
      int midpoint = (left + right) / 2;

      // Call mergeSort() on the first and second halves
      mergeSort(left, midpoint);
      mergeSort(midpoint + 1, right);

      // Merge the sorted halves
      merge(left, midpoint, right);
    }
  }

  private void merge(int left, int midpoint, int right) {
    // Find size of two subarrays to be merged
    int leftArraySize = midpoint - left + 1;
    int rightArraySize = right - midpoint;

    // Create temporary arrays
    int[] leftArray = new int[leftArraySize];
    int[] rightArray = new int[rightArraySize];

    // Copy corresponding values from mainArray to temporary arrays
    for (int i = 0; i < leftArraySize; i++) {
      leftArray[i] = mainArray[left + i];
    }
    for (int i = 0; i < rightArraySize; i++) {
      rightArray[i] = mainArray[midpoint + i + 1];
    }

    // Merge the arrays into one and sort at the same time
    int leftIndex = 0;
    int rightIndex = 0;
    int targetIndex = left;

    while (leftIndex < leftArraySize && rightIndex < rightArraySize) {
      if (leftArray[leftIndex] <= rightArray[rightIndex]) {
        mainArray[targetIndex] = leftArray[leftIndex];
        leftIndex++;
      } else {
        mainArray[targetIndex] = rightArray[rightIndex];
        rightIndex++;
      }
      targetIndex++;
    }

    // Arrays are now merged but there may be leftover values in the temporary
    // arrays, so copy them to mainArray
    while (leftIndex < leftArraySize) {
      mainArray[targetIndex] = leftArray[leftIndex];
      leftIndex++;
      targetIndex++;
    }
    while (rightIndex < rightArraySize) {
      mainArray[targetIndex] = rightArray[rightIndex];
      rightIndex++;
      targetIndex++;
    }
  }

  /**
   * Sorts the array inside mainArray using the merge sort algorithm with optimisations, while
   * keeping track of metrics. Use this method when assessing algorithm optimisation.
   */
  @Override
  public void sortWithMetrics() {
    mergeSortWithMetrics(0, mainArray.length - 1);
  }

  private void mergeSortWithMetrics(int left, int right) {
    metrics.incrementComparisons();
    if (left < right) {
      // Find the midpoint in the array
      int midpoint = (left + right) / 2;

      // Call mergeSort() on the first and second halves
      mergeSortWithMetrics(left, midpoint);
      mergeSortWithMetrics(midpoint + 1, right);

      // Merge the sorted halves
      mergeWithMetrics(left, midpoint, right);
    }
  }

  private void mergeWithMetrics(int left, int midpoint, int right) {
    // Find size of two subarrays to be merged
    int leftArraySize = midpoint - left + 1;
    int rightArraySize = right - midpoint;

    // Create temporary arrays
    int[] leftArray = new int[leftArraySize];
    int[] rightArray = new int[rightArraySize];

    // Copy corresponding values from mainArray to temporary arrays
    // These for loops have been rewritten as while loops for more accurate metrics
    // tracking
    int i = 0;
    while (true) {
      metrics.incrementComparisons();
      if (!(i < leftArraySize)) {
        break;
      }

      metrics.increaseArrayAccesses(2);
      metrics.incrementSwaps();
      leftArray[i] = mainArray[left + i];
      i++;
    }

    i = 0;
    while (true) {
      metrics.incrementComparisons();
      if (!(i < rightArraySize)) {
        break;
      }

      metrics.increaseArrayAccesses(2);
      metrics.incrementSwaps();
      rightArray[i] = mainArray[midpoint + i + 1];
      i++;
    }

    // Merge the arrays into one and sort at the same time
    int leftIndex = 0;
    int rightIndex = 0;
    int targetIndex = left;

    while (true) {
      // Condition for while loop, with comparisons counted
      metrics.increaseComparisons(2);
      if (!(leftIndex < leftArraySize && rightIndex < rightArraySize)) {
        break;
      }

      metrics.incrementComparisons(); // Comparison for if statement
      metrics.increaseArrayAccesses(4); // Array accesses in if statement & swap
      metrics.incrementSwaps();
      if (leftArray[leftIndex] <= rightArray[rightIndex]) {
        mainArray[targetIndex] = leftArray[leftIndex];
        leftIndex++;
      } else {
        mainArray[targetIndex] = rightArray[rightIndex];
        rightIndex++;
      }
      targetIndex++;
    }

    // Arrays are now merged but there may be leftover values in the temporary
    // arrays, so copy them to mainArray
    while (true) {
      // Condition for while loop, with comparisons counted
      metrics.incrementComparisons();
      if (!(leftIndex < leftArraySize)) {
        break;
      }
      metrics.increaseArrayAccesses(2);
      metrics.incrementSwaps();
      mainArray[targetIndex] = leftArray[leftIndex];
      leftIndex++;
      targetIndex++;
    }
    while (true) {
      // Condition for while loop, with comparisons counted
      metrics.incrementComparisons();
      if (!(rightIndex < rightArraySize)) {
        break;
      }
      metrics.increaseArrayAccesses(2);
      metrics.incrementSwaps();
      mainArray[targetIndex] = rightArray[rightIndex];
      rightIndex++;
      targetIndex++;
    }
  }

  /**
   * Creates a separate instance of this algorithm as a ManualSorter object with an independent copy
   * of mainArray, which will be able to sort the array by stepping through a queue of operations
   * that correspond with the merge sort algorithm.
   * 
   * @return ManualSorter object queued with operations of merge sort algorithm
   */
  @Override
  public ManualSorter preComputeManualSort() {
    manualSorter = new ManualSorter(Arrays.copyOf(mainArray, mainArray.length));
    mainArrayDuplicate = Arrays.copyOf(mainArray, mainArray.length);
    manualSorter.setUsingTemporaryArraysStatus(true);
    mergeSortForManualSorter(0, mainArrayDuplicate.length - 1);
    return manualSorter;
  }

  private void mergeSortForManualSorter(int left, int right) {
    if (left < right) {
      // Find the midpoint in the array
      int midpoint = (left + right) / 2;

      // Call mergeSort() on the first and second halves
      mergeSortForManualSorter(left, midpoint);
      mergeSortForManualSorter(midpoint + 1, right);

      // Merge the sorted halves
      mergeForManualSorter(left, midpoint, right);
    }
  }

  private void mergeForManualSorter(int left, int midpoint, int right) {
    // Find size of two subarrays to be merged
    int leftArraySize = midpoint - left + 1;
    int rightArraySize = right - midpoint;

    // Create temporary arrays
    manualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, leftArraySize, 1);
    manualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, rightArraySize, 2);

    // Copy corresponding values from mainArray to temporary left array
    int[] leftArray = new int[leftArraySize];
    for (int i = 0; i < leftArraySize; i++) {
      leftArray[i] = mainArrayDuplicate[left + i];
      manualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, mainArray[left + i], i,
          1);
    }

    // Copy corresponding values from mainArray to temporary right array
    int[] rightArray = new int[rightArraySize];
    for (int i = 0; i < rightArraySize; i++) {
      rightArray[i] = mainArrayDuplicate[midpoint + i + 1];
      manualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL,
          mainArray[midpoint + i + 1], i, 2);
    }

    // Merge the arrays into one and sort at the same time
    int leftIndex = 0;
    int rightIndex = 0;
    int targetIndex = left;

    while (leftIndex < leftArraySize && rightIndex < rightArraySize) {
      manualSorter.enqueueOperation(SortingAlgorithmOperation.COMPARE, leftIndex, rightIndex, 1, 2);
      if (leftArray[leftIndex] <= rightArray[rightIndex]) {
        manualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, leftArray[leftIndex],
            targetIndex, 0);
        mainArrayDuplicate[targetIndex] = leftArray[leftIndex];
        leftIndex++;
      } else {
        manualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL,
            rightArray[rightIndex], targetIndex, 0);
        mainArrayDuplicate[targetIndex] = rightArray[rightIndex];
        rightIndex++;
      }
      targetIndex++;
    }

    // Arrays are now merged but there may be leftover values in the temporary
    // arrays, so copy them to mainArray
    while (leftIndex < leftArraySize) {
      manualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, leftArray[leftIndex],
          targetIndex, 0);
      mainArrayDuplicate[targetIndex] = leftArray[leftIndex];
      leftIndex++;
      targetIndex++;
    }
    while (rightIndex < rightArraySize) {
      manualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, rightArray[rightIndex],
          targetIndex, 0);
      mainArrayDuplicate[targetIndex] = rightArray[rightIndex];
      rightIndex++;
      targetIndex++;
    }
    manualSorter.enqueueOperation(SortingAlgorithmOperation.DELETE_ARRAY, 1, 0);
    manualSorter.enqueueOperation(SortingAlgorithmOperation.DELETE_ARRAY, 2, 0);
  }
}
