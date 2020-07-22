public class TurtleMain {

  private static final int DELAY = 2;

  public static void main(String[] args) {

    PowerfulTurtle ed = new PowerfulTurtle();
    ed.setDelay(DELAY);

    PowerfulTurtle steve = new PowerfulTurtle();
    steve.setDelay(DELAY + 1);

    PowerfulTurtle noseGod = new PowerfulTurtle();
    noseGod.setDelay(DELAY + 11);

    new Thread(new Runnable() {
      @Override
      public void run() {
        // Move into position
        ed.penUp();
        ed.turnRight(90);
        ed.movingMove(275);
        ed.turnLeft(90);
        ed.penDown();

        // Draw head
        for (int i = 0; i < 2; i++) {
          ed.drawRegularPolygon(360, 5 - i * 0.1);
          ed.penUp();
          ed.turnLeft(90);
          ed.movingMove(5);
          ed.turnRight(90);
          ed.penDown();
        }

        // Draw frown
        ed.penUp();
        ed.turnLeft(100);
        ed.movingMove(120);
        ed.penDown();
        ed.turnRight(60);
        for (int i = 0; i < 110; i++) {
          ed.turnRight(0.5);
          ed.movingMove(2);
        }
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        // Move to left eye position and draw eye
        steve.penUp();
        steve.turnLeft(105);
        steve.movingMove(100);
        steve.penDown();
        steve.drawEye(25);

        // Move to right eye position and draw eye
        steve.turnRight(105);
        steve.movingMove(200);
        steve.drawEye(25);

        // Draw glasses
        steve.movingMove(50);
        // steve.drawMovingRectangle(120, -50);
        steve.turnLeft(180);
        steve.movingMove(315);
        steve.drawMovingRectangle(120, 50);
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        noseGod.penUp();
        noseGod.movingMove(100);
        noseGod.penDown();
        noseGod.drawFibNose(6);
      }
    }).start();
  }
}
