package player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import tictactoe.Board;
import tictactoe.Piece;

public class TicTacMaster extends Player {

  private Map<String, Integer> states;
  private int difficulty;

  public TicTacMaster(Board board, Piece piece, int difficulty) {
    super(board, piece, PlayerType.COMPUTER);
    states = new HashMap<>();
    this.difficulty = difficulty;
  }

  public int[] getMove() {
    int choice = (int)(Math.random() * 10);
    if (choice > difficulty - 1) {
      return randomMove();
    } else {
      return bestMove();
    }
  }

  public int[] randomMove() {
    while (true) {
      int[] move = new int[] {(int)(Math.random() * board.getBoard().length),
                              (int)(Math.random() * board.getBoard().length)};
      if (Arrays.stream(board.emptySpaces())
              .anyMatch(x -> x[0] == move[0] && x[1] == move[1])) {
        return move;
      }
    }
  }

  public int[] bestMove() {
    int[] best = new int[] {-1, -1};
    int bestValue = -11;

    for (int[] spaces : board.emptySpaces()) {
      Board testBoard = new Board(board);
      testBoard.place(piece, spaces[0], spaces[1]);
      int move = minimax(testBoard, (piece == Piece.EX) ? Piece.OH : Piece.EX,
                         -1000, 1000);
      if (move > bestValue) {
        best = spaces;
        bestValue = move;
      }
    }
    return best;
  }

  public int[] worstMove() {
    int[] worst = new int[] {-1, -1};
    int worstValue = 11;

    for (int[] spaces : board.emptySpaces()) {
      Board testBoard = new Board(board);
      testBoard.place(piece, spaces[0], spaces[1]);
      int move = minimax(testBoard, (piece == Piece.EX) ? Piece.OH : Piece.EX,
                         -1000, 1000);
      if (move < worstValue) {
        worst = spaces;
        worstValue = move;
      }
    }
    return worst;
  }

  private int minimax(Board levelBoard, Piece currentPiece, int alpha,
                      int beta) {
    int score = scoreBoard(levelBoard);
    if (Math.abs(score) == 10) {
      return score;
    }

    int[][] avail = levelBoard.emptySpaces();
    if (avail.length == 0) {
      return 0;
    }

    if (currentPiece == piece) {
      int bestScore = -10;
      for (int[] spaces :
           avail) { // iterate through available spaces and run minimax
        Board newBoard = new Board(levelBoard);
        newBoard.place(currentPiece, spaces[0], spaces[1]);

        Integer value = states.get(newBoard.toString());
        if (value == null) {
          value = minimax(newBoard,
                          (currentPiece == Piece.EX) ? Piece.OH : Piece.EX,
                          alpha, beta);
          states.put(newBoard.toString(), value);
        }
        bestScore = Math.max(bestScore, value);
        alpha = Math.max(alpha, bestScore);

        if (beta <= alpha) {
          break;
        }
      }
      return bestScore;
    }

    else {
      int bestScore = 10;
      for (int[] spaces :
           avail) { // iterate through available spaces and run minimax
        Board newBoard = new Board(levelBoard);
        newBoard.place(currentPiece, spaces[0], spaces[1]);

        Integer value = states.get(newBoard.toString());
        if (value == null) {
          value = minimax(newBoard,
                          (currentPiece == Piece.EX) ? Piece.OH : Piece.EX,
                          alpha, beta);
          states.put(newBoard.toString(), value);
        }
        bestScore = Math.min(bestScore, value);
        beta = Math.min(beta, bestScore);

        if (beta <= alpha) {
          break;
        }
      }
      return bestScore;
    }
  }

  private int scoreBoard(Board b) {
    if (b.checkBoard(piece)) { // return score of 10 if won
      return 10;
    } else if (b.checkBoard(
                   (piece == Piece.EX)
                       ? Piece.OH
                       : Piece.EX)) { // return score of -10 if opponent won
      return -10;
    } else {
      return 0;
    }
  }
}
