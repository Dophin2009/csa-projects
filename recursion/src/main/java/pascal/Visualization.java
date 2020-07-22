package pascal;

import processing.core.PApplet;

public class Visualization extends PApplet {

  private Combination combination;
  private long[][] pascal;

  private float rW = 1;
  private float rH = 1;

  public static void main(String[] args) { PApplet.main(Visualization.class); }

  @Override
  public void setup() {
    combination = new Combination(1024);
    pascal = combination.getPascal();
  }

  @Override
  public void settings() {
    fullScreen();
  }

  @Override
  public void draw() {
    background(255);

    rectMode(CENTER);
    noStroke();

    float x = width / 2;
    for (int i = 0; i < pascal.length; i++) {
      for (int j = 0; j < pascal[i].length; j++) {
        if (pascal[i][j] % 2 == 0) {
          fill(255);
        } else {
          fill(0);
        }
        rect(x + j * rW, i * rH, rW, rH);
      }
      x = x - rW / 2;
    }
  }

  @Override
  public void mousePressed() {
    noLoop();
    int r = (int)(mouseY / rH);
    int x = (int)(width / 2 - (rW / 2 * (r + 1)));
    int c = (int)((mouseX - x) / rW);
    if (r >= 0 && c < pascal[r].length) {
      fill(0);
      textSize(16);
      text(pascal[r][c], 50, 50);
    }
  }

  @Override
  public void mouseReleased() {
    loop();
  }
}
