package driver;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import java.util.List;
import processing.core.PApplet;
import processing.core.PImage;
import tour.Knight;
import tour.Square;

public class KnightsProcessing extends PApplet {

  private static final String KNIGHT_IMAGE_PATH = "ihe.png";
  private static final String SQUARE_IMAGE_PATH = "destiny.png";
  private static final String MUSIC_PATH = "local_forecast.mp3";

  private static final int BOARD_SIZE = 32;

  private int[][] order;
  private float squareSize;

  private Knight k;
  private List<Square> sequence;
  private PImage knightImage;
  private PImage squareImage;

  private Minim minim;
  private AudioPlayer player;

  public static void main(String[] args) {
    PApplet.main(KnightsProcessing.class);
  }

  @Override
  public void setup() {
    frameRate(6);

    squareSize = (float)height / BOARD_SIZE;
    knightImage = loadImage(KNIGHT_IMAGE_PATH);
    knightImage.resize((int)squareSize, (int)squareSize);
    squareImage = loadImage(SQUARE_IMAGE_PATH);
    squareImage.resize((int)squareSize, (int)squareSize);

    order = new int[BOARD_SIZE][BOARD_SIZE];
    k = new Knight(new Square(0, 0, 0), BOARD_SIZE, BOARD_SIZE);
    sequence = k.solve();
    for (int i = 0; i < sequence.size(); i++) {
      order[sequence.get(i).getRow()][sequence.get(i).getColumn()] = i + 1;
    }

    minim = new Minim(this);
    player = minim.loadFile(MUSIC_PATH);
    player.play();
  }

  @Override
  public void settings() {
    size(900, 900);
  }

  private int c = 0;
  @Override
  public void draw() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        fill(255);
        // rect(i * squareSize, j * squareSize, squareSize, squareSize);
        image(squareImage, i * squareSize, j * squareSize);
        if (order[i][j] <= c) {
          fill(0);
          // text(order[i][j], i * squareSize + 5, j * squareSize + 15);
          image(knightImage, i * squareSize, j * squareSize);
        }
      }
    }
    image(knightImage, sequence.get(c).getRow() * squareSize,
          sequence.get(c).getColumn() * squareSize);

    if (c < sequence.size() - 1) {
      c++;
    }
  }
}
