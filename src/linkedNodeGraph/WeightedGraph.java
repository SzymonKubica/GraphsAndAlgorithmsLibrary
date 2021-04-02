package linkedNodeGraph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WeightedGraph<T> extends UndirectedGraph<T> {
  private Set<WeightedEdge<T>> edges;

  public WeightedGraph() {
    super.nodes = new HashSet<>();
    edges = new HashSet<>();
  }

  public void addEdgeWithWeight(Node<T> origin, Node<T> destination, int weight) {
    super.addEdge(origin, destination);
    WeightedEdge<T> edge = new WeightedEdge<>(origin, destination, weight);
    edges.add(edge);
  }


  public Set<WeightedEdge<T>> getEdgesStartingAt(Node<T> origin) {
    return edges.stream().filter(edge -> edge.getOrigin() == origin).collect(Collectors.toSet());
  }

  public Tree<T> MST(Node<T> start) {
    Tree<T> minST = new Tree<>();
    minST.root = start;
    Set<Node<T>> fringe = new HashSet<>();
    fringe.addAll(start.adjacentNodes);
    while (!fringe.isEmpty()) {
      WeightedEdge<T> shortest = getMinimumWeightEdge(minST, fringe);
      Node<T> closest = getClosestNode(minST, fringe);
      minST.addNode(closest);
      minST.addEdge(shortest);
      for (Node<T> neighbour : closest.adjacentNodes) {
        if (!fringe.contains(neighbour)) {
          fringe.add(neighbour);
        }
      }
    }
    return minST;
  }

  private WeightedEdge<T> getMinimumWeightEdge(Tree<T> mst, Set<Node<T>> fringe) {
    return edges.stream().filter(edge -> connectsTreeWithFringe(edge, mst, fringe))
            .min(Comparator.comparingInt(WeightedEdge::getWeight)).get();
  }

  private Node<T> getClosestNode(Tree<T> mst, Set<Node<T>> fringe) {
    WeightedEdge<T> shortest = getMinimumWeightEdge(mst, fringe);
    if (fringe.contains(shortest.getDestination())) {
      return shortest.getDestination();
    } else {
      return shortest.getOrigin();
    }
  }

  private boolean connectsTreeWithFringe(WeightedEdge<T> edge, Tree<T> mst, Set<Node<T>> fringe) {
    return mst.nodes.contains(edge.getOrigin()) && fringe.contains(edge.getDestination())
            || mst.nodes.contains(edge.getDestination()) && fringe.contains(edge.getOrigin());
  }

}
