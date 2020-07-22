package pac.game.entity.mobile;

public enum Direction {

  NONE(0, 0),
  LEFT(-1, 0),
  RIGHT(1, 0),
  UP(0, -1),
  DOWN(0, 1);

  private int[] val;

  Direction(int hor, int vert) { val = new int[] {hor, vert}; }

  public static Direction fromString(String s) {
    switch (s) {
    case "LEFT":
      return LEFT;
    case "RIGHT":
      return RIGHT;
    case "UP":
      return UP;
    case "DOWN":
      return DOWN;
    default:
      return NONE;
    }
  }

  public static Direction oppositeOf(Direction d) {
    return fromSignArray(-d.val[0], -d.val[1]);
  }

  public static Direction fromSignArray(int hor, int vert) {
    if (hor == -1 && vert == 0) {
      return LEFT;
    } else if (hor == 1 && vert == 0) {
      return RIGHT;
    } else if (hor == 0 && vert == -1) {
      return UP;
    } else {
      return DOWN;
    }
  }

  public static boolean isOpposites(Direction d1, Direction d2) {
    return (d1 == Direction.LEFT && d2 == Direction.RIGHT) ||
        (d1 == Direction.RIGHT && d2 == Direction.LEFT) ||
        (d1 == Direction.UP && d2 == Direction.DOWN) ||
        (d1 == Direction.DOWN && d2 == Direction.UP);
  }
}
