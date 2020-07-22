package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

  private Piece[][] board;

  public Board(int size) {
    board = new Piece[size][size];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = Piece.EMPTY;
      }
    }
  }

  public Board(Board other) {
    board = new Piece[other.getBoard().length][];
    for (int i = 0; i < board.length; i++) {
      board[i] = Arrays.copyOf(other.getBoard()[i], other.getBoard()[i].length);
    }
  }

  public void place(Piece piece, int r, int c) {
    if (board[r][c] == Piece.EMPTY) {
      board[r][c] = piece;
    } else {
      throw new IllegalArgumentException("Space already occupied.");
    }
  }

  public boolean checkBoard(Piece piece) {
    Piece[] uDiag = new Piece[board.length];
    Piece[] dDiag = new Piece[board.length];
    for (int i = 0; i < board.length; i++) {
      if (winCondition(board[i], piece)) {
        return true;
      }

      Piece[] col = new Piece[board.length];
      for (int j = 0; j < board.length; j++) {
        col[j] = board[j][i];
        if (i == j) {
          dDiag[j] = board[i][j];
        }
        if (i + j == board.length - 1) {
          uDiag[j] = board[i][j];
        }
      }
      if (winCondition(col, piece)) {
        return true;
      }
    }
    return winCondition(uDiag, piece) || winCondition(dDiag, piece);
  }

  private boolean winCondition(Piece[] list, Piece piece) {
    return Arrays.stream(list).distinct().count() == 1 &&
        Arrays.stream(list).allMatch(i -> i == piece);
  }

  public int[][] emptySpaces() {
    ArrayList<int[]> avail = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == Piece.EMPTY) {
          avail.add(new int[] {i, j});
        }
      }
    }

    int[][] ret = new int[avail.size()][];
    for (int i = 0; i < avail.size(); i++) {
      ret[i] = avail.get(i);
    }
    return ret;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();

    s.append("   ");
    for (int i = 0; i < board.length; i++) {
      s.append(" ").append(i).append(" ");
    }
    s.append("\n");
    for (int i = 0; i < board.length; i++) {
      s.append(i).append(" |");
      for (int j = 0; j < board[i].length; j++) {
        switch (board[i][j]) {
        case EX:
          s.append(" X ");
          break;
        case OH:
          s.append(" O ");
          break;
        default:
          s.append(" _ ");
        }
      }
      s.append("| \n");
    }
    return s.toString();
  }

  public void printBoard() { System.out.println(toString()); }

  public Piece[][] getBoard() { return board; }

  public int getSize() { return board.length; }
}
