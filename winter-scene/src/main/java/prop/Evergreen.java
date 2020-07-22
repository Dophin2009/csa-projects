package prop;

public class Evergreen {

  private float x1, y1;
  private float x2, y2;
  private float x3, y3;
  private int layer;
  private Evergreen hat;

  public Evergreen(float x1, float y1, float height, int layer) {
    this.layer = layer;

    this.x1 = x1;
    this.y1 = y1;

    float dx = (float)(height / Math.sqrt(3));
    x2 = x1 - dx;
    y2 = y1 + height;

    x3 = x1 + dx;
    y3 = y1 + height;

    if (layer != 1) {
      hat = new Evergreen(x1, y1 - height * 0.3f, height * 0.7f, layer - 1);
    }
  }

  public float getX1() { return x1; }

  public void setX1(float x1) { this.x1 = x1; }

  public float getY1() { return y1; }

  public void setY1(float y1) { this.y1 = y1; }

  public float getX2() { return x2; }

  public void setX2(float x2) { this.x2 = x2; }

  public float getY2() { return y2; }

  public void setY2(float y2) { this.y2 = y2; }

  public float getX3() { return x3; }

  public void setX3(float x3) { this.x3 = x3; }

  public float getY3() { return y3; }

  public void setY3(float y3) { this.y3 = y3; }

  public int getLayer() { return layer; }

  public void setLayer(int layer) { this.layer = layer; }

  public Evergreen getHat() { return hat; }

  public void setHat(Evergreen hat) { this.hat = hat; }
}
