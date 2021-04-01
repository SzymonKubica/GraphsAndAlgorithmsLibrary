import java.util.ArrayList;
import java.util.List;

public class Node<T> {
  T element;
  List<Node<T>> adjacentNodes;
  LinkedGraph<T> graphThisBelongsTo;

  public Node(T element, LinkedGraph<T> graph) {
    this.element = element;
    adjacentNodes = new ArrayList<>();
    graphThisBelongsTo = graph;
    graphThisBelongsTo.nodes.add(this);

  }

  @Override
  public String toString() {
    return "(Node: " + element.toString() + ")";
  }
}
