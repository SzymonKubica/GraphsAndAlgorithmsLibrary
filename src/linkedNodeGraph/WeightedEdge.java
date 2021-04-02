package linkedNodeGraph;

public class WeightedEdge<T> {

  private final Node<T> origin;
  private final Node<T> destination;
  private final int weight;

  public WeightedEdge(Node<T> origin, Node<T> destination, int weight) {
    this.origin = origin;
    this.destination = destination;
    this.weight = weight;
  }

  public Node<T> getOrigin() {
    return origin;
  }

  public int getWeight() {
    return weight;
  }

  public Node<T> getDestination() {
    return destination;
  }
}
