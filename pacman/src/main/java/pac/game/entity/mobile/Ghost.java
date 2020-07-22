package pac.game.entity.mobile;

import java.lang.reflect.InvocationTargetException;
import pac.game.Level;
import pac.game.entity.Consumable;
import pac.game.entity.mobile.ai.GhostAI;
import pac.game.entity.mobile.ai.GhostState;

public class Ghost extends MovingEntity implements Consumable {

  private static final double MOVE_INTERVAL = 0.2;

  private GhostAI ai;
  private boolean active;
  private int value;

  public Ghost(Class<? extends GhostAI> type, double row, double col,
               Level board, Pacman pac) {
    super(row, col, MOVE_INTERVAL, Direction.RIGHT);
    ai = Ghost.getInstance(type, this, board, pac);
    active = false;
    value = 200;
  }

  public Direction nextDirection() {
    if (!active) {
      return Direction.NONE;
    }

    if (ai.atCrossroads()) {
      double[] next = ai.nextMove();
      return Direction.fromSignArray((int)(next[1] - getCol()),
                                     (int)(next[0] - getRow()));
    }
    return getDirection();
  }

  @Override
  public double consume() {
    respawn();

    return value;
  }

  @Override
  public boolean canChangeDirection(Direction newDir) {
    return true;
  }

  public GhostAI getAi() { return ai; }

  public void setAi(GhostAI ai) { this.ai = ai; }

  public GhostState getState() { return ai.getState(); }

  public boolean isActive() { return active; }

  public void setActive(boolean active) { this.active = active; }

  public int getValue() { return value; }

  public void setValue(int value) { this.value = value; }

  private static GhostAI getInstance(Class<? extends GhostAI> type, Ghost ghost,
                                     Level board, Pacman pac) {
    try {
      return type.getConstructor(Ghost.class, Level.class, Pacman.class)
          .newInstance(ghost, board, pac);
    } catch (InstantiationException | IllegalAccessException |
             InvocationTargetException | NoSuchMethodException ignored) {
    }

    throw new IllegalArgumentException("Invalid AI type " + type);
  }
}
