package flower;

public class Stem {

  private int baseX, baseY;
  private double length, angle;

  public Stem(int baseX, int baseY, double length, double angle) {
    this.baseX = baseX;
    this.baseY = baseY;
    this.length = length;
    this.angle = angle;
  }

  public int getEndPointXValue() {
    return (int)(baseX + length * Math.cos(Math.toRadians(angle)));
  }

  public int getEndPointYValue() {
    return (int)(baseY + length * Math.sin(Math.toRadians(angle)));
  }

  public int getBaseX() { return baseX; }

  public void setBaseX(int baseX) { this.baseX = baseX; }

  public int getBaseY() { return baseY; }

  public void setBaseY(int baseY) { this.baseY = baseY; }

  public double getLength() { return length; }

  public void setLength(double length) { this.length = length; }

  public double getAngle() { return angle; }

  public void setAngle(double angle) { this.angle = angle; }
}
