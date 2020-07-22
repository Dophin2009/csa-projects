public class Color {

  int r, g, b;

  public Color(int r, int g, int b, boolean isDark) {
    this.r = r;
    this.g = g;
    this.b = b;
    if (isDark) {
      Main.darkColors.add(this);
    } else {
      Main.lightColors.add(this);
    }
  }

  public int getR() { return r; }

  public int getG() { return g; }

  public int getB() { return b; }
}
