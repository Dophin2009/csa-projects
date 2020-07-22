package pac.game.entity.still;

import pac.game.entity.Entity;

public class Space extends Entity {

  private boolean walkable;
  private Consumable consumable;

  public Space(int row, int col, boolean walkable, Consumable consumable) {
    super(row, col);
    this.walkable = walkable;
    this.consumable = consumable;
  }

  @Override
  public void update() {
    // do nothing
  }

  public double consume() {
    double pts = consumable.consume();
    consumable = null;
    return pts;
  }

  public boolean isWalkable() { return walkable; }

  public void setWalkable(boolean walkable) { this.walkable = walkable; }

  public Consumable getConsumable() { return consumable; }

  public void setConsumable(Consumable consumable) {
    this.consumable = consumable;
  }

  public static Space createWall(int row, int col) {
    return new Space(row, col, false, null);
  }

  public static Space createBlank(int row, int col) {
    return new Space(row, col, true, null);
  }

  public static Space createBlank(int row, int col, double value) {
    return new Space(row, col, true, new Consumable(value));
  }

  public static Space createPowerupSpace(int row, int col, Powerup powerup) {
    return new Space(row, col, true, powerup);
  }
}
