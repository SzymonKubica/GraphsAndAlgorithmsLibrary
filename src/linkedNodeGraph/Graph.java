package linkedNodeGraph;

import java.util.Set;

public interface Graph<T> {

  void addEdge(Node<T> origin, Node<T> destination);

  void addNode(Node<T> node);

  Set<Node<T>> getNodes();

  int getNumberOfNodes();

}
