package all;

import processing.core.PApplet;

public class RenderMandelbrot extends PApplet {

  private double lowReal = -2.0, highReal = 1.0, realInterval = 0.03;
  private double lowImag = -1.0, highImag = 1.0, imagInterval = 0.03;

  public static void main(String[] args) {
    PApplet.main(RenderMandelbrot.class);
  }

  public void settings() { fullScreen(); }

  public void setup() {
    background(255, 255, 255);
    drawSet();
  }

  public void draw() {}

  public void drawSet() {
    background(255, 255, 255);

    MandelbrotSet m = new MandelbrotSet(lowReal, highReal, realInterval,
                                        lowImag, highImag, imagInterval);
    for (ComplexNumber c : m.getSet()) {
      point(transformReal(c.getReal()), transformImag(c.getImaginary()));
    }
  }

  public float transformReal(double n) {
    return (float)((n - lowReal) * (width / (highReal - lowReal)));
  }

  public float transformImag(double n) {
    return height - (float)((n - lowImag) * (height / (highImag - lowImag)));
  }

  public void zoomIn(float x, float y) {
    double xShift = (highReal - lowReal) / 4.0;
    double yShift = (highImag - lowImag) / 4.0;
    lowReal = x - xShift;
    highReal = x + xShift;
    realInterval /= 2.0;
    lowImag = y - yShift;
    highImag = y + yShift;
    imagInterval /= 2.0;
    drawSet();
  }

  public void zoomOut(float x, float y) {
    double xShift = (highReal - lowReal) * 4.0;
    double yShift = (highImag - lowImag) * 4.0;
    lowReal = x - xShift;
    highReal = x + xShift;
    realInterval *= 2.0;
    lowImag = y - yShift;
    highImag = y + yShift;
    imagInterval *= 2.0;
    drawSet();
  }

  public void increaseResolution() {
    realInterval /= 2.0;
    imagInterval /= 2.0;
    drawSet();
  }

  public void decreaseResolution() {
    realInterval *= 2.0;
    imagInterval *= 2.0;
    drawSet();
  }

  public void keyPressed() {
    if (key == 'q') {
      increaseResolution();
    } else if (key == 'e') {
      decreaseResolution();
    } else if (key == 's') {
      realInterval = 0.03;
      imagInterval = 0.03;
    }
  }

  public void mouseClicked() {
    float mouseXTransformed =
        (float)(((mouseX / (float)width) * (highReal - lowReal)) + lowReal);
    float mouseYTransformed =
        (float)((((height - mouseY) / (float)height) * (highImag - lowImag)) +
                lowImag);
    if (mouseButton == LEFT) {
      zoomIn(mouseXTransformed, mouseYTransformed);
    }
    if (mouseButton == RIGHT) {
      zoomOut(mouseXTransformed, mouseYTransformed);
    }
  }
}
