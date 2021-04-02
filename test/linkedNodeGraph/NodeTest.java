package linkedNodeGraph;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {
  @Test
  public void equalsTest() {
    Node<Integer> intNode = new Node<>(5);
    Node<Character> characterNode = new Node<>('a');
    Node<Integer> intNode2 = new Node<>(5);
    Assert.assertNotEquals(intNode, characterNode);
    Assert.assertEquals(intNode, intNode2);
  }
}
