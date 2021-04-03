package linkedNodeGraph;

import java.util.*;
import java.util.stream.Collectors;

public class WeightedGraph<T> extends UndirectedGraph<T> {

  private Set<WeightedEdge<T>> edges;

  public WeightedGraph() {
    super.nodes = new HashSet<>();
    edges = new HashSet<>();
  }

  public void addEdgeWithWeight(Node<T> origin, Node<T> destination, int weight) {
    super.addEdge(origin, destination);
    // Edges are undirected
    WeightedEdge<T> edge = new WeightedEdge<>(origin, destination, weight);
    WeightedEdge<T> edge2 = new WeightedEdge<>(destination, origin, weight);
    edges.add(edge);
    edges.add(edge2);
  }

  public Set<WeightedEdge<T>> getEdges() {
    return edges;
  }

  public Set<WeightedEdge<T>> getEdgesStartingAt(Node<T> origin) {
    return edges.stream().filter(edge -> edge.getOrigin().equals(origin)).collect(Collectors.toSet());
  }

  public int getEdgeWeight(Node<T> origin, Node<T> destination) {
    return getEdgesStartingAt(origin).stream()
            .filter(edge -> edge.getDestination().equals(destination))
            .mapToInt(WeightedEdge::getWeight).boxed().collect(Collectors.toList()).get(0);
  }
}
