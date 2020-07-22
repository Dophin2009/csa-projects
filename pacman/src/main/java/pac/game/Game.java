package pac.game;

import java.util.ArrayList;
import java.util.List;

/**
 * everything is ridiculously hacky and poorly organized
 * drops frames sometimes but I don't know how to deal with it
 * painful to debug
 */
public class Game {

  private static final List<String> LEVELS = new ArrayList<>();

  static { LEVELS.add("level.txt"); }

  private int levelNumber;
  private Level level;

  private double score;
  private int lives;
  private boolean gameover;

  public Game() {
    levelNumber = -1;
    level = nextLevel();

    score = 0;
    lives = 3;
  }

  public void update(String key) {
    if (lives <= 0) {
      gameover = true;
      return;
    }

    if (level.getNumberOfDots() <= 0) {
      level = nextLevel();
      if (level == null) {
        gameover = true;
        return;
      }
    }

    level.update(key);
    score = level.getScore();
  }

  private Level nextLevel() {
    levelNumber++;
    if (levelNumber >= LEVELS.size()) {
      return null;
    }
    return new Level(this, LEVELS.get(levelNumber));
  }

  public int loseLife() {
    lives--;
    return lives;
  }

  public int gainLife() {
    lives++;
    return lives;
  }

  public Level getLevel() { return level; }

  public double getScore() { return score; }

  public void setScore(double score) { this.score = score; }

  public int getLives() { return lives; }

  public void setLives(int lives) { this.lives = lives; }

  public boolean isGameover() { return gameover; }

  public void setGameover(boolean gameover) { this.gameover = gameover; }
}
