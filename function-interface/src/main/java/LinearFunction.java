public class LinearFunction implements Function {

  private int a, b;

  public LinearFunction(int a, int b) {
    this.a = a;
    this.b = b;
  }

  public int getValue(int x) { return a * x + b; }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(a).append("x");

    if (b > 0) {
      s.append("+");
    }

    s.append("b");
    return s.toString();
  }
}
