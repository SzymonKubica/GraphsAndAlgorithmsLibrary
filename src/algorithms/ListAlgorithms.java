package algorithms;

import java.util.Optional;

public class ListAlgorithms {
  public static void main(String[] args) {
    int[] array = new int[3];
    array[0] = 2;
    array[1] = 1;
    array[2] = 3;
    System.out.println(isSorted(array));
    System.out.println(linearSearch(2, array).get());
    System.out.println(modifiedLinearSearch(3, array).get());
    System.out.println(binarySearch(1, array).get());
    System.out.println(secondLargestEntry(array));
  }

  private static Optional<Integer> linearSearch(int x, int[] xs) {
    try {
      return Optional.of(linearSearchUnsafe(x, xs));
    } catch (ItemNotFoundException e) {
      System.out.println(e.getMessage());
      return Optional.empty();
    }
  }

  private static int linearSearchUnsafe(int x, int[] xs) throws ItemNotFoundException {
    int pointer = 0;
    while (pointer < xs.length) {
      if (xs[pointer] == x) {
        return pointer;
      }
      pointer++;
    }
    throw new ItemNotFoundException();
  }

  private static Optional<Integer> modifiedLinearSearch(int x, int[] xs) {
    try {
      return Optional.of(modifiedLinearSearchUnsafe(x, xs));
    } catch (ItemNotFoundException e) {
      System.out.println(e.getMessage());
      return Optional.empty();
    }
  }

  private static int modifiedLinearSearchUnsafe(int x, int[] xs) throws ItemNotFoundException {
    assert isSorted(xs);
    int pointer = 0;
    while (pointer < xs.length) {
      if (xs[pointer] == x) {
        return pointer;
      } else if (xs[pointer] > x) {
        throw new ItemNotFoundException();
      } else {
        pointer++;
      }
    }
    throw new ItemNotFoundException();
  }

  private static Optional<Integer> binarySearch(int x, int[] xs) {
    try {
      return Optional.of(binarySearchInner(x, xs, 0, xs.length - 1));
    } catch (ItemNotFoundException e) {
      System.out.println(e.getMessage());
      return Optional.empty();
    }
  }

  private static int binarySearchInner(int x, int[] xs, int left, int right) throws ItemNotFoundException {
    if (left > right) {
      throw new ItemNotFoundException();
    } else {
      int mid = (left + right) / 2;
      if (xs[mid] == x) {
        return mid;
      } else if (xs[mid] > x) {
        return binarySearchInner(x, xs, left, mid - 1);
      } else {
        return binarySearchInner(x, xs, mid + 1, right);
      }
    }
  }

  private static boolean isSorted(int[] xs) {
    int pointer = 0;
    boolean isSorted = true;
    while (pointer < xs.length - 1) {
      isSorted = isSorted && xs[pointer] < xs[pointer + 1];
      pointer++;
    }
    return isSorted;
  }

  public static void swap(int[] xs, int position1, int position2) {
    int temp = xs[position1];
    xs[position1] = xs[position2];
    xs[position2] = temp;
  }

  public static String printArray(int[] xs) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int item : xs) {
      sb.append(item);
      if (item != xs[xs.length - 1]) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  public static void copy(int[] source, int[] destination) {
    copySubArray(source, destination, 0);
  }

  public static void copySubArray(int[] source, int[] destination, int position) {
    assert position + destination.length <= source.length;
    for (int i = 0; i < destination.length; i++) {
      destination[i] = source[position];
      position++;
    }
  }

  public static void copySubArray(int[] source, int[] destination, int sourcePointer, int destinationPointer) {
    assert destinationPointer + (source.length - sourcePointer) <= destination.length;
    while (destinationPointer < destination.length){
      destination[destinationPointer] = source[sourcePointer];
      destinationPointer++;
      sourcePointer++;
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
        ListAlgorithms.swap(xs, pointer, pointer + 1);
      }
      pointer++;
    }
  }
}
