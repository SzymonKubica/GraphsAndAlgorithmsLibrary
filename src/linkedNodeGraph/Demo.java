package linkedNodeGraph;

public class Demo {
  public static void main(String[] args) {
    WeightedGraph<Integer> test = new WeightedGraph<>();

    Node<Integer> node1 = new Node<>(1, test);
    Node<Integer> node2 = new Node<>(2, test);
    Node<Integer> node3 = new Node<>(3, test);
    Node<Integer> node4 = new Node<>(4, test);

    test.addEdgeWithWeight(node1, node2, 3);
    test.addEdgeWithWeight(node2, node3, 4);
    test.addEdgeWithWeight(node3, node4, 2);
    test.addEdgeWithWeight(node4, node1, 4);
    test.addEdgeWithWeight(node1, node3, 5);



    Tree<Integer> minimumSpanningTree = test.MST(node1);
    System.out.println(GraphAlgorithm.BFS(minimumSpanningTree.root));
    // TODO: Add MST algorithm to GraphAlgorithms class.
    // TODO: Migrate this MST test into the test suite.


  }

}
