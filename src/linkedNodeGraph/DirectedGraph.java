package linkedNodeGraph;

import java.util.*;

public class DirectedGraph<T> extends UndirectedGraph<T> {
  public DirectedGraph() {
    super.nodes = new HashSet<>();
  }

  @Override
  public void addEdge(Node<T> origin, Node<T> destination) {
    origin.adjacentNodes.add(destination);
  }
}
