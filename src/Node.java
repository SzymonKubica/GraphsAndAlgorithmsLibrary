import java.util.ArrayList;
import java.util.List;

public class Node<T> {
  T element;
  List<Node<T>> adjacentNodes;

  public Node(T element) {
    this.element = element;
    adjacentNodes = new ArrayList<>();
  }

  @Override
  public String toString() {
    return "(Node: " + element.toString() + ")";
  }
}
