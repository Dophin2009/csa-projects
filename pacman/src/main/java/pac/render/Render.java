package pac.render;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import pac.game.Game;
import pac.game.Level;
import pac.game.entity.mobile.Pacman;
import pac.game.entity.mobile.ai.GhostState;
import pac.game.entity.mobile.ai.OrangeAI;
import pac.game.entity.mobile.ai.PinkAI;
import pac.game.entity.mobile.ai.RedAI;
import pac.game.entity.still.Consumable;
import pac.game.entity.still.Fruit;
import pac.game.entity.still.Powerup;
import pac.game.entity.still.Space;

public class Render extends Application {

  private static final double WIN_WIDTH = 600;
  private static final double WIN_HEIGHT = 800;
  private static final double LEVEL_WIDTH = WIN_WIDTH;
  private static final double LEVEL_HEIGHT = 700;
  private static final double MENU_HEIGHT = 100;
  private static final double DOT_FACTOR =
      0.1; // dot's will be 10% of size of square
  private static final double PIECE_FACTOR =
      1.3; // piece will be 130% of size of square
  private static final double POWERUP_FACTOR = 0.8;
  private static final double FRAMERATE = 30.0;
  private double sqWidth;
  private double sqHeight;

  private GraphicsContext gc;
  private static Scene scene;
  private int frame;
  private KeyPressHandler keyPressHandler;

  private Game game;

  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage stage) {
    Group root = new Group();
    Canvas canvas = new Canvas(WIN_WIDTH, WIN_HEIGHT);
    root.getChildren().add(canvas);
    gc = canvas.getGraphicsContext2D();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("Pacman");
    stage.show();
    setup();

    Timeline loop = new Timeline();
    loop.setCycleCount(Timeline.INDEFINITE);
    KeyFrame kf = new KeyFrame(Duration.seconds(1.0 / FRAMERATE), ae -> run());
    loop.getKeyFrames().add(kf);
    loop.play();
  }

  private void setup() {
    // set up key handlers
    keyPressHandler = new KeyPressHandler();
    scene.setOnKeyPressed(keyPressHandler);

    // set game variables
    game = new Game();
    sqWidth = LEVEL_WIDTH / game.getLevel().getBoard()[0].length;
    sqHeight = LEVEL_HEIGHT / game.getLevel().getBoard().length;
  }

  private void run() {
    if (!game.isGameover()) {
      frame++;

      String key = keyPressHandler.getLastKey();

      // update
      game.update(key);
      // draw
      drawState();
    } else {
      drawGameover();
    }
  }

  private void drawState() {
    // background
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, WIN_WIDTH, WIN_HEIGHT);
    drawBoard();
    drawUI();
  }

  /**
   * Updates the graphics for the game itself
   */
  private void drawBoard() {
    Level currentLevel = game.getLevel();
    Space[][] board = currentLevel.getBoard();
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        Space e = board[r][c];
        if (!e.isWalkable()) {
          gc.setFill(Color.BLUE);
          gc.fillRect(c * sqWidth, r * sqHeight, sqWidth + 1, sqHeight + 1);
        } else if (e.getConsumable() != null) {
          Consumable con = e.getConsumable();
          if (con.getClass() == Powerup.class) {
            gc.setFill(Color.WHITE);
            gc.fillOval(c * sqWidth + sqWidth * (1 - POWERUP_FACTOR) / 2,
                        r * sqHeight + sqHeight * (1 - POWERUP_FACTOR) / 2,
                        sqWidth * POWERUP_FACTOR, sqHeight * POWERUP_FACTOR);
          } else if (con.getClass() == Fruit.class) {
            if (((Fruit)con).isVisible()) {
              gc.setFill(Color.RED);
              gc.fillOval(c * sqWidth + sqWidth * (1 - POWERUP_FACTOR) / 2,
                          r * sqHeight + sqHeight * (1 - POWERUP_FACTOR) / 2,
                          sqWidth * POWERUP_FACTOR, sqHeight * POWERUP_FACTOR);
            }
          } else {
            gc.setFill(Color.WHITE);
            gc.fillOval(c * sqWidth + sqWidth * (1 - DOT_FACTOR) / 2,
                        r * sqHeight + sqHeight * (1 - DOT_FACTOR) / 2,
                        sqWidth * DOT_FACTOR, sqHeight * DOT_FACTOR);
          }
        }

        //                if (frame < 70) {
        //                    gc.setFill(Color.WHITE);
        //                    gc.fillRect(c * sqWidth + 1, r * sqHeight +
        //                    sqHeight / 4, sqWidth + 1, sqHeight / 2);
        //                }
      }
    }

    gc.setFill(Color.GRAY);
    currentLevel.getGhosts().forEach(g -> {
      if (g.isActive()) {
        if (g.getState() == GhostState.CHASE) {
          Class cs = g.getAi().getClass();
          if (cs == RedAI.class) {
            gc.setFill(Color.RED);
          } else if (cs == OrangeAI.class) {
            gc.setFill(Color.ORANGE);
          } else if (cs == PinkAI.class) {
            gc.setFill(Color.PINK);
          } else {
            gc.setFill(Color.LIGHTBLUE);
          }
        } else {
          gc.setFill(Color.NAVY);
        }

        gc.fillOval(g.getCol() * sqWidth + sqWidth * (1 - POWERUP_FACTOR) / 2,
                    g.getRow() * sqHeight + sqHeight * (1 - POWERUP_FACTOR) / 2,
                    sqWidth * POWERUP_FACTOR, sqHeight * POWERUP_FACTOR);
      }
    });

    Pacman pac = currentLevel.getPac();
    if (pac.isActive()) {
      gc.setFill(Color.YELLOW);
      gc.fillOval(pac.getCol() * sqWidth + sqWidth * (1 - PIECE_FACTOR) / 2,
                  pac.getRow() * sqHeight + sqHeight * (1 - PIECE_FACTOR) / 2,
                  sqWidth * PIECE_FACTOR, sqHeight * PIECE_FACTOR);
    }
  }

  private void drawUI() {
    gc.setFill(Color.WHITE);
    gc.setFont(new Font("Helvetica", 14));
    gc.fillText(String.format("score: %.0f", game.getScore()), 20,
                LEVEL_HEIGHT + 30);
    gc.fillText(String.format("lives left: %d", game.getLives()), 20,
                LEVEL_HEIGHT + 50);
  }

  private void drawGameover() {
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, LEVEL_WIDTH, LEVEL_HEIGHT + 5);

    gc.setFill(Color.WHITE);
    gc.setFont(new Font("Helvetica", 20));
    gc.fillText(
        String.format("Gameover, you ended with %.0f points", game.getScore()),
        50, LEVEL_HEIGHT - 10);
  }

  public KeyPressHandler getKeyPressHandler() { return keyPressHandler; }
}
