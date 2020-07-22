import processing.core.PApplet;

public class Square {

  public final static float SQUARE_SIZE = 50;
  public final static float SQUARE_GAP = 70;

  private float x, y, tr, br;
  private boolean hasTriangle; // Only first square will have a triangle
  private int xDirection = 1, yDirection = 0; // Direction the square is moving
  private int currentRow = 0;                 // The current row of the square

  public enum StartSide { LEFT, RIGHT }
  ; // enum class of the sides the square can start on
  private StartSide startSide;

  public Square(float x, float y, float tr, float br, boolean hasTriangle) {
    this.x = x;
    this.y = y;
    this.tr = tr;
    this.br = br;
    this.hasTriangle = hasTriangle;
    startSide = StartSide.LEFT;
  }

  // Method to draw the square
  public void draw(PApplet renderWindow) {
    renderWindow.rect(x - SQUARE_SIZE / 2, y - SQUARE_SIZE / 2, SQUARE_SIZE,
                      SQUARE_SIZE, tr, tr, br, br);
    if (hasTriangle) {
      addTriangle(renderWindow);
    }
  }

  // Method to draw the head triangle
  public void addTriangle(PApplet renderWindow) {
    if (xDirection == 0) {
      renderWindow.triangle(x - SQUARE_SIZE / 2, y + SQUARE_SIZE / 2,
                            x + SQUARE_SIZE / 2, y + SQUARE_SIZE / 2, x,
                            y + SQUARE_SIZE + 10);
    } else if (xDirection == 1) {
      renderWindow.triangle(x + SQUARE_SIZE / 2, y - SQUARE_SIZE / 2,
                            x + SQUARE_SIZE / 2, y + SQUARE_SIZE / 2,
                            x + SQUARE_SIZE + 10, y);
    } else if (xDirection == -1) {
      renderWindow.triangle(x - SQUARE_SIZE / 2, y - SQUARE_SIZE / 2,
                            x - SQUARE_SIZE / 2, y + SQUARE_SIZE / 2,
                            x - SQUARE_SIZE - 10, y);
    }
  }

  public void setX(float x) { this.x = x; }
  public float getX() { return x; }

  public void setY(float y) { this.y = y; }
  public float getY() { return y; }

  public void setTr(float tr) { this.tr = tr; }

  public void setyDirection(int yDirection) { this.yDirection = yDirection; }
  public int getyDirection() { return yDirection; }

  public void setxDirection(int xDirection) { this.xDirection = xDirection; }
  public int getxDirection() { return xDirection; }

  public void changeDirection() {
    if (xDirection == -1) {
      xDirection = 1;
    } else {
      xDirection = -1;
    }
  }

  public void setCurrentRow(int currentRow) { this.currentRow = currentRow; }
  public int getCurrentRow() { return currentRow; }

  public void setStartSide(StartSide startSide) { this.startSide = startSide; }
  public StartSide getStartSide() { return startSide; }
}
