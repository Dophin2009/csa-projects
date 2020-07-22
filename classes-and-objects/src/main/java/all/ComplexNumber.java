package all;

/**
 * A class that represents a complex number.
 * @author Eric C. Zhao.
 */
public class ComplexNumber {

  private double a, b; // complex number is a + bi

  /**
   * Default constructor.
   */
  public ComplexNumber() {
    a = 0.0;
    b = 0.0;
  }

  /**
   * Constructor.
   * @param a - a part of the complex number.
   * @param b - imaginary part of the complex number.
   */
  public ComplexNumber(double a, double b) {
    this.a = a;
    this.b = b;
  }

  /**
   * Method to compare if two complex numbers objects are equal.
   * @param obj - the other complex number.
   * @return  true if a and imaginary parts of the given object are equal to
   *          the a and imaginary parts of this object, respectively, false
   * otherwise.
   */
  public boolean equals(Object obj) {
    final double THRESHOLD = 0.0001;

    if (!(obj instanceof ComplexNumber)) {
      return false;
    }
    ComplexNumber other = (ComplexNumber)obj;
    return Math.abs(a - other.getReal()) < THRESHOLD &&
        Math.abs(b - other.getImaginary()) < THRESHOLD;
  }

  /**
   * Returns the string format of this complex number object.
   * @return string format of this complex number object.
   */
  public String toString() {
    String r = String.format("%.2f", a);
    String i = String.format("%.2f", b);
    return b >= 0 ? r + "+" + i + "i" : r + "" + i + "i";
  }

  /**
   * Method to add two complex number objects.
   * @param complex - the other complex number.
   * @return a <code>ComplexNumber</code> object representing the sum of the two
   *     complex numbers.
   */
  public ComplexNumber plus(ComplexNumber complex) {
    return new ComplexNumber(a + complex.getReal(), b + complex.getImaginary());
  }

  /**
   * Method to multiply the complex number by a scalar factor.
   * @param n - the factor by which to multiply the complex number.
   * @return a <code>ComplexNumber</code> object representing the product of
   *     this object and the given factor.
   */
  public ComplexNumber times(double n) {
    return new ComplexNumber(a * n, b * n);
  }

  /**
   * Method to multiply two complex numbers together.
   * @param complex - the other complex number.
   * @return a <code>ComplexNumber</code> object representing the product of the
   *     two complex numbers.
   */
  public ComplexNumber times(ComplexNumber complex) {
    return new ComplexNumber(a * complex.getReal() - b * complex.getImaginary(),
                             a * complex.getImaginary() +
                                 b * complex.getReal());
  }

  /**
   * Method to divide this complex number by the given divisor.
   * @param n - the given divisor.
   * @return  a <code>ComplexNumber</code> object representing the result of
   *     this complex number
   *          divided by the given number.
   */
  public ComplexNumber dividedBy(double n) {
    return new ComplexNumber(a / n, b / n);
  }

  /**
   * Method to divide this complex number by the given complex number.
   * @param complex - the given complex number.
   * @return  a <code>ComplexNumber</code> object representing the quotient of
   *     this complex number
   *          and the given complex number.
   * @exception throws an <code>IllegalArgumentException</code> object when the
   * divisor is equal to 0.
   */
  public ComplexNumber dividedBy(ComplexNumber complex) {
    if (complex.getReal() == 0 && complex.getImaginary() == 0) {
      throw new IllegalArgumentException("Cannot divide by zero.");
    }
    return this.times(complex.getConjugate())
        .dividedBy(complex.times(complex.getConjugate()).getReal());
  }

  /**
   * Method to raise this complex number to the given exponent.
   * @param x - the given exponent.
   * @return a <code>ComplexNumber</code> object representing this complex
   *     number to the given power.
   */
  public ComplexNumber pow(double x) {
    if (x < 0) {
      throw new IllegalArgumentException();
    }
    double r = getModulus();
    double theta = Math.atan(b / a);
    return new ComplexNumber(Math.pow(r, x) * Math.cos(x * theta),
                             Math.pow(r, x) * Math.sin(x * theta));
  }

  /**
   * Method to raise this complex number to the given complex exponent.
   * @param x - the given exponent.
   * @return a <code>ComplexNumber</code> object representing this complex
   *     number to the given complex power.
   */
  public ComplexNumber pow(ComplexNumber x) {
    double m = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), x.getReal() / 2) *
               Math.pow(Math.E, -x.getImaginary() * Math.atan(b / a));
    double n =
        x.getReal() * Math.atan(b / a) +
        (x.getImaginary() / 2) * (Math.log(Math.pow(a, 2) + Math.pow(b, 2)));

    return new ComplexNumber(m * Math.cos(n), m * Math.sin(n));
  }

  /**
   * Method to return the conjugate of this complex number.
   * @return a <code>ComplexNumber</code> representing the conjugate of this
   *     complex number.
   */
  public ComplexNumber getConjugate() { return new ComplexNumber(a, -b); }

  /**
   * Method to return the modulus of this complex number.
   * @return a number representing the modulus of this complex number.
   */
  public double getModulus() {
    return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
  }

  /**
   * Method to return the real part of this complex number.
   * @return a number representing the real part of this complex number.
   */
  public double getReal() { return a; }

  /**
   * Method to return the imaginary part of this complex number.
   * @return a number representing the imaginary part of this complex number.
   */
  public double getImaginary() { return b; }
}
