import java.awt.*;

public class ColorScheme {

  private int[] background, fill, stroke;

  public ColorScheme(Color background, Color stroke, Color fill) {
    this.background =
        new int[] {background.getR(), background.getG(), background.getB()};
    this.stroke = new int[] {stroke.getR(), stroke.getG(), stroke.getB()};
    this.fill = new int[] {fill.getR(), fill.getG(), fill.getB()};
  }

  public int[] getBackground() { return background; }

  public int[] getFill() { return fill; }

  public int[] getStroke() { return stroke; }
}
