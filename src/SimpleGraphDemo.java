public class SimpleGraphDemo {
  public static void main(String[] args) {
    SimpleGraph test = new SimpleGraph(5);
    test.addArc(1, 2);
    test.addArc(3, 4);
    test.addArc(4, 5);
    test.addArc(3, 5);

    assert test.hasArc(2, 1);
    assert test.hasArc(5, 4);

    System.out.println(test);
    System.out.println(test.getNodeDegree(1));
    System.out.println(test.getNodeDegree(3));

    SimpleGraph eulerGraph = new SimpleGraph(4);
    eulerGraph.addArc(1, 2);
    eulerGraph.addArc(2, 3);
    eulerGraph.addArc(3, 4);
    eulerGraph.addArc(4, 1);

    System.out.println(hasEulerCycle(eulerGraph));

    LinkedGraph<Integer> test2 = new LinkedGraph<>();
    test2.first = new Node<>(1, test2);
    test2.first.adjacentNodes.add(new Node<>(2, test2));
    test2.first.adjacentNodes.add(new Node<>(3, test2));
    test2.first.adjacentNodes.add(new Node<>(4, test2));
    test2.first.adjacentNodes.get(1).adjacentNodes.add((new Node<>(5, test2)));
    test2.first.adjacentNodes.get(1).adjacentNodes.add(new Node<>(6, test2));
    Node<Integer> node = new Node<>(7, test2);
    node.adjacentNodes.add(new Node<>(8, test2));
    node.adjacentNodes.add(new Node<>(9, test2));
    test2.first.adjacentNodes.get(1).adjacentNodes.get(0).adjacentNodes.add(node);

    System.out.println(test2.DFS(test2.first));

    System.out.println(test2.BFS(test2.first));

    System.out.println(test2.hasCycle(test2.first));

    test2.first.adjacentNodes.get(0).adjacentNodes.add(test2.first.adjacentNodes.get(1).adjacentNodes.get(0));

    System.out.println(test2.hasCycle(test2.first));

    System.out.println(test2.shortestPathsFrom(test2.first));

    LinkedGraph<Integer> test3 = new LinkedGraph<>();
    test3.first = new Node<>(1, test3);

    Node<Integer> node2 = new Node<>(2, test2);
    Node<Integer> node3 = new Node<>(3, test3);
    Node<Integer> node4 = new Node<>(4, test3);
    Node<Integer> node5 = new Node<>(5, test3);
    Node<Integer> node6 = new Node<>(6, test3);

    test3.first.adjacentNodes.add(node2);
    node2.adjacentNodes.add(new Node<>(7, test3));
    node2.adjacentNodes.add(node5);
    node3.adjacentNodes.add(node4);
    node4.adjacentNodes.add(node5);
    node6.adjacentNodes.add(node2);

    System.out.println(test3.DFS(test3.first));
    System.out.println(test3.DFS(node6));
    System.out.println(test3.DFS(node3));
    System.out.println(test3.topologicalSort(test3.first));



  }

  public static boolean hasEulerCycle(SimpleGraph g) {
    boolean result = true;
    for (int i = 1; i <= g.getNumberOfNodes(); i++) {
      result = result && (g.getNodeDegree(i) % 2 == 0);
    }
    return result;
  }

  public static boolean hasEulerPath(SimpleGraph g) {
    int oddNodesCounter = 0;
    for (int i = 1; i <= g.getNumberOfNodes(); i++) {
      if (g.getNodeDegree(i) % 2 == 1) {
        oddNodesCounter++;
      }
    }
    return oddNodesCounter == 0 || oddNodesCounter == 2;
  }
}
