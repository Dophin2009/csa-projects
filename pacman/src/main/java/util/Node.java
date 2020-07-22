package util;

public class Node {

  private int r, c;
  private boolean walkable;
  private Node parent;
  private int g;
  private int h;

  public Node(int r, int c, boolean walkable) {
    this.r = r;
    this.c = c;
    this.walkable = walkable;
  }

  public double[] coordinates() { return new double[] {r, c}; }

  public int getF() { return g + h; }

  public void setG(Node parent) { g = (parent.getG() + 1); }

  public int calculateG(Node parent) { return (parent.getG() + 1); }

  public void setH(Node goal) {
    h = (Math.abs(r - goal.getR()) + Math.abs(c - goal.getC()));
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (o.getClass() != Node.class) {
      return false;
    }
    if (o == this) {
      return true;
    }

    Node n = (Node)o;
    return n.r == r && n.c == c && n.walkable == walkable;
  }

  public int getR() { return r; }

  public void setR(int r) { this.r = r; }

  public int getC() { return c; }

  public void setC(int c) { this.c = c; }

  public boolean isWalkable() { return walkable; }

  public void setWalkable(boolean walkable) { this.walkable = walkable; }

  public Node getParent() { return parent; }

  public void setParent(Node parent) { this.parent = parent; }

  public int getG() { return g; }

  public int getH() { return h; }
}
