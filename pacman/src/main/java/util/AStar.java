package util;

import java.util.LinkedList;
import java.util.List;

/**
 * a-star algorithm code take from
 * https://github.com/tiagodopke/java-a-star/blob/master/src/game/astar
 * because I'm too lazy and dumb to implement it myself
 */
public class AStar {

  private AStar() {
    // disallow instantiation; utility class
  }

  public static List<Node> search(Node[][] nodes, int sR, int sC, int tR,
                                  int tC) {
    // nodes already visited
    List<Node> openList = new LinkedList<>();
    // nodes yet to be visited
    List<Node> closedList = new LinkedList<>();

    // add starting node to visited list
    openList.add(nodes[sR][sC]);

    while (true) {
      // get node with lowest F score from open list
      Node current = lowestFInList(openList);
      openList.remove(current);
      closedList.add(current);

      // if current position is the same as target position
      if ((current.getR() == tR) && (current.getC() == tC)) {
        return calcPath(nodes[sR][sC], current);
      }

      List<Node> adjacentNodes = getAdjacentNodes(current, closedList, nodes);
      for (Node adjacent : adjacentNodes) {
        if (!openList.contains(adjacent)) {
          adjacent.setParent(current);
          adjacent.setH(nodes[tR][tC]);
          adjacent.setG(current);
          openList.add(adjacent);
        } else if (adjacent.getG() > adjacent.calculateG(current)) {
          adjacent.setParent(current);
          adjacent.setG(current);
        }
      }

      if (openList.isEmpty()) {
        return new LinkedList<>();
      }
    }
  }

  private static List<Node> calcPath(Node start, Node target) {
    LinkedList<Node> path = new LinkedList<>();
    Node node = target;
    boolean done = false;
    while (!done) {
      path.addFirst(node);
      node = node.getParent();
      if (node.equals(start)) {
        done = true;
      }
    }
    return path;
  }

  private static Node lowestFInList(List<Node> list) {
    Node cheapest = list.get(0);
    for (Node n : list) {
      if (n.getF() < cheapest.getF()) {
        cheapest = n;
      }
    }
    return cheapest;
  }

  private static List<Node> getAdjacentNodes(Node node, List<Node> closedList,
                                             Node[][] grid) {
    List<Node> adjacentNodes = new LinkedList<>();
    int r = node.getR();
    int c = node.getC();

    Node adjacent;

    if (r > 0) {
      adjacent = getNode(grid, r - 1, c);
      if (adjacent != null && adjacent.isWalkable() &&
          !closedList.contains(adjacent)) {
        adjacentNodes.add(adjacent);
      }
    }

    // Check right node
    if (r < grid.length) {
      adjacent = getNode(grid, r + 1, c);
      if (adjacent != null && adjacent.isWalkable() &&
          !closedList.contains(adjacent)) {
        adjacentNodes.add(adjacent);
      }
    }

    // Check top node
    if (c > 0) {
      adjacent = getNode(grid, r, c - 1);
      if (adjacent != null && adjacent.isWalkable() &&
          !closedList.contains(adjacent)) {
        adjacentNodes.add(adjacent);
      }
    }

    // Check bottom node
    if (c < grid[0].length) {
      adjacent = getNode(grid, r, c + 1);
      if (adjacent != null && adjacent.isWalkable() &&
          !closedList.contains(adjacent)) {
        adjacentNodes.add(adjacent);
      }
    }
    return adjacentNodes;
  }

  private static Node getNode(Node[][] grid, int r, int c) {
    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) {
      return grid[r][c];
    } else {
      return null;
    }
  }

  public static void printGrid(Node[][] grid, List<Node> path) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (!grid[i][j].isWalkable()) {
          System.out.print(" #");
        } else if (path.contains(new Node(i, j, true))) {
          System.out.print(" @");
        } else {
          System.out.print("  ");
        }
      }
      System.out.print("\n");
    }
  }
}
