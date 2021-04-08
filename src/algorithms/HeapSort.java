package algorithms;

public abstract class HeapSort {
  public static void sort(int[] array) {
    // The array for the heap needs to be indexed starting from 1 for algebraic simplicity.
    int[] heapArray = new int[array.length + 1];
    ListAlgorithms.copySubArray(array, heapArray, 0, 1);
    int heapSize = array.length;
    buildMaxHeap(1, heapSize, heapArray);
    while (heapSize > 1) {
      ListAlgorithms.swap(heapArray, 1, heapSize);
      heapSize--;
      fixMaxHeap(1, heapSize, heapArray);
    }
    ListAlgorithms.copySubArray(heapArray, array, 1, 0);
  }

  private static void buildMaxHeap(int root, int heapSize, int[] array) {
    int left = 2 * root;
    int right = 2 * root + 1;
    if (left <= heapSize) {
      buildMaxHeap(left, heapSize, array);
      if (right <= heapSize) {
        buildMaxHeap(right, heapSize, array);
      }
      fixMaxHeap(root, heapSize, array);
    }
  }

  private static void fixMaxHeap(int root, int heapSize, int[] array) {
    int left = 2 * root;
    int right = 2 * root + 1;
    if (left <= heapSize) {
      int largerSubHeap;
      if (left == heapSize) {
        largerSubHeap = left;
      } else if (array[left] > array[right]) {
        largerSubHeap = left;
      } else {
        // If keys are the same right sub heap is chosen.
        largerSubHeap = right;
      }
      if (array[root] < array[largerSubHeap]) {
        ListAlgorithms.swap(array, root, largerSubHeap);
        fixMaxHeap(largerSubHeap, heapSize, array);
      }
    }
  }
}
