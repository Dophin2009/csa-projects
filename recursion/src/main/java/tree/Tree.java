package tree;

public class Tree {

  private Node root;

  public static void main(String[] args) {
    String[] names = {"Jazib",  "Will",   "Ian",       "Tommy M.",  "Michael",
                      "Minju",  "Karen",  "Elizabeth", "Gautam",    "Matt",
                      "Pranav", "Gloria", "Eric",      "Maya-chan", "Daniel",
                      "Ryan O", "Ryan Z", "John",      "David",     "Tommy P.",
                      "Hayden"};

    double[] values = {10, 8,  99, 24, 42, 7,  4, 15, 88, 27, 12,
                       48, 72, 9,  2,  6,  36, 1, 34, 52, 3};

    Node[] nodes = new Node[names.length];
    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = new Node(values[i], names[i], null, null);
    }
    for (int i = 0; i < nodes.length; i++) {
      if (2 * i + 1 < nodes.length) {
        nodes[i].setLeftChild(nodes[2 * i + 1]);
      }
      if (2 * i + 2 < nodes.length) {
        nodes[i].setRightChild(nodes[2 * i + 2]);
      }
    }

    Tree t = new Tree(nodes[0]);

    System.out.println(t.sum());
  }

  public Tree(Node root) { this.root = root; }

  public double sum() { return sum(root); }

  private double sum(Node n) {
    if (n == null) {
      return 0;
    }
    return n.getValue() + sum(n.getLeftChild()) + sum(n.getRightChild());
  }

  public Node getRoot() { return root; }
}
