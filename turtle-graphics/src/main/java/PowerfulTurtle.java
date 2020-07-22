import java.awt.*;

public class PowerfulTurtle extends Turtle {

  /**
   * Method to turn the turtle right.
   * @param degrees The number of degrees to turn right.
   */
  public void turnRight(double degrees) { turnLeft(-degrees); }

  /**
   * Method to draw a regular polygon with given
   * number of sides, each of given sideLength.
   * @param sides The number of sides of the polygon.
   * @param sideLength The length of each side of the regular polygon.
   */
  public void drawRegularPolygon(int sides, double sideLength) {
    for (int i = 0; i < sides; i++) {
      turnLeft(180 - (((sides - 2) * 180) / sides));
      move(sideLength);
    }
  }

  /**
   * Method to draw a regular pentagon with given sideLength.
   * @param sideLength The length of each side of the regular pentagon.
   */
  public void drawPentagon(double sideLength) {
    for (int i = 0; i < 5; i++) {
      turnLeft(72);
      move(sideLength);
    }
  }

  /**
   * Method to draw a rectangle with given length and width.
   * @param length    The length of the rectangle.
   * @param width     The width of the rectangle.
   */
  public void drawRectangle(double length, double width) {
    for (int i = 0; i < 2; i++) {
      turnLeft(90);
      move(width);
      turnLeft(90);
      move(length);
    }
  }

  /**
   * Method to draw in an animated fashion a
   * rectangle with given length and width.
   * @param length    The length of the rectangle.
   * @param width     The width of the rectangle.
   */
  public void drawMovingRectangle(double length, double width) {
    for (int i = 0; i < 2; i++) {
      turnLeft(90);
      movingMove(width);
      turnLeft(90);
      movingMove(length);
    }
  }

  /**
   * Method to draw an 'eye,' which is simply numerous
   * pentagons with given side length rotated about a point.
   * @param polygonSideLength The length of each side of each
   *                          pentagon within the 'eye'.
   */
  public void drawEye(double polygonSideLength) {
    for (int i = 0; i < 36; i++) {
      drawRegularPolygon((int)(Math.random() * 3 + 3), polygonSideLength);
      turnLeft(10);
    }
  }

  /**
   * Method to draw a recursive shape that looks in the end like a diamond.
   * @param depth     The number of layers of recursion.
   * @param length    The length of the final diamond.
   */
  public void drawRecursiveDiamond(int depth, double length) {
    if (depth == 0) {
      drawRegularPolygon(4, length);
    } else {
      for (int i = 0; i < 4; i++) {
        drawRecursiveDiamond(depth - 1, length / 2);
        move(length / 2);
        turnRight(90);
        move(length / 2);
      }
    }
  }

  /**
   * Method to draw a bunch of lines with
   * lengths according to the Fibonacci sequence.
   * @param n The number of lines.
   */
  public void drawFibNose(int n) {
    while (true) {
      for (int i = 1; i <= n; i++) {
        penDown();
        double distance = fib(i) * 5;
        move(distance);
        penUp();
        turnLeft(180);
        move(distance + 2);
        turnRight(90);
        move(30.0 / n);
        turnRight(90);
      }
      turnRight(90);
    }
  }

  /**
   * Method to find the nth number of in the Fibonacci sequence.
   * @param n     The term number wish to be found.
   * @return      The nth term of the sequence.
   */
  public int fib(int n) {
    // base case - 0, 1
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    } else {
      return fib(n - 1) + fib(n - 2);
    }
  }

  /**
   * Method to switch the turtle's color to a random color.
   */
  public void switchTo() {
    int r = (int)(Math.random() * 256);
    int g = (int)(Math.random() * 256);
    int b = (int)(Math.random() * 256);
    switchTo(new Color(r, g, b));
  }

  /**
   * Method to move in an animated fashion.
   * @param length The distance to move.
   */
  public void movingMove(double length) {
    for (int i = 0; i < length / 2; i++) {
      move(2);
    }
  }

  /**
   * Overrides superclass's move method, and makes
   * turtle switch to random color before moving.
   * @param forward The distance to move forward.
   * @return
   */
  @Override
  public Turtlet move(double forward) {
    switchTo();
    return super.move(forward);
  }
}
