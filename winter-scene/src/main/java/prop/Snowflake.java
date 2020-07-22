package prop;

import processing.core.PApplet;
import scene.Wind;

public class Snowflake {

  private float x, y;
  private float radius;
  private float vx, vy;
  private boolean freefalling;

  public Snowflake(float x, float y, float radius, float vx, float vy) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.vx = vx;
    this.vy = vy;
  }

  public void update(Wind wind, Wind gust) {
    vx = wind.getDx();
    if (gust.isActive()) {
      vx += gust.getCdx();
    }
    x += vx;
    y += vy;
  }

  public void fall(Wind g) {
    resetVelocity(g);
    freefalling = true;
  }

  public void resetPosition(PApplet p) {
    x = (float)Math.random() * p.width * 2 - p.width / 2;
    y = -10;
  }

  public boolean testSnowballCollision(Snowball b) {
    float circleX = b.getX();
    float circleY = b.getY();

    if (Math.pow(x - circleX, 2) + Math.pow(y - circleY, 2) <
        Math.pow(b.getRadius(), 2)) {
      return true;
    }
    if (testBranchCollision(b.getLeftB()) ||
        testBranchCollision(b.getRightB())) {
      return true;
    }
    if (b.getHat() != null) {
      return testSnowballCollision(b.getHat());
    }
    return false;
  }

  public boolean testBranchCollision(Branch b) {
    if (b != null) {
      double d1 =
          Math.sqrt(Math.pow(b.getX1() - x, 2) + Math.pow(b.getY1() - y, 2));
      double d2 =
          Math.sqrt(Math.pow(b.getX2() - x, 2) + Math.pow(b.getY2() - y, 2));

      if (!freefalling && d1 + d2 - b.length() < 0.5) {
        return true;
      }
      return b.getTwigs().stream().anyMatch(this::testBranchCollision);
    }
    return false;
  }

  public boolean testCollision(Snowflake other) {
    if (Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2) <
        Math.pow(other.getRadius(), 2)) {
      return true;
    }
    return false;
  }

  public void resetVelocity(Wind g) {
    vy = (float)(Math.random() + 4) + g.getDy();
  }

  public boolean isWithin(PApplet p, float delta) {
    return x >= 0 - delta && x <= p.width + delta && y >= 0 - delta &&
        y <= p.height + delta;
  }

  public boolean isWithin(float rX, float lX, float uY, float bY, float delta) {
    return x >= rX - delta && x <= lX + delta && y >= uY - delta &&
        y <= bY + delta;
  }

  public float getX() { return x; }

  public void setX(float x) { this.x = x; }

  public float getY() { return y; }

  public void setY(float y) { this.y = y; }

  public float getRadius() { return radius; }

  public float getVx() { return vx; }

  public void setVx(float vx) { this.vx = vx; }

  public void incrementVx(float increment) { vx += increment; }

  public float getVy() { return vy; }

  public void setVy(float vy) { this.vy = vy; }

  public void incrementVy(float increment) { vy += increment; }

  public boolean isFreefalling() { return freefalling; }

  public void setFreefalling(boolean freefalling) {
    this.freefalling = freefalling;
  }
}
