package mondrian;

import processing.core.PApplet;

public class Mondrian extends PApplet {

  public static void main(String[] args) { PApplet.main(Mondrian.class); }

  private int[] colors = {
      color(0),         color(255),       color(255, 0, 0),
      color(0, 255, 0), color(0, 0, 255), color(255, 200, 0),
      color(255),       color(255),       color(255)};

  @Override
  public void setup() {
    background(255);
    mondrian(7, 0, 0, width, height);
  }

  @Override
  public void settings() {
    fullScreen();
  }

  @Override
  public void draw() {}

  private void mondrian(int depth, float x, float y, float w, float h) {
    if (depth == 0) {
      strokeWeight(random(0.1f, 0.3f));
      int colorIndex = (int)random(0, colors.length);
      fill(colors[colorIndex]);

      rect(x, y, w, h);
      return;
    }

    float newX = random(x, x + w);
    float newY = random(y, y + h);
    mondrian(depth - 1, x, y, newX - x, newY - y);
    mondrian(depth - 1, newX, y, x + w - newX, newY - y);
    mondrian(depth - 1, x, newY, newX - x, y + h - newY);
    mondrian(depth - 1, newX, newY, x + w - newX, y + h - newY);
  }

  @Override
  public void mousePressed() {
    mondrian(7, 0, 0, width, height);
  }
}
