package algorithms;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ListAlgorithmsTest {
  @Test
  public void linearSearchTest() {
    int[] array = getSampleArray();
    Assert.assertEquals(Optional.of(3), ListAlgorithms.linearSearch(5, array));
    Assert.assertEquals(Optional.empty(), ListAlgorithms.linearSearch(8, array));
  }

  @Test
  public void modifiedLinearSearchTest() {
    int[] array = getSampleArray();
    SortingAlgorithms.heapSort(array);
    Assert.assertEquals(Optional.of(2), ListAlgorithms.modifiedLinearSearch(3, array));
  }

  @Test (expected = AssertionError.class)
  public void modifiedLinearSearchExceptionTest() {
    int[] array = getSampleArray();
    ListAlgorithms.modifiedLinearSearch(3, array);
  }

  @Test
  public void binarySearchTest() {
    int[] array = getSampleArray();
    SortingAlgorithms.insertionSort(array);
    Assert.assertEquals(Optional.of(3), ListAlgorithms.binarySearch(5, array));
  }

  @Test (expected = AssertionError.class)
  public void binarySearchExceptionTest() {
    int[] array = getSampleArray();
    ListAlgorithms.binarySearch(5, array);
  }

  @Test
  public void isSortedTest() {
    int[] array = getSampleArray();
    Assert.assertFalse(ListAlgorithms.isSorted(array));
    SortingAlgorithms.mergeSort(array);
    Assert.assertTrue(ListAlgorithms.isSorted(array));
  }

  @Test
  public void printArrayTest() {
    int[] array = getSampleArray();
    Assert.assertEquals("[3, 13, 17, 5, 7, 2, 23, 19, 1, 29]", ListAlgorithms.printArray(array));
  }

  @Test
  public void copyTest() {
    int[] array = getSampleArray();
    int[] array2 = new int[array.length];
    ListAlgorithms.copy(array, array2);
    Assert.assertEquals(ListAlgorithms.printArray(array), ListAlgorithms.printArray(array2));
  }

  @Test (expected = AssertionError.class)
  public void copyExceptionTest() {
    int[] array = getSampleArray();
    int[] array2 = new int[2];
    ListAlgorithms.copy(array, array2);

  }

  @Test
  public void copySubArrayTest1() {
    int[] array = getSampleArray();
    int[] array2 = new int[5];
    ListAlgorithms.copySubArray(array, array2, 4);
    Assert.assertEquals("[7, 2, 23, 19, 1]", ListAlgorithms.printArray(array2));
  }

  @Test
  public void copySubArrayTest2() {
    int[] array = getSampleArray();
    int[] array2 = new int[5];
    ListAlgorithms.copySubArray(array, array2, 7, 2);
    Assert.assertEquals("[0, 0, 19, 1, 29]", ListAlgorithms.printArray(array2));
  }

  @Test
  public void secondLargestEntryTest() {
    int[] array = getSampleArray();
    Assert.assertEquals(23, ListAlgorithms.secondLargestEntry(array));
  }

  private static int[] getSampleArray() {
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
