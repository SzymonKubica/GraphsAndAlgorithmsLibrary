package linkedNodeGraph;

import java.util.*;
import java.util.stream.Collectors;

public class UndirectedGraph<T> implements Graph<T> {

  protected Set<Node<T>> nodes;
  protected Node<T> first;

  public UndirectedGraph() {
    nodes = new HashSet<>();
  }

  @Override
  public void addEdge(Node<T> origin, Node<T> destination) {
    origin.adjacentNodes.add(destination);
    destination.adjacentNodes.add(origin);
  }

  @Override
  public void addNode(Node<T> node) {
    Set<T> elementSet = nodes.stream().map(n -> n.element).collect(Collectors.toSet());
    assert !elementSet.contains(node.element) : "All nodes need to store distinct elements";
    nodes.add(node);
  }

  @Override
  public Set<Node<T>> getNodes() {
    return nodes;
  }

  @Override
  public int getNumberOfNodes() {
    return nodes.size();
  }
}
