package linkedNodeGraph;

public class Tree<T> extends UndirectedGraph<T> {
  public Node<T> root;

  @Override
  public void addEdge(Node<T> origin, Node<T> destination) {
    super.addEdge(origin, destination);
    assert !GraphAlgorithm.isCyclic(this) : "The tree needs to be acyclic.";
  }

  public void addEdge(WeightedEdge<T> edge) {
    this.addEdge(edge.getOrigin(), edge.getDestination());
  }
}
