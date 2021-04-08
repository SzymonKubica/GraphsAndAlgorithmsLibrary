package algorithms;

public abstract class MergeSort {
  public static void sort(int[] xs) {
    ListAlgorithms.copy(mergeSort(xs, 0, xs.length - 1), xs);
  }

  private static int[] mergeSort(int[] xs, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      Pair<int[], int[]> split = splitAt(mid, xs);
      split.left = mergeSort(split.left, 0, split.left.length - 1);
      split.right = mergeSort(split.right, 0, split.right.length - 1);
      xs = merge(split);
    }
    return xs;
  }

  private static int[] merge(Pair<int[], int[]> split) {
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

  private static Pair<int[], int[]> splitAt(int position, int[] xs) {
    int[] left = new int[position + 1];
    int[] right = new int[xs.length - (position + 1)];
    ListAlgorithms.copySubArray(xs, left, 0);
    ListAlgorithms.copySubArray(xs, right, position + 1);
    return new Pair<>(left, right);
  }
}
