package pac.game.entity.mobile;

import pac.game.entity.Entity;
import util.Util;

public abstract class MovingEntity extends Entity {

  private Direction direction;
  private double moveInterval;

  private double spawnRow, spawnCol;

  public MovingEntity(double row, double col, double moveInterval,
                      Direction direction) {
    super(row, col);
    spawnRow = (int)getRow();
    spawnCol = (int)getCol();

    this.moveInterval = moveInterval;
    this.direction = direction;
  }

  public abstract boolean canChangeDirection(Direction newDir);

  public void update() {
    if (direction == Direction.UP) {
      incrementRow(-moveInterval);
    } else if (direction == Direction.DOWN) {
      incrementRow(moveInterval);
    } else if (direction == Direction.RIGHT) {
      incrementCol(moveInterval);
    } else {
      incrementCol(-moveInterval);
    }
  }

  public void respawn() { setLocation(spawnRow, spawnCol); }

  public double[] nextRowCol() {
    double row = getRow();
    double col = getCol();

    if (direction == Direction.LEFT) {
      return new double[] {row, Math.floor(Util.round(col - moveInterval, 2))};
    } else if (direction == Direction.RIGHT) {
      return new double[] {row, Math.ceil(Util.round(col + moveInterval, 2))};
    } else if (direction == Direction.UP) {
      return new double[] {Math.floor(Util.round(row - moveInterval, 2)), col};
    } else {
      return new double[] {Math.ceil(Util.round(row + moveInterval, 2)), col};
    }
  }

  public double[] prevRowCol() {
    Direction curDir = direction;
    direction = Direction.oppositeOf(direction);
    double[] backRC = nextRowCol();
    direction = curDir;
    return backRC;
  }

  public boolean atIntersection() {
    return getRow() == Math.round(getRow()) && getCol() == Math.round(getCol());
  }

  public Direction getDirection() { return direction; }

  public void setDirection(Direction direction) { this.direction = direction; }

  public double getMoveInterval() { return moveInterval; }

  public void setMoveInterval(double moveInterval) {
    this.moveInterval = moveInterval;
  }

  public double getSpawnRow() { return spawnRow; }

  public void setSpawnRow(double spawnRow) { this.spawnRow = spawnRow; }

  public double getSpawnCol() { return spawnCol; }

  public void setSpawnCol(double spawnCol) { this.spawnCol = spawnCol; }
}
