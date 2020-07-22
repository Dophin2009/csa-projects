import processing.core.PApplet;

public class Shapes extends PApplet {

  private static final int SPACING = 10;
  private int circleNumber = 0;
  private static int circleRadius = 30;
  private int drawAngle = 0;

  public static void main(String[] args) { PApplet.main("Shapes"); }

  public void setup() {
    // background(255, 250, 234);
    frameRate(25);
  }

  public void settings() { size(800, 600); }

  public void draw() {}

  private float direction, length;
  public void dragonCurve(float x, float y, int depth) {
    noLoop();

    String s = generateString("FX", depth);
    direction = 0;
    length = 5;
    for (int i = 0; i < s.length(); i++) {
      char letter = s.charAt(i);
      switch (letter) {
      case '+':
        direction += Math.PI / 2;
        break;
      case '-':
        direction -= Math.PI / 2;
        break;
      case 'F':
        float x2 = x + length * cos(direction);
        float y2 = y + length * sin(direction);
        line(x, y, x2, y2);
        x = x2;
        y = y2;
      }
    }
  }

  // Lindenmayer System: X -> X+YF+ / Y -> -FX-Y
  public String generateString(String s, int depth) {
    if (depth == 0) {
      return s;
    }
    String newS = "";
    for (int i = 0; i < s.length(); i++) {
      String letter = s.substring(i, i + 1);
      if (letter.equals("X")) {
        newS += "X+FY+";
      } else if (letter.equals("Y")) {
        newS += "-XF-Y";
      } else {
        newS += letter;
      }
    }
    return generateString(newS, depth - 1);
  }

  public void loadingCircle() {
    clear();
    background(255, 250, 234);
    float xGap;
    float yGap;
    for (int i = 0; i < 10; i++) {
      xGap = 30 * cos(drawAngle) + SPACING;
      yGap = 30 * sin(drawAngle) + SPACING;
      ellipse(mouseX + xGap, mouseY + yGap, 20, 20);
    }
    drawAngle++;
    if (drawAngle == 360) {
      drawAngle = 0;
    }
  }

  public void crazyCircles() {
    if (mousePressed) {
      int factor = circleNumber * SPACING;
      fillRandomColor();
      ellipse(mouseX + factor, mouseY, circleRadius, circleRadius);
      fillRandomColor();
      ellipse(mouseX - factor, mouseY, circleRadius, circleRadius);
      fillRandomColor();
      ellipse(mouseX, mouseY + factor, circleRadius, circleRadius);
      fillRandomColor();
      ellipse(mouseX, mouseY - factor, circleRadius, circleRadius);
      circleNumber++;
      if (circleNumber == 10) {
        circleNumber = 0;
        background(255, 250, 234);
      }
    }
  }

  public void fillRandomColor() {
    int r = (int)(Math.random() * 256);
    int g = (int)(Math.random() * 256);
    int b = (int)(Math.random() * 256);
    fill(r, g, b);
  }
}
