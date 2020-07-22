package flower;

import java.awt.Color;
import java.util.Random;
import processing.core.PApplet;

public class FlowerRunner extends PApplet {

  static Random randy = new Random();
  static final float MIN_LEN_PERCENTAGE = 0.6f;
  static final float MAX_LEN_PERCENTAGE = 0.8f;
  static final float MIN_ANGLE_CHANGE = 1.0f;
  static final float MAX_ANGLE_CHANGE = 45.0f;
  static Color[] funColors =
      new Color[] {Color.RED, Color.ORANGE, Color.YELLOW, Color.MAGENTA};
  private boolean hasStarted;

  public static void main(String[] args) { PApplet.main(FlowerRunner.class); }

  public void setup() {}

  public void settings() { size(800, 800); }

  public void draw() {}

  private void drawFlower(Stem stem, int depth) {
    strokeWeight(depth);
    if (depth == 0) {
      // draw the flowers - base case
      this.line(stem.getBaseX(), stem.getBaseY(), stem.getEndPointXValue(),
                stem.getEndPointYValue());
      int randIndex = randy.nextInt(funColors.length);
      stroke(funColors[randIndex].getRGB());
      fill(funColors[randIndex].getRGB());
      this.ellipse(stem.getEndPointXValue(), stem.getEndPointYValue(), 6, 6);
      noFill();
      stroke(80, 118, 66); // green again
    } else {
      // recursion here
      int numBranches = randy.nextInt(4) + 5;
      line(stem.getBaseX(), stem.getBaseY(), stem.getEndPointXValue(),
           stem.getEndPointYValue());
      int branchDir = 1;
      for (int i = 0; i < numBranches; i++) {
        branchDir *= -1;
        double newLength =
            stem.getLength() * random(MIN_LEN_PERCENTAGE, MIN_LEN_PERCENTAGE);
        double newAngle =
            stem.getAngle() * random(MIN_ANGLE_CHANGE, MAX_ANGLE_CHANGE);
        drawFlower(new Stem(stem.getEndPointXValue(), stem.getEndPointYValue(),
                            newLength, newAngle),
                   depth - 1);
      }
    }
  }

  public void showFlower() {
    background(255, 255, 204);
    double branchLen = randy.nextInt(101) + 100; // in [100,200]
    double initAngle = randy.nextInt(5) - 92;    // in [-92,-88]
    Stem b = new Stem(400, 700, branchLen, initAngle);
    drawFlower(b, 6);
  }

  public void keyPressed() { showFlower(); }
}
