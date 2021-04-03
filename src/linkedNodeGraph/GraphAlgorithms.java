package linkedNodeGraph;

import java.util.*;
import java.util.stream.Collectors;

public class GraphAlgorithms {
  public static <T> List<T> BFS(Node<T> start) {
    Set<Node<T>> visitedNodes = new HashSet<>();
    List<T> accumulator = new LinkedList<>();
    Queue<Node<T>> queue = new ArrayDeque<>();

    visitedNodes.add(start);
    accumulator.add(start.element);
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

  // Application of BFS.
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

  // Applications of DFS.
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

  public static <T> boolean isCyclic(UndirectedGraph<T> graph) {
    return hasCycleDFSTest(graph.first, new HashSet<>(), new HashMap<>());
  }

  private static <T> boolean hasCycleDFSTest(Node<T> start,
                                             Set<Node<T>> visitedNodes,
                                             HashMap<Node<T>, Node<T>> childParentMap
  ) {
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

  public static <T> Tree<T> MSTprim(WeightedGraph<T> graph, Node<T> start) {
    Tree<T> mst = new Tree<>();
    Set<Node<T>> fringe = new HashSet<>();
    Map<Node<T>, Integer> weightOfNodesMap = new HashMap<>();
    weightOfNodesMap.put(start, 0);

    for (Node<T> neighbour : start.adjacentNodes) {
      fringe.add(neighbour);
      weightOfNodesMap.put(neighbour, graph.getEdgeWeight(start, neighbour));
    }

    mst.addRoot(start);

    while (!fringe.isEmpty()) {
      Node<T> closestNode = getMinimumWeightFringeNode(fringe, weightOfNodesMap);
      WeightedEdge<T> minWeightEdge = getMinimumFringeEdgeEndingAt(closestNode, mst, graph);

      mst.addNode(closestNode);
      mst.addEdge(minWeightEdge);
      fringe.remove(closestNode);

      for (Node<T> neighbour : closestNode.adjacentNodes) {
        if (!mst.nodes.contains(neighbour)) {
          if (fringe.contains(neighbour)) {
            // Updating candidate arc.
            if (graph.getEdgeWeight(closestNode, neighbour) < weightOfNodesMap.get(neighbour)) {
              weightOfNodesMap.put(neighbour, graph.getEdgeWeight(closestNode, neighbour));
            }
          } else {
            // neighbour is unseen.
            fringe.add(neighbour);
            weightOfNodesMap.put(neighbour, graph.getEdgeWeight(closestNode, neighbour));
          }
        }
      }
    }
    return mst;
  }

  private static <T> Set<WeightedEdge<T>> getEdgesStartingInTheTreeEndingAt(Node<T> destination,
                                                                            Tree<T> mst,
                                                                            WeightedGraph<T> graph) {
    return graph.getEdges().stream().filter(
            edge -> edge.getDestination().equals(destination) && mst.nodes.contains(edge.getOrigin()))
            .collect(Collectors.toSet());
  }

  private static <T> WeightedEdge<T> getMinimumFringeEdgeEndingAt(Node<T> destination,
                                                                  Tree<T> mst,
                                                                  WeightedGraph<T> graph) {
    return getEdgesStartingInTheTreeEndingAt(destination, mst, graph).stream()
            .min(Comparator.comparingInt(WeightedEdge::getWeight)).get();
  }

  private static <T> Node<T> getMinimumWeightFringeNode(Set<Node<T>> fringe,
                                                        Map<Node<T>, Integer> weightOfNodesMap) {
    return fringe.stream().min(Comparator.comparingInt(node -> weightOfNodesMap.get(node))).get();
  }

  public static <T> Tree<T> MSTkruskal(WeightedGraph<T> graph, Node<T> start) {
    Queue<WeightedEdge<T>> edgeQueue = new PriorityQueue<>(Comparator.comparingInt(WeightedEdge::getWeight));
    edgeQueue.addAll(graph.getEdges());
    Set<UnionFind<T>> sets = createSets(graph.nodes);
    Tree<T> forest = new Tree<>();

    while (!edgeQueue.isEmpty()) {
      WeightedEdge<T> edge = edgeQueue.remove();
      Node<T> originLeader = UnionFind.findLeader(sets, edge.getOrigin());
      Node<T> destinationLeader = UnionFind.findLeader(sets, edge.getDestination());
      if (originLeader != destinationLeader) {
        if (forest.nodes.isEmpty()) {
          forest.addRoot(edge.getOrigin(
          ));
        }
        if (!forest.nodes.contains(edge.getOrigin())) {
          forest.addNode(edge.getOrigin());
        }
        if (!forest.nodes.contains(edge.getDestination())) {
          forest.addNode(edge.getDestination());
        }
        forest.addEdge(edge.getDestination(), edge.getOrigin());
        UnionFind.union(sets, originLeader, destinationLeader);
      }
    }

    forest.root = forest.nodes.stream().collect(Collectors.toList()).get(0);
    return forest;
  }

  private static <T> Set<UnionFind<T>> createSets(Set<Node<T>> nodes) {
    Set<UnionFind<T>> sets = new HashSet<>();
    for (Node<T> node : nodes) {
      sets.add(new UnionFind<>(node));
    }
    return sets;
  }

  private static class UnionFind<T> {
    Node<T> leader;
    Set<Node<T>> set;

    public UnionFind(Node<T> leader) {
      this.leader = leader;
      set = new HashSet<>();
      set.add(leader);
    }

    public static <T> Node<T> findLeader(Set<UnionFind<T>> sets, Node<T> node) {
      return sets.stream().filter(set -> set.set.contains(node)).collect(Collectors.toList()).get(0).leader;
    }

    public static <T> void union(Set<UnionFind<T>> sets, Node<T> leader1, Node<T> leader2) {
      UnionFind<T> set1 = sets.stream().filter(set -> set.leader.equals(leader1)).collect(Collectors.toList()).get(0);
      UnionFind<T> set2 = sets.stream().filter(set -> set.leader.equals(leader2)).collect(Collectors.toList()).get(0);
      sets.remove(set1);
      sets.remove(set2);
      set1.set.addAll(set2.set);
      set2.leader = set1.leader;
      sets.add(set1);
    }
  }
}
