package scene;

import cprocessing.util.base.CPApplet;
import cprocessing.util.stats.StatNode;
import cprocessing.util.stats.Stats;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import prop.Branch;
import prop.Evergreen;
import prop.Snowball;
import prop.Snowflake;

public class Render extends CPApplet {

  private static final int FRAME_RATE = 480;
  private static final int[] BG_COLOR = {93, 126, 140};

  private static final int SNOWMAN_HEIGHT = 4;
  private static final int NUMBER_OF_FLAKES = 150;
  private static final int FLAKE_SIZE = 3;
  private static final int NUMBER_OF_TREES = 15;
  private static final float WIND_INCREMENT = 0.15f;

  private Wind wind;
  private Wind gust;

  private List<Snowflake> flakes;
  private Snowball sBase;
  private List<Evergreen> trees;

  public static void main(String[] args) { CPApplet.main(Render.class); }

  public Render() { super(Render.class); }

  @Override
  public void setup() {
    trees = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_TREES; i++) {
      float x = ((float)width / 2) + 100 * ((float)NUMBER_OF_TREES / 2 - i);
      float h = 120 + (float)Math.abs(NUMBER_OF_TREES / 2 - i) * 15;
      float y = height - h;
      Evergreen e = new Evergreen(x, y, h, 4);
      trees.add(e);
    }

    sBase = new Snowball((float)width / 2, height - 100, 150, SNOWMAN_HEIGHT);
    sBase.init();

    wind = new Wind(2.25f, 0);
    gust = new Wind(10, 0, 150);

