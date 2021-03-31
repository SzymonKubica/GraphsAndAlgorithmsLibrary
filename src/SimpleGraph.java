public class SimpleGraph {
  private final int[][] adjacencyMatrix;

  public SimpleGraph(int numberOfNodes) {
    adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
  }

  public void addArc(int nodeOrigin, int nodeDestination) {
    assert isValidNode(nodeOrigin);
    assert isValidNode(nodeDestination);

    int originIndex = translateIndex(nodeOrigin);
    int destinationIndex = translateIndex(nodeDestination);

    adjacencyMatrix[originIndex][destinationIndex]++;
    adjacencyMatrix[destinationIndex][originIndex]++;
  }

  public boolean hasArc(int nodeOrigin, int nodeDestination) {
    assert isValidNode(nodeOrigin);
    assert isValidNode(nodeDestination);
    return adjacencyMatrix[translateIndex(nodeOrigin)][translateIndex(nodeDestination)] != 0;
  }

  public int getNumberOfNodes() {
    return adjacencyMatrix.length;
  }

  public boolean isValidNode(int nodeNumber) {
    return nodeNumber <= getNumberOfNodes();
  }

  // By convention we begin numbering nodes starting from 1 but arrays start from 0.
  // Therefore, we need to convert the index;
  public int translateIndex(int nodeIndex) {
    return nodeIndex - 1;
  }

  public int getNodeName(int index) {
    return index + 1;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < getNumberOfNodes(); i++) {
      if (i > 0) {
        sb.append("\n");
      }
      sb.append(getNodeName(i));
      for (int j = 0; j < adjacencyMatrix[i].length; j++) {
        for (int k = 0; k < adjacencyMatrix[i][j]; k++) {
          sb.append(" -> " + getNodeName(j));
        }
      }
    }
    return sb.toString();
  }
}
