package game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import processing.core.PApplet;
import processing.core.PFont;

public class Driver extends PApplet {

  public static final int FRAMERATE = 40;
  private static final float UI_BAR_SIZE = 30;

  private static final float CONSUMPTION_THRESHOLD = 5;
  private static final float PLAYER_SIZE = 45;
  private Character character;

  private boolean gameOver = false;
  private File leaderboard = new File("leaderboard.txt");
  private String name;
  private int score = 0;

  private static final int CIRCLES = 50;
  private List<Circle> circles;

  public static void main(String[] args) { PApplet.main(Driver.class); }

  @Override
  public void setup() {
    noCursor();
    frameRate(FRAMERATE);

    try {
      UIManager.setLookAndFeel(
          "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception ignored) {
    }

    restart();
  }

  @Override
  public void settings() {
    fullScreen();
  }

  @Override
  public void draw() {
    background(255);

    checkHotkeys();

    // Move the player character to the x and y values that the mouse pointer is
    // at.
    character.update(mouseX, mouseY);

    // Update enemies and draw them.
    for (Circle c : circles) {
      c.update();

      Color fill = c.getColor();
      fill(fill.getRed(), fill.getGreen(), fill.getBlue());
      if (c.getSize() > 200) {
        ellipse(c.getX(), c.getY(), 200, 200);
      } else {
        ellipse(c.getX(), c.getY(), c.getSize(), c.getSize());
      }

      // Test collision: if player is larger, consumption; otherwise, game over.
      if (character.collidingWith(c) && !character.getEvade().isActive()) {
        if (character.getSize() < c.getSize() - CONSUMPTION_THRESHOLD) {
          noLoop();
          gameOver = true;
          break;
        } else if (character.getSize() > c.getSize() + CONSUMPTION_THRESHOLD) {
          score += c.getSize();
          c.respawn(character.getSize());
          character.grow();
        }
      }

      for (Projectile p : character.getBurst().getProjectiles()) {
        if (p.collidingWith(c) && character.getBurst().isActive()) {
          c.setSize(c.getSize() * 0.5f);
        }
      }
    }

    // Draw the player character.
    Color fill = character.getColor();
    fill(fill.getRed(), fill.getGreen(), fill.getBlue(), fill.getAlpha());
    ellipse(character.getX(), character.getY(), character.getSize(),
            character.getSize());

    if (character.getBurst().isActive()) {
      for (Projectile p : character.getBurst().getProjectiles()) {
        ellipse(p.getX(), p.getY(), p.getSize(), p.getSize());
      }
    }

    drawUi();

    if (gameOver) {
      try {
        Thread.sleep(750);
      } catch (InterruptedException ignored) {
      }
      clear();
      writeToLeaderboard();
      showLeaderboard(getLeaderboard());
    }
  }

  public void keyPressed() {
    if (key == 'r') {
      restart();
    }
  }

  private void checkHotkeys() {
    Ability ultimate = character.getUltimate();
    Ability evade = character.getEvade();
    Ability burst = character.getBurst();

    if (keyPressed) {
      switch (key) {
      case 'e': // activate ultimate ability
        if (ultimate.getCooldown() == 0) {
          character.useUltimate();
          for (Circle c : circles) {
            c.setSize(c.getSize() * Character.ULT_SIZE_REDUCTION);
          }
        }
        break;
      case 'f': // activate evade ability
        if (evade.getCooldown() == 0) {
          character.useEvade();
        }
        break;
      case 'q': // activate shoot ability
        if (burst.getCooldown() == 0) {
          character.useBurst();
        }
        break;
      default:
      }
    }
  }

  private void drawUi() {
    fill(238, 232, 237);
    stroke(255);
    rect(0, height - UI_BAR_SIZE, width, height);

    PFont f = createFont("Arial", 16, true);
    textFont(f, 16);
    fill(0);

    // Display score
    text("score: " + score, 15, height - UI_BAR_SIZE + 5, width - 5, 50);

    // Display ultimate cooldown
    text("Ultimate (E): " +
             String.format("%.1f",
                           character.getUltimate().getCooldown() / FRAMERATE) +
             "s",
         width - 200, height - UI_BAR_SIZE + 5, width - 5, height - 5);

    // Display evade cooldown
    text("Evade (F): " +
             String.format("%.1f",
                           character.getEvade().getCooldown() / FRAMERATE) +
             "s",
         width - 400, height - UI_BAR_SIZE + 5, width - 205, height - 5);

    // Display evade duration next to player circle
    if (character.getEvade().isActive()) {
      text("ev: " + String.format("%.1f", character.getEvade().getDuration() /
                                              FRAMERATE),
           mouseX + character.getSize() / 2, mouseY + character.getSize() / 2);
    }

    // Display burst cooldown
    text("Burst (Q): " +
             String.format("%.1f",
                           character.getBurst().getCooldown() / FRAMERATE) +
             "s",
         width - 600, height - UI_BAR_SIZE + 5, width - 405, height - 5);

    // Display burst duration next to player circle
    if (character.getBurst().isActive()) {
      text("bst: " + String.format("%.1f", character.getBurst().getDuration() /
                                               FRAMERATE),
           mouseX + character.getSize() / 2,
           mouseY + character.getSize() / 2 + 20);
    }

    textFont(f, 11);
    text(String.format("%.1f", frameRate) + " fps", 5, 5, 70, 25);
  }

  private void restart() {
    gameOver = false;
    name = javax.swing.JOptionPane.showInputDialog("Name?");

    loop();
    score = 0;
    circles = new ArrayList<>();
    for (int i = 0; i < CIRCLES; i++) {
      circles.add(new Circle(width, height));
    }
    for (Circle c : circles) {
      c.respawn(PLAYER_SIZE);
    }

    character = new Character((float)width / 2, (float)height / 2, PLAYER_SIZE,
                              new Color(0, 0, 0, 255));
  }

  private void showLeaderboard(List<Score> scores) {
    clear();
    background(255);

    fill(238, 232, 237);
    stroke(255);
    rect((width / 2.0f) - 150, 200, 300, 600);

    fill(0);
    PFont f = createFont("Monospaced", 16, true);

    textFont(f, 25);
    text("Leaderboard", width / 2.0f - 125, 215, width / 2.0f + 125, 250);

    textFont(f, 18);

    for (int i = 1; i <= 10; i++) {
      try {
        Score record = scores.get(i - 1);
        text(i + ". " + record.name, (float)((width / 2.0) - 125), 240 + 40 * i,
             (float)(width / 2.0), 200 + 80 * i);
        text(record.score + "", (float)(width / 2.0), 240 + 40 * i,
             (float)(width / 2.0) + 125, 200 + 80 * i);
      } catch (IndexOutOfBoundsException e) {
        break;
      }
    }
  }

  private void writeToLeaderboard() {
    try {
      PrintWriter writer =
          new PrintWriter(new FileOutputStream(leaderboard, true));
      writer.println(name + " " + score);
      writer.flush();
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private List getLeaderboard() {
    List<Score> scores = new ArrayList<>();

    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(leaderboard));
      String line = "";
      while ((line = reader.readLine()) != null) {
        String[] scoreProperties = line.split("\\s+");
        scores.add(new Score(scoreProperties[0],
                             Integer.parseInt(scoreProperties[1])));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    Collections.sort(scores, Collections.reverseOrder());
    return scores;
  }
}

class Score implements Comparable {

  String name;
  int score;

  Score(String name, int score) {
    this.name = name;
    this.score = score;
  }

  @Override
  public int compareTo(Object other) {
    return Integer.compare(this.score, ((Score)other).score);
  }
}
