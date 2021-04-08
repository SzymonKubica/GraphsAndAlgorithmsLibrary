package algorithms;

import java.util.Optional;

public abstract class ListAlgorithms {
  public static Optional<Integer> linearSearch(int x, int[] xs) {
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

  public static Optional<Integer> modifiedLinearSearch(int x, int[] xs) {
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

  public static Optional<Integer> binarySearch(int x, int[] xs) {
    try {
      return Optional.of(binarySearch(x, xs, 0, xs.length - 1));
    } catch (ItemNotFoundException e) {
      System.out.println(e.getMessage());
      return Optional.empty();
    }
  }

  private static int binarySearch(int x, int[] xs, int left, int right) throws ItemNotFoundException {
    assert isSorted(xs);
    if (left > right) {
      throw new ItemNotFoundException();
    } else {
      int mid = (left + right) / 2;
      if (xs[mid] == x) {
        return mid;
      } else if (xs[mid] > x) {
        return binarySearch(x, xs, left, mid - 1);
      } else {
        return binarySearch(x, xs, mid + 1, right);
      }
    }
  }

  public static boolean isSorted(int[] xs) {
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
    assert source.length == destination.length : "Arrays need to be of the same length.";
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

  public static int secondLargestEntry(int[] xs) {
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
