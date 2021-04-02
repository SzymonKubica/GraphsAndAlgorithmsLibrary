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


  public Set<WeightedEdge<T>> getEdgesStartingAt(Node<T> origin) {
    return edges.stream().filter(edge -> edge.getOrigin().equals(origin)).collect(Collectors.toSet());
  }

  public Set<WeightedEdge<T>> getEdgesStartingInTheTreeEndingAt(Node<T> destination, Tree<T> mst) {
    return edges.stream().filter(edge -> edge.getDestination().equals(destination) && mst.nodes.contains(edge.getOrigin()))
            .collect(Collectors.toSet());
  }

  private int getEdgeWeight(Node<T> origin, Node<T> destination) {
    return getEdgesStartingAt(origin).stream()
            .filter(edge -> edge.getDestination().equals(destination))
            .mapToInt(WeightedEdge::getWeight).boxed().collect(Collectors.toList()).get(0);
  }

  private WeightedEdge<T> getMinimumEdgeEndingAt(Node<T> destination, Map<Node<T>, Integer> startingNodeWeightMap, Tree<T> mst) {
    Set<WeightedEdge<T>> edgesEndingAtDestination = getEdgesStartingInTheTreeEndingAt(destination, mst);
    return edgesEndingAtDestination.stream()
            .min(Comparator.comparingInt(WeightedEdge::getWeight)).get();
  }

  public Tree<T> MST(Node<T> start) {
    Tree<T> minST = new Tree<>();
    Map<Node<T>, Integer> weightOfNodesMap = new HashMap<>();
    weightOfNodesMap.put(start, 0);
    minST.root = start;
    minST.nodes.add(start);
    Set<Node<T>> fringe = new HashSet<>();
    for (Node<T> neighbour : start.adjacentNodes) {
      fringe.add(neighbour);
      weightOfNodesMap.put(neighbour, getEdgeWeight(start, neighbour));
    }
    while (!fringe.isEmpty()) {
      Node<T> closestNode = getMinimumWeightNode(fringe, weightOfNodesMap);
      WeightedEdge<T> minWeightEdge = getMinimumEdgeEndingAt(closestNode, weightOfNodesMap, minST);
      minST.addNode(closestNode);
      fringe.remove(closestNode);
      minST.addEdge(minWeightEdge);
      for (Node<T> neighbour : closestNode.adjacentNodes) {
        if (!minST.nodes.contains(neighbour)) {
          if (fringe.contains(neighbour)) {
            // Updating candidate arc.
            if (getEdgeWeight(closestNode, neighbour) < weightOfNodesMap.get(neighbour)) {
              weightOfNodesMap.put(neighbour, getEdgeWeight(closestNode, neighbour));
            }
          } else {
            // neighbour is unseen.
            fringe.add(neighbour);
            weightOfNodesMap.put(neighbour, getEdgeWeight(closestNode, neighbour));
          }
        }
      }
    }
    return minST;
  }

  private Node<T> getMinimumWeightNode(Set<Node<T>> fringe, Map<Node<T>, Integer> weightOfNodesMap) {
    return fringe.stream().min(Comparator.comparingInt(node -> weightOfNodesMap.get(node))).get();
  }
}
