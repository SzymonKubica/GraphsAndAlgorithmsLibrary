package linkedNodeGraph;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
  T element;
  List<Node<T>> adjacentNodes;

  public Node(T element) {
    this.element = element;
    adjacentNodes = new ArrayList<>();
  }

  public Node(T element, Graph<T> graphThisBelongsTo) {
    this(element);
    graphThisBelongsTo.addNode(this);
  }

  @Override
  public String toString() {
    return "(Node: " + element.toString() + ")";
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Node) {
      return element.equals(((Node<?>) other).element);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return element.hashCode();
  }
}

