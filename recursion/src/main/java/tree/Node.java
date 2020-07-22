package tree;

public class Node {

  private double value;
  private String name;
  private Node leftChild, rightChild;

  public Node(double value, String name, Node leftChild, Node rightChild) {
    this.value = value;
    this.name = name;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

  public double getValue() { return value; }

  public void setValue(double value) { this.value = value; }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public Node getLeftChild() { return leftChild; }

  public void setLeftChild(Node leftChild) { this.leftChild = leftChild; }

  public Node getRightChild() { return rightChild; }

  public void setRightChild(Node rightChild) { this.rightChild = rightChild; }
}
