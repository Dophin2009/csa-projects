package game;

public class Character {

  public static final int FRAMERATE = Driver.FRAMERATE;

  private float x, y, size;
  private int xDirection, yDirection;
  private Color color;

  public static final float ULT_SIZE_REDUCTION = 0.6f;
  private Ability ultimate, evade, burst;

  public Character(float x, float y, float size, Color color) {
    this.x = x;
    this.y = y;
    this.size = size;
    this.color = color;

    ultimate = new Ability(this, 40 * FRAMERATE, 30 * FRAMERATE);
    evade = new Ability(this, 30 * FRAMERATE, 20 * FRAMERATE, 2 * FRAMERATE,
                        2 * FRAMERATE);
    burst = new Ability(this, 20 * FRAMERATE, 10 * FRAMERATE, 4 * FRAMERATE,
                        4 * FRAMERATE);

    burst.addProjectile(new Projectile(this, 30, 0, 3, 80));
    burst.addProjectile(new Projectile(this, 30, 180, 6, 20));
  }

  public void update(float mouseX, float mouseY) {
    moveCharacter(mouseX, mouseY);
    ultimate.update();
    evade.update();
    burst.update();
  }

  public void grow() { size += 2; }

  public void moveCharacter(float mouseX, float mouseY) {
    x = mouseX;
    y = mouseY;
  }

  public boolean collidingWith(Circle other) {
    float distance = (float)Math.sqrt(Math.pow(x - other.getX(), 2) +
                                      Math.pow(y - other.getY(), 2));
    if (size > other.getSize()) {
      return distance < size / 2 - 10;
    } else {
      return distance < other.getSize() / 2 - 10;
    }
  }

  public void useEvade() {
    color.setAlpha(150);
    evade.setActive(true);
    evade.resetCooldown();
  }

  public void useBurst() {
    burst.setActive(true);
    burst.resetCooldown();
  }

  public void useUltimate() { ultimate.resetCooldown(); }

  public float getX() { return x; }

  public void setX(float x) { this.x = x; }

  public float getY() { return y; }

  public void setY(float y) { this.y = y; }

  public int getxDirection() { return xDirection; }

  public void setxDirection(int xDirection) { this.xDirection = xDirection; }

  public int getyDirection() { return yDirection; }

  public void setyDirection(int yDirection) { this.yDirection = yDirection; }

  public float getSize() { return size; }

  public Color getColor() { return color; }

  public Ability getUltimate() { return ultimate; }

  public Ability getEvade() { return evade; }

  public Ability getBurst() { return burst; }
}
