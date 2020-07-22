package nqueen;

import java.util.ArrayList;
import java.util.List;

public class NQueensBoard {
  private int[][] board;
  public static final int QUEEN = -2;
  public static final int ELIMINATED = -1;

  /**
   * constructor
   * initializes board to be of size n-by-n and containing all zeroes
   */
  public NQueensBoard(int n) { board = new int[n][n]; }

  /**
   * sets value of all cells in board to 0
   */
  public void refreshBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = 0;
      }
    }
  }

  /**
   * prints the contents of each square in board in tabular format
   * if square contains QUEEN, print 'Q'
   * if square contains ELIMINATED, print '~'
   * otherwise, print '-'
   */
  public void printGrid() {
    for (int i = 0; i < board.length; i++) {
      System.out.print("[ ");
      for (int j = 0; j < board[i].length; j++) {
        switch (board[i][j]) {
        case QUEEN:
          System.out.print("Q");
          break;
        case ELIMINATED:
          System.out.print("~");
          break;
        default:
          System.out.print("-");
          break;
        }
        System.out.print(" ");
      }
      System.out.println("]");
    }
    System.out.println(" ");
  }

  public void printValues() {
    for (int i = 0; i < board.length; i++) {
      System.out.print("[ ");
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println("]");
    }
    System.out.println(" ");
  }

  /**
   * calls scoreSquare method for each square on board
   * places result from each call to scoreSquare in that square in board
   */
  public void scoreSpaces() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = scoreSquare(i, j);
      }
    }
  }

  /**
   * returns true if there is an available square on board
   * returns false if there are no available squares
   */
  public boolean checkBoard() {
    for (int[] row : board) {
      for (int s : row) {
        if (s >= 0) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * changes the value of board[row][col] to QUEEN
   * changes the value of all squares that are eliminated by this queen to
   * ELIMINATED
   */
  public void placeQueen(int row, int col) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (row == i || col == j || row - col == i - j || row + col == i + j) {
          board[i][j] = ELIMINATED;
        }
      }
    }
    board[row][col] = QUEEN;
  }

  /**
   * returns ELIMINATED if square at row, col contains ELIMINATED
   * returns QUEEN if square at row, col contains QUEEN
   * otherwise, counts the number of squares that would become unavailable
   * if the square at row, col were to receive a queen (including the
   * square at row,col); this count is returned
   */
  public int scoreSquare(int row, int col) {
    if (board[row][col] == QUEEN) {
      return QUEEN;
    }
    if (board[row][col] == ELIMINATED) {
      return ELIMINATED;
    }

    int count = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if ((row == i || col == j ||
             (row - col == i - j || row + col == i + j) && board[i][j] >= 0)) {
          count++;
        }
      }
    }
    return count - 2;
  }

  /**
   * calculates the smallest non-negative score on the board
   * determines the number of squares that contain this score
   * randomly selects one of these squares
   * calls placeQueen method to place a queen in this square
   */
  public void findAndPlace() {
    scoreSpaces();
    int min = board.length * board[0].length;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        int s = board[i][j];
        if (s < min && s > 0) {
          min = s;
        }
      }
    }

    List<Position> available = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == min) {
          available.add(new Position(i, j));
        }
      }
    }

    Position pos = available.get((int)(available.size() * Math.random()));
    placeQueen(pos.getR(), pos.getC());
  }

  /**
   * counts the number of queens on the board
   * @return integer representing the number of queens on the board
   */
  public int countQueens() {
    int count = 0;
    for (int[] row : board) {
      for (int s : row) {
        if (s == QUEEN) {
          count++;
        }
      }
    }
    return count;
  }

  public int[][] getBoard() { return board; }

  public class Position {
    private int r, c;

    public Position(int r, int c) {
      this.r = r;
      this.c = c;
    }

    public int getR() { return r; }

    public void setR(int r) { this.r = r; }

    public int getC() { return c; }

    public void setC(int c) { this.c = c; }
  }
}
