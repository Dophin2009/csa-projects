package nqueen;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class NQueensProcessing extends PApplet {

  public static final float FPS = 15;

  public static final float W_SIZE = 900;
  public static final int B_SIZE = 6;

  private float sqSize = W_SIZE / B_SIZE;

  private NQueensBoard board;
  private ArrayList<int[][]> tries;
  private PImage queen;

  public static void main(String[] args) {
    PApplet.main(NQueensProcessing.class);
  }

  @Override
  public void setup() {
    frameRate(FPS);

    board = new NQueensBoard(B_SIZE);
    tries = new ArrayList<>();
    queen = loadImage("nqueen/wyatt.jpg");
    queen.resize((int)(sqSize), (int)(sqSize));

    while (B_SIZE != board.countQueens()) {
      if (!board.checkBoard()) { // no available moves
        board.refreshBoard();    // reset
        tries.add(new int[B_SIZE][B_SIZE]);
      }
      board.findAndPlace();

      int[][] b = board.getBoard();
      int[][] t = new int[B_SIZE][B_SIZE];
      for (int i = 0; i < b.length; i++) {
        System.arraycopy(b[i], 0, t[i], 0, b[i].length);
      }
      tries.add(t);
    }
  }

  @Override
  public void settings() {
    size((int)W_SIZE, (int)W_SIZE);
  }

  private int count = 0;

  @Override
  public void draw() {
    drawBoard(tries.get(count));
    count++;
    if (count >= tries.size()) {
      noLoop();
      String phrase = "Don't you guys have phones????".toUpperCase();
      int k = 0;
      for (int i = 0; i < B_SIZE; i++) {
        for (int j = 0; j < B_SIZE; j++) {
          if (board.getBoard()[j][i] != -2) {
            stroke(0);
            fill(0);
            rectMode(RADIUS);
            textSize(40);
            text(phrase.charAt(k), sqSize * (float)(j + 0.5),
                 sqSize * (float)(i + 0.5));
            if (k < phrase.length()) {
              k++;
            }
          }
        }
      }
    }
  }

  private void drawBoard(int[][] b) {
    for (int i = 0; i < B_SIZE; i++) {
      for (int j = 0; j < B_SIZE; j++) {
        if (Math.abs((i - j) % 2) == 1) {
          stroke(125);
          fill(125);
        } else {
          stroke(255);
          fill(255);
        }
        rect(i * sqSize, j * sqSize, sqSize, sqSize);

        if (b[i][j] == NQueensBoard.QUEEN) {
          image(queen, i * sqSize, j * sqSize);
        }
      }
    }
  }
}
