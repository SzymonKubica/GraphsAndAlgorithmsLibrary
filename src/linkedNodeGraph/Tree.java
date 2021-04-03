package linkedNodeGraph;

import java.util.ArrayList;

public class Tree<T> extends UndirectedGraph<T> {
  public Node<T> root;

  public void addRoot(Node<T> root) {
    root.adjacentNodes = new ArrayList<>();
    this.root = root;
    super.first = root;
    nodes.add(root);
  }

  @Override
  public void addEdge(Node<T> origin, Node<T> destination) {
    super.addEdge(origin, destination);
    assert !GraphAlgorithms.isCyclic(this) : "The tree needs to be acyclic.";
  }

  @Override
  public void addNode(Node<T> node) {
    node.adjacentNodes = new ArrayList<>();
    super.addNode(node);
  }

  public void addEdge(WeightedEdge<T> edge) {
    this.addEdge(edge.getOrigin(), edge.getDestination());
  }
}
