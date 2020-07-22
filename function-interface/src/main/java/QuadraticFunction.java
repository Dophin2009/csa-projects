public class QuadraticFunction implements Function {

  private int a, b, c;

  public QuadraticFunction(int a, int b, int c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public int getValue(int x) { return a * x * x + b * x + c; }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(a).append("x^2");

    if (b > 0) {
      s.append("+");
    }

    s.append(b).append("x");

    if (c > 0) {
      s.append("+");
    }

    s.append(c);
    return s.toString();
  }
}
