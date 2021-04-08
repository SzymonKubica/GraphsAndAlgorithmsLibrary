package algorithms;

public abstract class InsertionSort {
  public static void sort(int[] xs) {
    int pointer = 1;
    while (pointer < xs.length) {
      int position = pointer;
      while (position > 0 && xs[position - 1] > xs[position]) {
        ListAlgorithms.swap(xs, position - 1, position);
        position--;
      }
      pointer++;
    }
  }
}
