import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Turtlet extends Object {
  public static final double DEGREE = Math.PI / 180;
  public static final Color RED = Color.red, BLUE = Color.blue,
                            BLACK = Color.black, GRAY = Color.gray,
                            YELLOW = Color.yellow, PINK = Color.pink,
                            ORANGE = Color.orange, GREEN = Color.green,
                            MAGENTA = Color.magenta, WHITE = Color.white;
  private static java.awt.Graphics thePage;
  //////////////////////////////////
  private double heading = 0; // heading initially east
  private double xcor, ycor;  // current position of Turtle
  private boolean penDown, showTurtle = false;
  private int delay = 0; // delay between draws

  /**
   * Set the drawing Color to the given value.  Made an instance method
   *  only so that it cannot be called until thePage is assigned, although
   *  the drawing color is a property of thePage, not of one Turtle.
   */

  public void setDelay(int newDelay) { delay = newDelay; }

  public void switchTo(Color given) {
    thePage.setColor(given);
  } //======================

  public void showTurtle(boolean showTurtle) {
    this.showTurtle = showTurtle;
    if (showTurtle) {
      drawTurtle();
    }
  }

  /**
   * Pick pen up
   *
   */
  public void penUp() { penDown = false; }

  /**
   * Put pen down
   */
  public void penDown() { penDown = true; }

  /** Write words without changing the Turtle's state.  */

  public void say(String message) {
    thePage.drawString(message, round(xcor), round(ycor));
    Turtlet.sleep(delay);
  } //======================

  /** Supply the nearest int value to methods requiring ints. */

  private int round(double x) {
    return (int)(x + 0.5);
  } //======================

  /** Make a circle of the given radius, Turtle at center. */

  public void swingAround(double radius) {
    if (radius > 0.0 && penDown) {
      int rad = round(2 * radius);
      thePage.drawOval(round(xcor - radius), round(ycor - radius), rad, rad);
    }
    Turtlet.sleep(delay);

  } //======================

  /** Fill a circle of the given radius, Turtle at center. */

  public void fillCircle(double radius) {
    if (radius > 0.0 && penDown) {
      int rad = round(2 * radius);
      thePage.fillOval(round(xcor - radius), round(ycor - radius), rad, rad);
    }
    Turtlet.sleep(delay);

  } //======================

  // the Turtle class, completed

  /** Rotate left by left degrees; MOVE for forward steps. */

  private Turtlet move(double left, double forward) {
    heading += left * DEGREE;
    double x = xcor + forward * Math.cos(heading);
    double y = ycor - forward * Math.sin(heading);
    if (penDown)
      thePage.drawLine(round(xcor), round(ycor), round(x), round(y));
    xcor = x;
    ycor = y;
    if (showTurtle)
      drawTurtle();
    Turtlet.sleep(delay);
    return this;
    //======================
  }

  /** MOVE for forward steps.*/
  public Turtlet move(double forward) { return this.move(0, forward); }

  /** Rotate left by left degrees; */
  public Turtlet turnLeft(double left) { return this.move(left, 0); }

  /** Fill a rectangle of the given width and height, Turtle at center. */

  public void fillBox(double width, double height) {
    if (width > 0.0 && height > 0.0 && penDown) {
      thePage.fillRect(round(xcor - width / 2), round(ycor - height / 2),
                       round(width), round(height));
    }
    Turtlet.sleep(delay);

  } //======================

  /**
   * Pause the animation for wait milliseconds.  Made a class method
   *  so that an applet can pause an animation "between turtles".
   */

  public static void sleep(int wait) {
    try {
      Thread.sleep(wait);
    } catch (InterruptedException ex) {
    }
  } //======================

  /**
   * Create a Turtlet on a given Component at a given starting position.
   *  All Turtlets must be created from within the Component's paint()
   *  method or from a method called by it.  All Turtles live in
   *  the same world at any given time, so changing the page is analogous
   *  to spaceshipping the entire Turtle population to a new world.
   */

  public Turtlet(TurtleWorld page, double xstart, double ystart) {
    if (page == null)
      throw new NullPointerException("You did not give a "
                                     + "world where turtles can live!");
    page.addKeyListener(new CustomListener());
    thePage = page.getPage();
    xcor = xstart;
    ycor = ystart;
    penDown = true;
    if (showTurtle)
      drawTurtle();
  } //======================

  private void drawTurtle() {
    Color current = thePage.getColor();
    thePage.setColor(GREEN);
    thePage.fillOval(round(xcor - 4), round(ycor - 4), 8, 8);
    double x = xcor + 4 * Math.cos(heading);
    double y = ycor - 4 * Math.sin(heading);
    thePage.fillOval(round(x - 2), round(y - 2), 4, 4);
    thePage.setColor(current);
  }

  class CustomListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
        Turtlet.this.move(20);
        break;
      case KeyEvent.VK_A:
        Turtlet.this.turnLeft(20);
        break;
      case KeyEvent.VK_D:
        Turtlet.this.turnLeft(-20);
        break;
      case KeyEvent.VK_SHIFT:
        Turtlet.this.penUp();
        break;
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      switch (e.getKeyCode()) {
      case KeyEvent.VK_SHIFT:
        Turtlet.this.penDown();
        break;
      }
    }
  }
}

// </pre>
