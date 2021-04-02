package linkedNodeGraph;

public class Demo {
  public static void main(String[] args) {

    DirectedGraph<Integer> test3 = new DirectedGraph<>();

    Node<Integer> node1 = new Node<>(1);
    Node<Integer> node2 = new Node<>(2);
    Node<Integer> node3 = new Node<>(3);
    Node<Integer> node4 = new Node<>(4);
    Node<Integer> node5 = new Node<>(5);
    Node<Integer> node6 = new Node<>(6);
    Node<Integer> node7 = new Node<>(7);

    test3.addNode(node1);
    test3.addNode(node2);
    test3.addNode(node3);
    test3.addNode(node4);
    test3.addNode(node5);
    test3.addNode(node6);
    test3.addNode(node7);

    test3.first = node1;
    test3.addEdge(node1, node2);
    test3.addEdge(node2, node7);
    test3.addEdge(node2, node5);
    test3.addEdge(node3, node4);
    test3.addEdge(node4, node5);
    test3.addEdge(node6, node2);


    System.out.println(GraphAlgorithm.topologicalSort(test3, test3.first));

  }

}
