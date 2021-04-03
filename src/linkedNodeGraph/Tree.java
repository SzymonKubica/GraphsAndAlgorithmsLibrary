package linkedNodeGraph;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
  }

  @Override
  public void addNode(Node<T> node) {
    Node<T> newNode = new Node<>(node.element);
    super.addNode(newNode);
  }

  public void addEdge(WeightedEdge<T> edge) {
    Node<T> thisOrigin = nodes.stream().filter(node -> node.element.equals(edge.getOrigin().element))
            .collect(Collectors.toList()).get(0);
    Node<T> thisDestination = nodes.stream().filter(node -> node.element.equals(edge.getDestination().element))
            .collect(Collectors.toList()).get(0);
    this.addEdge(thisOrigin, thisDestination);
  }
}
