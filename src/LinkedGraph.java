import java.util.*;

public class LinkedGraph<T> implements Graph<T> {
  public Node<T> first;

  public List<T> DFS(Node<T> start) {
    List<T> accumulator = new LinkedList<>();
    Set<Node<T>> visitedNodes = new HashSet<>();
    return DFS2(start, accumulator, visitedNodes);
  }

  private List<T> DFS2(Node<T> start, List<T> accumulator, Set<Node<T>> visitedNodes) {
    visitedNodes.add(start);
    accumulator.add(start.element);
    for (Node<T> child : start.adjacentNodes) {
      if (!visitedNodes.contains(child)) {
        DFS2(child, accumulator, visitedNodes);
      }
    }
    return accumulator;
  }

  public List<T> BFS(Node<T> start) {
    Set<Node<T>> visitedNodes = new HashSet<>();
    List<T> accumulator = new LinkedList<>();
    visitedNodes.add(start);
    accumulator.add(start.element);
    Queue<Node<T>> queue = new ArrayDeque<>();
    queue.add(start);
    while (!queue.isEmpty()) {
      Node<T> current = queue.peek();
      for (Node<T> neighbour : current.adjacentNodes) {
        if (!visitedNodes.contains(neighbour)) {
          visitedNodes.add(neighbour);
          accumulator.add(neighbour.element);
          queue.add(neighbour);
        }
      }
      queue.remove();
    }
    return accumulator;
  }

  public boolean hasCycle(Node<T> start) {
    return hasCycleDFSTest(start, new HashSet<>(), new HashMap<>());
  }

  private boolean hasCycleDFSTest(Node<T> start, Set<Node<T>> visitedNodes, HashMap<Node<T>, Node<T>> childParentMap) {
    visitedNodes.add(start);
    for (Node<T> neighbour : start.adjacentNodes) {
      if (visitedNodes.contains(neighbour) && !neighbour.equals(childParentMap.get(start))) {
        return true;
      }
      if (!visitedNodes.contains(neighbour)) {
        visitedNodes.add(neighbour);
        childParentMap.put(neighbour, start);
        boolean result = hasCycleDFSTest(neighbour, visitedNodes, childParentMap);
        if (result) {
          return true;
        }
      }
    }
    return false;
  }

  public Map<Node<T>, Integer> shortestPathsFrom(Node<T> start) {
    Set<Node<T>> visitedNodes = new HashSet<>();
    Queue<Node<T>> queue = new ArrayDeque<>();
    Map<Node<T>, Node<T>> childParentMap = new HashMap<>();
    Map<Node<T>, Integer> distanceMap = new HashMap<>();
    queue.add(start);
    distanceMap.put(start, 0);
    while (!queue.isEmpty()) {
      Node<T> current = queue.peek();
      for(Node<T> neighbour : current.adjacentNodes) {
        if (!visitedNodes.contains(neighbour)) {
          visitedNodes.add(neighbour);
          childParentMap.put(neighbour, current);
          distanceMap.put(neighbour, distanceMap.get(current) + 1);
          queue.add(neighbour);
        }
      }
      queue.remove();
    }
    return distanceMap;
  }
}