package linkedNodeGraph;

import org.junit.Assert;
import org.junit.Test;

public class GraphAlgorithmTest {
  @Test
  public void breadthFirstSearchTest() {
    DirectedGraph<Integer> testGraph = getIntegerDirectedGraph();
    Assert.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", GraphAlgorithm.BFS(testGraph.first).toString());
  }

  @Test
  public void depthFirstSearchTest() {
    DirectedGraph<Integer> testGraph = getIntegerDirectedGraph();
    Assert.assertEquals("[1, 2, 3, 5, 7, 8, 9, 6, 4]", GraphAlgorithm.DFS(testGraph.first).toString());
  }

  @Test
  public void isCyclicTest() {
    DirectedGraph<Integer> testGraph = getIntegerDirectedGraph();
    Assert.assertFalse(GraphAlgorithm.isCyclic(testGraph));
    // Introducing a cycle.
    testGraph.first.adjacentNodes.get(0).adjacentNodes.add(testGraph.first.adjacentNodes.get(1).adjacentNodes.get(0));
    Assert.assertTrue(GraphAlgorithm.isCyclic(testGraph));
  }

  @Test
  public void shortestPathsFromTest() {
    DirectedGraph<Integer> testGraph = getIntegerDirectedGraph();
    String expectedOutput = "{(Node: 1)=0, (Node: 2)=1, (Node: 3)=1, (Node: 4)=1, (Node: 5)=2, " +
            "(Node: 6)=2, (Node: 7)=3, (Node: 8)=4, (Node: 9)=4}";
    Assert.assertEquals(expectedOutput, GraphAlgorithm.shortestPathsFrom(testGraph.first).toString());
  }

  private DirectedGraph<Integer> getIntegerDirectedGraph() {
    DirectedGraph<Integer> graph = new DirectedGraph<>();

    Node<Integer> node1 = new Node<>(1, graph);
    Node<Integer> node2 = new Node<>(2, graph);
    Node<Integer> node3 = new Node<>(3, graph);
    Node<Integer> node4 = new Node<>(4, graph);
    Node<Integer> node5 = new Node<>(5, graph);
    Node<Integer> node6 = new Node<>(6, graph);
    Node<Integer> node7 = new Node<>(7, graph);
    Node<Integer> node8 = new Node<>(8, graph);
    Node<Integer> node9 = new Node<>(9, graph);
    graph.first = new Node<>(1);
    graph.first = node1;

    graph.addEdge(node1, node2);
    graph.addEdge(node1, node3);
    graph.addEdge(node1, node4);
    graph.addEdge(node3, node5);
    graph.addEdge(node3, node6);
    graph.addEdge(node5, node7);
    graph.addEdge(node7, node8);
    graph.addEdge(node7, node9);

    return graph;
  }
}
