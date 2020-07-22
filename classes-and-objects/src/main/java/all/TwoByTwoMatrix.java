package all;

public class TwoByTwoMatrix {

  private double a, b, c, d;
  private double determinant;

  public TwoByTwoMatrix(double a, double b, double c, double d) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
    determinant = (a * d) - (b * c);
  }

  public String toString() {
    return "[ " + a + ", " + b + " ]"
        + "\n"
        + "[ " + c + ", " + d + " ]";
  }

  @Override
  public boolean equals(Object other) {
    TwoByTwoMatrix x = (TwoByTwoMatrix)other;
    if (a == x.a && b == x.b && c == x.c && d == x.d) {
      return true;
    }
    return false;
  }

  public TwoByTwoMatrix multiply(TwoByTwoMatrix other) {
    return new TwoByTwoMatrix(
        a * other.a + b * other.c, a * other.b + b * other.d,
        c * other.a + d * other.c, c * other.b + d * other.d);
  }

  public TwoByTwoMatrix addition(TwoByTwoMatrix other) {
    return new TwoByTwoMatrix(a + other.a, b + other.b, c + other.c,
                              d + other.d);
  }

  public TwoByTwoMatrix scalarMultiply(double factor) {
    return new TwoByTwoMatrix(factor * a, factor * b, factor * c, factor * d);
  }

  public TwoByTwoMatrix inverse() {
    if (determinant == 0) {
      return null;
    }
    return new TwoByTwoMatrix(d, -b, -c, a).scalarMultiply(1 / determinant);
  }

  public double getDeterminant() { return determinant; }

  public double getA() { return a; }

  public double getB() { return b; }

  public double getC() { return c; }

  public double getD() { return d; }
}
