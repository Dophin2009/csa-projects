package grade;

/**
 * A class that represents a color in the RGB color model.
 */
public class Color {

  private int r, g, b, a;

  /**
   * Single parameter constructor that sets r, g, and b to the given integer.
   * @param n - the value to set r, g, and b to.
   */
  public Color(int n) {
    r = n;
    g = n;
    b = n;
    a = 255;
  }

  /**
   * Constructor to initialize values of r, g, and b.
   * @param r - the value of red light.
   * @param g - the value of green light.
   * @param b - the value of blue light.
   */
  public Color(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
    a = 255;
  }

  /**
   * Constructor to initialize the values of r, g, b, and a.
   * @param r - the value of red light.
   * @param g - the value of green light.
   * @param b - the value of blue light.
   * @param a - the value of the alpha channel.
   */
  public Color(int r, int g, int b, int a) {
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }

  /**
   * Method to return the value of red light.
   * @return the value of red light.
   */
  public int getR() { return r; }

  /**
   * Method to return the value of green light.
   * @return the value of green light.
   */
  public int getG() { return g; }

  /**
   * Method to return the value of blue light.
   * @return the value of blue light.
   */
  public int getB() { return b; }

  /**
   * Method to return the value of the alpha channel.
   * @return the value of the alpha channel.
   */
  public int getA() { return a; }
}
