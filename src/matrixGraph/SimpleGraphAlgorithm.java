package matrixGraph;

public class SimpleGraphAlgorithm {

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
