package prop;

import java.util.Random;
import java.util.function.Consumer;

/**
 * Linked list snowman.
 */
public class Snowball {

  private Random rng;

  private float x, y;
  private float radius;
  private int depth;
  private Branch leftB, rightB;
  private Snowball hat;

  public Snowball(float x, float y, float radius, int depth) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.depth = depth;

    rng = new Random();

    if (depth != 1) {
      leftB = new Branch((float)(x - radius * 0.5), y, x - radius * 1.5f,
                         (float)(y - (rng.nextFloat() * radius * 0.3)), -1, 3);
      rightB = new Branch((float)(x + radius * 0.5), y, x + radius * 1.5f,
                          (float)(y - (rng.nextFloat() * radius * 0.3)), 1, 3);
    }
  }

  public void init() {
    if (depth == 1) {
      return;
    }

    leftB.generateBranch();
    rightB.generateBranch();

    wearHat();
    hat.init();
  }

  public void wearHat() {
    hat = new Snowball(x, y - (radius * 1.3f), radius * 0.7f, depth - 1);
  }

  public Snowball topHat() {
    Snowball current = this;
    while (current.getHat() != null) {
      current = current.getHat();
    }
    return current;
  }

  public void move(float dx) {
    x += dx;
    if (leftB != null) {
      leftB.move(dx);
    }
    if (rightB != null) {
      rightB.move(dx);
    }
  }

  public void forSnowman(Consumer<Snowball> func) {
    Snowball current = this;
    while (current != null) {
      func.accept(current);
      current = current.getHat();
    }
  }

  public float getX() { return x; }

  public float getY() { return y; }

  public float getRadius() { return radius; }

  public int getDepth() { return depth; }

  public Branch getLeftB() { return leftB; }

  public Branch getRightB() { return rightB; }

  public Snowball getHat() { return hat; }
}
