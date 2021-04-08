package algorithms;

public abstract class QuickSort {
  public static void sort(int[] xs) {
    quickSort(xs, 0, xs.length - 1);
  }

  private static void quickSort(int[] xs, int left, int right) {
    if (left < right) {
      int s = split(xs, left, right);
      quickSort(xs, left, s - 1);
      quickSort(xs, s + 1, right);
    }
  }

  private static int split(int[] xs, int left, int right) {
    assert left < right;
    int pivot = xs[left];
    int leftPointer = left + 1;
    int rightPointer = right;

    while (leftPointer <= rightPointer) {
      if (xs[leftPointer] <= pivot) { // The element is in the correct sub array.
        leftPointer++;
      } else { // The element is greater than the pivot and needs to be swapped.
        ListAlgorithms.swap(xs, leftPointer, rightPointer);
        rightPointer--;
      }
    }
    ListAlgorithms.swap(xs, left, rightPointer); // Inserting the pivot in the correct place.
    return rightPointer;
  }
}
