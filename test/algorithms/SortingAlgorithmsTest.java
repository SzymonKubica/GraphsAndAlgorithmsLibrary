package algorithms;

import org.junit.Assert;
import org.junit.Test;

public class SortingAlgorithmsTest {
  @Test
  public void insertionSortTest() {
    int[] array = getSampleArray1();
    SortingAlgorithms.insertionSort(array);
    Assert.assertEquals("[1, 2, 3, 5, 7, 13, 17, 19, 23, 29]", ListAlgorithms.printArray(array));
  }

  @Test
  public void mergeSortTest() {
    int[] array = getSampleArray1();
    SortingAlgorithms.mergeSort(array);
    Assert.assertEquals("[1, 2, 3, 5, 7, 13, 17, 19, 23, 29]", ListAlgorithms.printArray(array));
  }

  @Test
  public void quickSortTest() {
    int[] array = getSampleArray1();
    SortingAlgorithms.quickSort(array);
    Assert.assertEquals("[1, 2, 3, 5, 7, 13, 17, 19, 23, 29]", ListAlgorithms.printArray(array));
  }

  @Test
  public void heapSortTest() {
    int[] array = getSampleArray1();
    SortingAlgorithms.heapSort(array);
    Assert.assertEquals("[1, 2, 3, 5, 7, 13, 17, 19, 23, 29]", ListAlgorithms.printArray(array));
  }

  private int[] getSampleArray1() {
    int[] array = new int[10];
    array[0] = 3;
    array[1] = 13;
    array[2] = 17;
    array[3] = 5;
    array[4] = 7;
    array[5] = 2;
    array[6] = 23;
    array[7] = 19;
    array[8] = 1;
    array[9] = 29;
    return array;
  }
}
