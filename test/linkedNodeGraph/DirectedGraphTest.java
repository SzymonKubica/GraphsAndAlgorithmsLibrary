package linkedNodeGraph;

import org.junit.Assert;
import org.junit.Test;

public class DirectedGraphTest {
  @Test
  public void edgesGoOneWayTest() {
    Graph<Integer> test = new DirectedGraph<>();
    Node<Integer> node1 = new Node<>(1, test);
    Node<Integer> node2 = new Node<>(2, test);

    test.addEdge(node1, node2);

    Assert.assertTrue(node1.adjacentNodes.contains(node2));
    Assert.assertFalse(node2.adjacentNodes.contains(node1));
  }
}
