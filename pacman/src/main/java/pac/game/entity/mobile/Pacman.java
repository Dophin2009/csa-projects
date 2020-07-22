package pac.game.entity.mobile;

public class Pacman extends MovingEntity {

  private static final double MOVE_INTERVAL = 0.25;

  private boolean active;

  public Pacman(double row, double col, boolean active) {
    super(row, col, MOVE_INTERVAL, Direction.LEFT);
    this.active = active;
  }

  public void update() {
    if (active) {
      super.update();
    }
  }

  @Override
  public boolean canChangeDirection(Direction newDir) {
    if (Direction.isOpposites(getDirection(), newDir)) {
      return true;
    } else {
      return atIntersection();
    }
  }

  public boolean isActive() { return active; }

  public void setActive(boolean active) { this.active = active; }
}