    flakes = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_FLAKES; i++) {
      Snowflake s = new Snowflake(
          (float)(Math.random() * width * 2) - ((float)width / 2),
          -(float)Math.random() * height, FLAKE_SIZE, wind.getDx(), 0);
      s.resetVelocity(wind);
      flakes.add(s);
    }

    initializeStats();

    frameRate(FRAME_RATE);
    noStroke();
  }

  @Override
  public void settings() {
    fullScreen();
  }

  @Override
  public void draw() {
    super.draw();

    background(BG_COLOR[0], BG_COLOR[1], BG_COLOR[2]);

    crstats.display();
    stats.display(0, 100);

    // Auto-activate gust every 2000 frames and force snow on branches to fall.
    if (frameCount % 2000 == 0) {
      blow();
    }

    // Draw the background trees.
    drawForest();

    fill(255);
    rect(-10, (float)height - 3, (float)width + 20, 5);

    for (int i = 0; i < flakes.size(); i++) {
      Snowflake s = flakes.get(i);
      // If snowflake is within screen, draw; otherwise, do not draw.
      if (s.isWithin(this, FLAKE_SIZE)) {
        drawFlake(s);
      }
      // If snowflake is moving, update position.
      if (s.getVy() != 0) {
        s.update(wind, gust);
        // Test for collision on even frames only.
        if ((frameCount % 2) == 0) {
          // If snowflake hits bottom of screen or collides with a snowball or
          // branch, set to stop falling.
          if (s.getY() >= height - FLAKE_SIZE ||
              s.testSnowballCollision(sBase)) {
            // If snowflake stopped within screen, set to stop moving.
            if (s.isWithin(this, 5 * FLAKE_SIZE)) {
              s.setVy(0);
              // If snowflake-branch collision is on, generate new snowflake.
              if (!s.isFreefalling()) {
                Snowflake f = new Snowflake(0, 0, 3, wind.getDx(), 0);
                f.resetVelocity(wind);
                f.resetPosition(this);
                flakes.add(f);
              }
              // If snowflake is outside of screen, reset position.
            } else {
              s.resetPosition(this);
            }
          }

          // If snowflake collides with two or more others, replace with single
          // larger snowflake.
          List<Snowflake> clump =
              flakes.stream()
                  .filter(f -> s.testCollision(f) && f.getVy() == 0)
                  .collect(Collectors.toList());
          if (clump.size() >= 3) {
            flakes.removeAll(clump);
            i += clump.size() - 1;
            float r =
                (float)(s.getRadius() +
                        Math.min(clump.stream()
                                         .mapToDouble(Snowflake::getRadius)
                                         .sum() /
                                     3,
                                 Double.POSITIVE_INFINITY));
            Snowflake n =
                new Snowflake(s.getX(), s.getY(), r, s.getVx(), s.getVy());
            flakes.add(n);
          }
        }
      }
    }

    // Draw the snowman.
    drawSnowball(sBase);

    gust.run();
  }

  @Override
  public void keyPressed() {
    // Press 'spacebar' to pause
    if (key == ' ') {
      if (looping) {
        noLoop();
      } else {
        loop();
      }
    }

    // Press 'z' or 'c' to change wind direction horizontally
    if (key == 'z') {
      wind.incrementDx(-WIND_INCREMENT);
    }
    if (key == 'c') {
      wind.incrementDx(WIND_INCREMENT);
    }

    // Press 's' or 'x' to change wind direction vertically.
    if (key == 's') {
      wind.incrementDy(WIND_INCREMENT);
      forSnow(s -> {
        if (s.getVy() != 0) {
          s.incrementVy(WIND_INCREMENT);
        }
      });
    }
    if (key == 'x') {
      wind.incrementDy(-WIND_INCREMENT);
      forSnow(s -> {
        if (s.getVy() != 0) {
          s.incrementVy(-WIND_INCREMENT);
        }
      });
    }

    // Press 'f' to force all snow on branches to fall to the ground.
    if (key == 'f') {
      forceSnowFall();
    }

    // Press left and right arrow keys to move snowman.
    if (keyCode == LEFT) {
      sBase.forSnowman(s -> s.move(-3));
      forSnow(s -> {
        if (s.getVy() == 0 && s.getY() < height - FLAKE_SIZE) {
          s.fall(wind);
        }
      });
    }
    if (keyCode == RIGHT) {
      sBase.forSnowman(s -> s.move(3));
      forSnow(s -> {
        if (s.getVy() == 0 && s.getY() < height - FLAKE_SIZE) {
          s.fall(wind);
        }
      });
    }

    // Press 'g' to active gust.
    if (key == 'g') {
      blow();
    }

    // Press 'a' and 'd' to tune intensity and direction of gust.
    if (key == 'a' && !gust.isActive()) {
      gust.incrementDx(-WIND_INCREMENT);
    }
    if (key == 'd' && !gust.isActive()) {
      gust.incrementDx(WIND_INCREMENT);
    }
  }

  // Draw an ellipse representing a single snowflake.
  private void drawFlake(Snowflake f) {
    noStroke();
    fill(255);
    float centerX = f.getX();
    float centerY = f.getY();
    float radius = f.getRadius();
    ellipse(centerX, centerY, radius * 2, radius * 2);
  }

  // Draw a snowman of the given base snowball.
  private void drawSnowball(Snowball b) {
    noStroke();
    fill(255);
    ellipse(b.getX(), b.getY(), b.getRadius() * 2, b.getRadius() * 2);
    if (b.getHat() != null) {
      drawBranch(b.getRightB());
      drawBranch(b.getLeftB());
      drawSnowball(b.getHat());
    }
  }

  // Draw a branch of the given root.
  private void drawBranch(Branch b) {
    stroke(255);
    strokeWeight(5);
    line(b.getX1(), b.getY1(), b.getX2(), b.getY2());
    b.getTwigs().forEach(this::drawBranch);
  }

  // Draw background trees.
  private void drawForest() {
    fill(102, 145, 163);

    trees.forEach(this::drawEvergreen);
  }

  // Draw a single tree of given base.
  private void drawEvergreen(Evergreen t) {
    triangle(t.getX1(), t.getY1(), t.getX2(), t.getY2(), t.getX3(), t.getY3());
    if (t.getHat() == null) {
      return;
    }
    drawEvergreen(t.getHat());
  }

  // Run code for all snowflakes.
  private void forSnow(Consumer<Snowflake> func) { flakes.forEach(func); }

  // Force snow on branches to fall.
  private void forceSnowFall() {
    forSnow(s -> {
      if (s.getVy() == 0 && s.getY() < height - FLAKE_SIZE) {
        s.fall(wind);
      }
    });
  }

  // Activate gust and force snow on branches to fall.
  private void blow() {
    gust.start();
    forceSnowFall();
  }

  private void initializeStats() {
    StatNode<Integer> numFlakes =
        new StatNode<>("flakes", 0, () -> flakes.size());
    stats.add(numFlakes);

    StatNode<Integer> minFlakes = new StatNode<>("min", 0);
    minFlakes.setUpdateFunc(
        () -> Math.min(minFlakes.getValue(), flakes.size()));
    numFlakes.addChild(minFlakes);

    StatNode<Integer> maxFlakes = new StatNode<>("max", 0);
    minFlakes.setUpdateFunc(
        () -> Math.max(maxFlakes.getValue(), flakes.size()));
    numFlakes.addChild(minFlakes);

    StatNode<Float> avgRadius = new StatNode<>("avg. radius", 0f, () -> {
      OptionalDouble d =
          flakes.stream().mapToDouble(Snowflake::getRadius).average();
      if (d.isPresent()) {
        return (float)d.getAsDouble();
      }
      return 0f;
    });
    avgRadius.setCustomFormatter(Stats.FLOAT_FORMATTER);
    numFlakes.addChild(avgRadius);

    StatNode<Float> horizWind =
        new StatNode<>("horiz. wind", 0f, () -> wind.getDx());
    horizWind.setCustomFormatter(Stats.FLOAT_FORMATTER);
    stats.add(horizWind);

    StatNode<Float> vertWind =
        new StatNode<>("vert. wind", 0f, () -> wind.getDy());
    horizWind.setCustomFormatter(Stats.FLOAT_FORMATTER);
    stats.add(vertWind);

    StatNode<Float> gustStrength =
        new StatNode<>("gust", 0f, () -> gust.getDx());
    gustStrength.setCustomFormatter(Stats.FLOAT_FORMATTER);
    stats.add(gustStrength);

    StatNode<Boolean> gustActive =
        new StatNode<>("gust active", false, () -> gust.isActive());
    gustStrength.addChild(gustActive);
  }
}
