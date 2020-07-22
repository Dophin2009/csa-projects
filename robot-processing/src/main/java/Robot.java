public class Robot {

  private int[] hall;
  private int pos;
  private boolean facingRight;

  public Robot(int[] hall, int pos, boolean facingRight) {
    this.hall = hall;
    this.pos = pos;
    this.facingRight = facingRight;
  }

  private boolean forwardMoveBlocked() {
    return (facingRight && pos == hall.length - 1) ||
        (!facingRight && pos == 0);
  }

  public void move() {
    if (hall[pos] > 0) {
      hall[pos]--;
    } else {
      if (!forwardMoveBlocked()) {
        if (facingRight) {
          pos++;
        } else {
          pos--;
        }
      } else {
        facingRight = !facingRight;
      }
    }
  }

  public int clearHall() {
    int moves = 0;
    while (!hallIsClear()) {
      move();
      moves++;
    }
    return moves;
  }

  public boolean hallIsClear() {
    for (int n : hall) {
      if (n > 0) {
        return false;
      }
    }
    return true;
  }

  public int[] getHall() { return hall; }

  public int getPos() { return pos; }

  public boolean isFacingRight() { return facingRight; }
}
