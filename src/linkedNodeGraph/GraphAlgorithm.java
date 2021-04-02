package linkedNodeGraph;

import java.util.*;

public class GraphAlgorithm {

  public static <T> List<T> BFS(Node<T> start) {
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

  public static <T> List<T> DFS(Node<T> start) {
    List<T> accumulator = new LinkedList<>();
    Set<Node<T>> visitedNodes = new HashSet<>();
    return DFS2(start, accumulator, visitedNodes);
  }

  private static <T> List<T> DFS2(Node<T> start, List<T> accumulator, Set<Node<T>> visitedNodes) {
    visitedNodes.add(start);
    accumulator.add(start.element);
    for (Node<T> child : start.adjacentNodes) {
      if (!visitedNodes.contains(child)) {
        DFS2(child, accumulator, visitedNodes);
      }
    }
    return accumulator;
  }

  public static <T> List<T> topologicalSort(DirectedGraph<T> graph, Node<T> start) {
    List<T> result = new ArrayList<>();
    try {
      Set<Node<T>> enteredNodes = new HashSet<>();
      Set<Node<T>> exitedNodes = new HashSet<>();
      DFSTopologicalSort(start, enteredNodes, exitedNodes, result);
      for (Node<T> node : graph.getNodes()) {
        if (!enteredNodes.contains(node)) {
          DFSTopologicalSort(node, enteredNodes, exitedNodes, result);
        }
      }
    } catch (CyclicTreeException e) {
      System.out.println("The graph must be acyclic!");
    }
    return result;
  }

  private static <T> void DFSTopologicalSort(
          Node<T> start,
          Set<Node<T>> enteredNodes,
          Set<Node<T>> exitedNodes,
          List<T> accumulator
  ) throws CyclicTreeException {
    enteredNodes.add(start);
    for (Node<T> neighbour : start.adjacentNodes) {
      if (enteredNodes.contains(neighbour)) {
        if (!exitedNodes.contains(neighbour)) {
          throw new CyclicTreeException();
        }
      } else {
        DFSTopologicalSort(neighbour, enteredNodes, exitedNodes, accumulator);
      }
    }
    exitedNodes.add(start);
    accumulator.add(0, start.element);
  }

  public static  <T> boolean isCyclic(UndirectedGraph<T> graph) {
    return hasCycleDFSTest(graph.first, new HashSet<>(), new HashMap<>());
  }

  private static <T> boolean hasCycleDFSTest(Node<T> start, Set<Node<T>> visitedNodes, HashMap<Node<T>, Node<T>> childParentMap) {
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
  public static <T> Map<Node<T>, Integer> shortestPathsFrom(Node<T> start) {
    Set<Node<T>> visitedNodes = new HashSet<>();
    Queue<Node<T>> queue = new ArrayDeque<>();
    Map<Node<T>, Node<T>> childParentMap = new HashMap<>();
    Map<Node<T>, Integer> distanceMap = new HashMap<>();
    queue.add(start);
    distanceMap.put(start, 0);
    while (!queue.isEmpty()) {
      Node<T> current = queue.peek();
      for (Node<T> neighbour : current.adjacentNodes) {
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