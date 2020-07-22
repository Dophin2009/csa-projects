package scene;

public class Wind {

  private static final float START_STOP_INCR = 0.05f;

  private float cdx, cdy;
  private float dx, dy;
  private float duration, remainingDuration;
  private boolean active;
  private boolean starting, stopping;

  public Wind(float dx, float dy) {
    this.dx = dx;
    this.dy = dy;
    duration = -1;
  }

  public Wind(float dx, float dy, float duration) {
    this.dx = dx;
    this.dy = dy;
    this.duration = duration;

    cdx = 0;
    cdy = 0;
  }

  public void start() {
    active = true;
    starting = true;
    remainingDuration = duration;
  }

  public void run() {
    float incr = (dx > 0 ? 1 : -1) * START_STOP_INCR;

    if (starting) {
      cdx += incr;
      if ((dx > 0 && cdx >= dx) || (dx < 0 && cdx <= dx)) {
        starting = false;
      }
    }

    if (!starting && !stopping &&
        ((dx > 0 && cdx >= dx) || (dx < 0 && cdx <= dx))) {
      decrementRemainingDuration();
      if (remainingDuration == 0) {
        stopping = true;
      }
    }

    if (stopping) {
      cdx += -incr;
      if ((dx > 0 && cdx <= 0) || (dx < 0 && cdx >= 0)) {
        stop();
      }
    }
  }

  public void stop() {
    active = false;
    stopping = false;
    remainingDuration = duration;

    cdx = 0;
    cdy = 0;
  }

  public void decrementRemainingDuration() { remainingDuration--; }

  public void incrementDx(float incr) { dx += incr; }

  public float getDx() { return dx; }

  public void setDx(float dx) { this.dx = dx; }

  public void incrementDy(float incr) { dy += incr; }

  public float getDy() { return dy; }

  public void setDy(float dy) { this.dy = dy; }

  public float getCdx() { return cdx; }

  public float getCdy() { return cdy; }

  public float getDuration() { return duration; }

  public void setDuration(float duration) { this.duration = duration; }

  public float getRemainingDuration() { return remainingDuration; }

  public void setRemainingDuration(float remainingDuration) {
    this.remainingDuration = remainingDuration;
  }

  public boolean isActive() { return active; }

  public void setActive(boolean active) { this.active = active; }
}
