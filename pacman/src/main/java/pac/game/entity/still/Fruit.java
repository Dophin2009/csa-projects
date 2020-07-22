package pac.game.entity.still;

public class Fruit extends Consumable {

  private int countdown;
  private int totalCountdown;
  private boolean visible;

  public Fruit(double points, int countdown) {
    super(points);
    this.countdown = countdown;
    this.totalCountdown = countdown;
    this.visible = false;
  }

  public void update() { countdown--; }

  public void restartCountdown() { this.countdown = totalCountdown; }

  public int getCountdown() { return countdown; }

  public void setCountdown(int countdown) { this.countdown = countdown; }

  public boolean isVisible() { return visible; }

  public void setVisible(boolean visible) { this.visible = visible; }
}
