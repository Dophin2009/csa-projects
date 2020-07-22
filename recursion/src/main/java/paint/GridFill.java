package paint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import processing.core.PApplet;

public class GridFill extends PApplet {
  private int[][] grid;
  private int size = 10, sqSideLength = 50;
  private final int BLACK = 1;
  private final int WHITE = 0;
  private final int RED = 2;
  private String file = "board.txt";

  public static void main(String[] args) { PApplet.main(GridFill.class); }

  public void settings() { size(size * sqSideLength, size * sqSideLength); }

  public void setup() {
    noStroke();
    grid = new int[size][size];
    try {
      initGrid(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void paintRed(int row, int col) {
    if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length &&
        grid[row][col] == WHITE) {
      grid[row][col] = RED;
      paintRed(row - 1, col);
      paintRed(row + 1, col);
      paintRed(row, col - 1);
      paintRed(row, col + 1);
    }
  }

  public void draw() { display(); }

  public void mousePressed() {
    int row = mouseX / sqSideLength;
    int col = mouseY / sqSideLength;
    paintRed(row, col);
  }

  public void initGrid(String file) throws FileNotFoundException {
    Scanner sc = new Scanner(new File(file));
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[j][i] = sc.nextInt();
      }
    }
  }

  public void display() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        switch (grid[i][j]) {
        case BLACK:
          fill(0);
          break;
        case WHITE:
          fill(255);
          break;
        case RED:
          fill(252, 0, 0);
          break;
        }
        rect(i * sqSideLength, j * sqSideLength, sqSideLength, sqSideLength);
      }
    }
  }
}
