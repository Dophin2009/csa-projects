package game;

public class Color {

  private int red, green, blue;
  private float alpha;

  public Color(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public Color(int red, int green, int blue, float alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (this.getClass() != other.getClass()) {
      return false;
    }
    Color c = (Color)other;
    return red == c.getRed() && green == c.getGreen() && blue == c.getBlue();
  }

  public int getRed() { return red; }

  public int getGreen() { return green; }

  public int getBlue() { return blue; }

  public float getAlpha() { return alpha; }

  public void setAlpha(float alpha) { this.alpha = alpha; }
}
