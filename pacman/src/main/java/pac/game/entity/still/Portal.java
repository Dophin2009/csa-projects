package pac.game.entity.still;

import pac.game.entity.mobile.MovingEntity;

public class Portal extends Space {

  private Portal other;

  public Portal(int row, int col, Portal other) {
    super(row, col, true, null);
    this.other = other;
  }

  public void teleport(MovingEntity e) {
    e.setLocation(other.getRow(), other.getCol());
  }

  public Portal getOther() { return other; }

  public void setOther(Portal other) { this.other = other; }
}
