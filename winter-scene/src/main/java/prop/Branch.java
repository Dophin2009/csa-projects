package prop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Branch {

  private Random rng = new Random();

  private float x1, y1;
  private float x2, y2;
  private int dir;
  private int depth;
  private List<Branch> twigs;

  public Branch(float x1, float y1, float x2, float y2, int dir, int depth) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.dir = dir;
    this.depth = depth;

    twigs = new ArrayList<>();
  }

  public void generateBranch() {
    twigs.clear();
    if (depth == 1) {
      return;
    }
    for (int i = 0; i < Math.random() * 2 + 1; i++) {
      float l = length() * (rng.nextFloat() * 0.2f + 0.7f);
      float angle = i * (rng.nextFloat() * 15 + 15) + 60;
      angle = (float)Math.toRadians(angle);
      float x = (float)(x2 + dir * l * Math.sin(angle));
      float y = (float)(y2 - l * Math.cos(angle));
      Branch t = new Branch(x2, y2, x, y, dir, depth - 1);
      twigs.add(t);
      t.generateBranch();
    }
  }

  public void move(float dx) {
    x1 += dx;
    x2 += dx;
    twigs.forEach(t -> t.move(dx));
  }

  public float length() {
    return (float)Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  public float getX1() { return x1; }

  public float getY1() { return y1; }

  public float getX2() { return x2; }

  public float getY2() { return y2; }

  public int getDepth() { return depth; }

  public List<Branch> getTwigs() { return twigs; }
}
