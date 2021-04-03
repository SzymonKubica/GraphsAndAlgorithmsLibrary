package linkedNodeGraph;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;

public class GraphAlgorithmsTest {
  @Test
  public void breadthFirstSearchTest() {
    DirectedGraph<Integer> testGraph = getIntegerDirectedGraph();
    Assert.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", GraphAlgorithms.BFS(testGraph.first).toString());
  }

  @Test
  public void depthFirstSearchTest() {
    DirectedGraph<Integer> testGraph = getIntegerDirectedGraph();
    Assert.assertEquals("[1, 2, 3, 5, 7, 8, 9, 6, 4]", GraphAlgorithms.DFS(testGraph.first).toString());
  }

  @Test
  public void isCyclicTest() {
    DirectedGraph<Integer> testGraph = getIntegerDirectedGraph();
    Assert.assertFalse(GraphAlgorithms.isCyclic(testGraph));
    // Introducing a cycle.
    testGraph.first.adjacentNodes.get(0).adjacentNodes.add(testGraph.first.adjacentNodes.get(1).adjacentNodes.get(0));
    Assert.assertTrue(GraphAlgorithms.isCyclic(testGraph));
  }

  @Test
  public void shortestPathsFromTest() {
    DirectedGraph<Integer> testGraph = getIntegerDirectedGraph();
    String expectedOutput = "{(Node: 1)=0, (Node: 2)=1, (Node: 3)=1, (Node: 4)=1, (Node: 5)=2, " +
            "(Node: 6)=2, (Node: 7)=3, (Node: 8)=4, (Node: 9)=4}";
    Assert.assertEquals(expectedOutput, GraphAlgorithms.shortestPathsFrom(testGraph.first).toString());
  }

  @Test
  public void topologicalSortTest() {
    DirectedGraph<Integer> graph = getGraphForTopologicalSort();
    String expected = "[6, 3, 4, 1, 2, 5, 7]";
    Assert.assertEquals(expected, GraphAlgorithms.topologicalSort(graph, graph.first).toString());
  }

  @Test
  public void primsMSTTest() {
    WeightedGraph<Integer> graphForMST = getWeightedGraphForMST();
    Tree<Integer> minimumSpanningTree = GraphAlgorithms.MST_Prim(graphForMST, graphForMST.first);
    Assert.assertEquals(
            "(Node: 1) -> (Node: 2) \n" +
                    "(Node: 2) -> (Node: 1) -> (Node: 3) \n" +
                    "(Node: 3) -> (Node: 2) -> (Node: 4) \n" +
                    "(Node: 4) -> (Node: 3) \n",
            minimumSpanningTree.toString()
    );
  }

  @Test
  public void kruskalMSTTest() {
    WeightedGraph<Integer> graphForMST = getWeightedGraphForMST();
    Tree<Integer> minimumSpanningTree = GraphAlgorithms.MST_Kruskal(graphForMST, graphForMST.first);
    Assert.assertEquals(
            "(Node: 1) -> (Node: 2) -> (Node: 4) \n" +
                    "(Node: 2) -> (Node: 1) \n" +
                    "(Node: 3) -> (Node: 4) \n" +
                    "(Node: 4) -> (Node: 3) -> (Node: 1) \n",
            minimumSpanningTree.toString()
    );
  }

  @Test
  public void shortestPathDijkstraTest() {
    WeightedGraph<Integer> graphForDijkstra = getWeightedGraphForDijkstraShortestPath();

    String expected = "[(Node: 1), (Node: 3), (Node: 4), (Node: 5), (Node: 6), (Node: 7)]";
    Assert.assertEquals(expected,
            GraphAlgorithms.shortestPathFromTo(
                    graphForDijkstra.first,
                    graphForDijkstra.nodes.stream().filter(node -> node.element == 7)
                            .collect(Collectors.toList()).get(0), graphForDijkstra).toString());
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

  private DirectedGraph<Integer> getGraphForTopologicalSort() {
    DirectedGraph<Integer> graph = new DirectedGraph<>();

    Node<Integer> node1 = new Node<>(1);
    Node<Integer> node2 = new Node<>(2);
    Node<Integer> node3 = new Node<>(3);
    Node<Integer> node4 = new Node<>(4);
    Node<Integer> node5 = new Node<>(5);
    Node<Integer> node6 = new Node<>(6);
    Node<Integer> node7 = new Node<>(7);

    graph.addNode(node1);
    graph.addNode(node2);
    graph.addNode(node3);
    graph.addNode(node4);
    graph.addNode(node5);
    graph.addNode(node6);
    graph.addNode(node7);

    graph.first = node1;
    graph.addEdge(node1, node2);
    graph.addEdge(node2, node7);
    graph.addEdge(node2, node5);
    graph.addEdge(node3, node4);
    graph.addEdge(node4, node5);
    graph.addEdge(node6, node2);

    return graph;
  }

  private WeightedGraph<Integer> getWeightedGraphForMST() {
    WeightedGraph<Integer> graph = new WeightedGraph<>();

    Node<Integer> node1 = new Node<>(1, graph);
    Node<Integer> node2 = new Node<>(2, graph);
    Node<Integer> node3 = new Node<>(3, graph);
    Node<Integer> node4 = new Node<>(4, graph);

    graph.first = node1;
    graph.addEdgeWithWeight(node1, node2, 3);
    graph.addEdgeWithWeight(node2, node3, 4);
    graph.addEdgeWithWeight(node3, node4, 2);
    graph.addEdgeWithWeight(node4, node1, 4);
    graph.addEdgeWithWeight(node1, node3, 5);
    return graph;
  }

  private WeightedGraph<Integer> getWeightedGraphForDijkstraShortestPath() {
    WeightedGraph<Integer> graph = new WeightedGraph<>();

    Node<Integer> node1 = new Node<>(1, graph);
    Node<Integer> node2 = new Node<>(2, graph);
    Node<Integer> node3 = new Node<>(3, graph);
    Node<Integer> node4 = new Node<>(4, graph);
    Node<Integer> node5 = new Node<>(5, graph);
    Node<Integer> node6 = new Node<>(6, graph);
    Node<Integer> node7 = new Node<>(7, graph);

    graph.first = node1;
    graph.addEdgeWithWeight(node1, node2, 10);
    graph.addEdgeWithWeight(node1, node4, 8);
    graph.addEdgeWithWeight(node1, node3, 3);
    graph.addEdgeWithWeight(node2, node6, 7);
    graph.addEdgeWithWeight(node2, node4, 9);
    graph.addEdgeWithWeight(node3, node4, 4);
    graph.addEdgeWithWeight(node4, node5, 6);
    graph.addEdgeWithWeight(node5, node6, 3);
    graph.addEdgeWithWeight(node6, node7, 4);

    return graph;

  }
}
