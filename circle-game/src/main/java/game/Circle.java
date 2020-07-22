package game;

public class Circle {

  private int width, height;
  private float x, y, vx, vy, size;
  private Color color;

  public Circle(int width, int height) {
    this.width = width;
    this.height = height;
    respawn();
    size = (float)(Math.random() * 70 + 10);
  }

  public Circle(int width, int height, float x, float y, float vx, float vy,
                float size, Color color) {
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.size = size;
    this.color = color;
  }

  public void update() {
    move();
    checkBoundary();
  }

  public void checkBoundary() {
    if (x + size / 2 < 0 || x - size / 2 > width) {
      respawn();
    }
    if (y + size / 2 < 0 || y - size / 2 > height) {
      respawn();
    }
  }

  public void move() {
    x += vx;
    y += vy;
  }

  public void respawn(float playerSize) {
    respawn();
    if (size < 100) {
      size = (float)((Math.random() * (playerSize * 1.5) + (playerSize * 0.5)));
    } else {
      size = 100;
    }
  }

  public void respawn() {

    switch ((int)(Math.random() * 4)) {
    case 0: // spawn on top side
      x = (float)(Math.random() * width);
      y = -10;
      break;
    case 1: // spawn on right side
      x = width + 10;
      y = (float)(Math.random() * height);
      break;
    case 2: // spawn on bottom side
      x = (float)(Math.random() * width);
      y = height + 10;
      break;
    case 3: // spawn on left side
      x = -10;
      y = (float)(Math.random() * height);
      break;
    }

    vx = (float)(Math.signum(-(x - width / 2.0)) * ((Math.random() * 2) + 1));
    vy = (float)(Math.signum(-(y - height / 2.0)) * ((Math.random() * 2) + 1));

    color = new Color((int)(Math.random() * 256), (int)(Math.random() * 256),
                      (int)(Math.random() * 256));
  }

  public int getWidth() { return width; }

  public int getHeight() { return height; }

  public float getX() { return x; }

  public void setX(float x) { this.x = x; }

  public float getY() { return y; }

  public void setY(float y) { this.y = y; }

  public float getVx() { return vx; }

  public float getVy() { return vy; }

  public float getSize() { return size; }

  public void setSize(float size) { this.size = size; }

  public Color getColor() { return color; }
}
