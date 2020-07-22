import javax.swing.*;
import processing.core.PApplet;

public class Render extends PApplet {

  private Robot ed;
  private int[] hall = new int[] {2, 4, 6, 7, 3};
  private int moves;

  public static void main(String[] args) {
    PApplet.main("Render");
    try {
      javax.swing.UIManager.setLookAndFeel(
          "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (ClassNotFoundException | InstantiationException |
             IllegalAccessException | UnsupportedLookAndFeelException ignored) {
    }
  }

  public void setup() { ed = new Robot(hall, 1, true); }

  public void settings() { size(hall.length * 50, 50); }

  public void draw() {
    background(255);
    stroke(0);

    drawHall();
    drawRobot();

    if (!ed.hallIsClear()) {
      ed.move();
      moves++;

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    } else {
      noLoop();
      javax.swing.JOptionPane.showMessageDialog(null, moves);
    }
  }

  private void drawHall() {
    for (int i = 0; i < hall.length; i++) {
      fill(255);
      rect(i * 50, 0, 50, 50);
      fill(0);
      text(ed.getHall()[i], (i + 1) * 50 - 7, 45);
    }
  }

  private void drawRobot() { ellipse(25 + ed.getPos() * 50, 25, 20, 20); }
}
