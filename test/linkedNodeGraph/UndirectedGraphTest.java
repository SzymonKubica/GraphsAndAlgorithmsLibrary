package linkedNodeGraph;

import org.junit.Assert;
import org.junit.Test;

public class UndirectedGraphTest {
  @Test
  public void edgesGoBothWaysTest() {
    Graph<Integer> test = new UndirectedGraph<>();
    Node<Integer> node1 = new Node<>(1, test);
    Node<Integer> node2 = new Node<>(2, test);

    test.addEdge(node1, node2);

    Assert.assertTrue(node1.adjacentNodes.contains(node2));
    Assert.assertTrue(node2.adjacentNodes.contains(node1));
  }
}
