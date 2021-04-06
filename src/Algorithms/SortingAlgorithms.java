package Algorithms;

public class SortingAlgorithms {
  public static void main(String[] args) {
    int[] array = new int[4];
    array[0] = 3;
    array[1] = 0;
    array[2] = 1;
    array[3] = 2;

    printArray(array);
    mergeSort(array);
    printArray(array);

    System.out.println(secondLargestEntry(array));
  }

  private static void insertionSort(int[] xs) {
    int pointer = 1;
    while (pointer < xs.length) {
      int position = pointer;
      while (position > 0 && xs[position - 1] > xs[position]) {
        swap(xs, position - 1, position);
        position--;
      }
      pointer++;
    }
  }

  private static void mergeSort(int[] xs) {
    copySubArray(mergeSort(xs, 0, xs.length - 1), xs, 0);
  }

  private static int[] mergeSort(int[] xs, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      ArrayPair split = splitAt(mid, xs);
      split.left = mergeSort(split.left, 0, split.left.length - 1);
      split.right = mergeSort(split.right, 0, split.right.length - 1);
      xs = merge(split);
    }
    return xs;
  }

  private static int[] merge(ArrayPair split) {
    int[] result = new int[split.left.length + split.right.length];
    int leftPointer = 0;
    int rightPointer = 0;
    for (int i = 0; i < result.length; i++) {
      if (rightPointer == split.right.length
              || (leftPointer != split.left.length && split.left[leftPointer] < split.right[rightPointer])) {
        result[i] = split.left[leftPointer];
        leftPointer++;
      } else {
        result[i] = split.right[rightPointer];
        rightPointer++;
      }
    }
    return result;
  }


  private static void swap(int[] xs, int position1, int position2) {
    int temp = xs[position1];
    xs[position1] = xs[position2];
    xs[position2] = temp;
  }

  private static ArrayPair splitAt(int position, int[] xs) {
    int[] left = new int[position + 1];
    int[] right = new int[xs.length - (position + 1)];
    copySubArray(xs, left, 0);
    copySubArray(xs, right, position + 1);
    return new ArrayPair(left, right);
  }

  private static void copySubArray(int[] source, int[] destination, int position) {
    assert position + destination.length < source.length;
    for (int i = 0; i < destination.length; i++) {
      destination[i] = source[position];
      position++;
    }
  }

  private static int secondLargestEntry(int[] xs) {
    pushLargestEntryAtTheEnd(xs);
    // pushing second largest entry at the penultimate position in the array.
    pushLargestEntryAtTheEnd(xs);
    return xs[xs.length - 2];
  }

  private static void pushLargestEntryAtTheEnd(int[] xs) {
    int pointer = 0;
    while (pointer < xs.length - 1) {
      if (xs[pointer] > xs[pointer + 1]) {
        swap(xs, pointer, pointer + 1);
      }
      pointer++;
    }
  }

  private static void printArray(int[] xs) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int item : xs) {
      sb.append(item);
      if (item != xs[xs.length - 1]) {
        sb.append(", ");
      }
    }
    sb.append("]");
    System.out.println(sb.toString());
  }

  private static class ArrayPair {
    int[] left;
    int[] right;

    ArrayPair(int[] left, int[] right) {
      this.left = left;
      this.right = right;
    }
  }
}
