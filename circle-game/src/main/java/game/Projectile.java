package game;

public class Projectile {

  private Character home;
  private float centerX, centerY;
  private float x, y, size, r;
  private float gap;
  private float angle, dAngle;

  public Projectile(Character home, float size, float angle, float dAngle,
                    float gap) {
    this.home = home;
    centerX = home.getX();
    centerY = home.getY();

    this.size = size;
    this.angle = (float)Math.toRadians(angle);
    this.dAngle = (float)Math.toRadians(dAngle);
    this.gap = gap;

    update();
  }

  public void update() {
    centerX = home.getX();
    centerY = home.getY();
    r = (home.getSize() / 2) + gap + (size / 2);
    revolve();
  }

  public void revolve() {
    angle += dAngle;
    x = (float)(Math.cos(angle) * r) + centerX;
    y = (float)(-Math.sin(angle) * r) + centerY;
  }

  public boolean collidingWith(Circle other) {
    float distance = (float)Math.sqrt(Math.pow(x - other.getX(), 2) +
                                      Math.pow(y - other.getY(), 2));
    return distance < other.getSize() / 2 + 10;
  }

  public float getX() { return x; }

  public void setX(float x) { this.x = x; }

  public float getY() { return y; }

  public void setY(float y) { this.y = y; }

  public float getSize() { return size; }

  public void setSize(float size) { this.size = size; }

  public float getAngle() { return angle; }

  public void setAngle(float angle) { this.angle = angle; }
}
