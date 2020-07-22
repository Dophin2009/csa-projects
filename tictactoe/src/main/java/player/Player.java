package player;

import tictactoe.Board;
import tictactoe.Piece;

public class Player {

  public enum PlayerType { HUMAN, COMPUTER }

  protected Board board;
  protected Piece piece;
  protected PlayerType type;

  public Player(Board board, Piece piece, PlayerType type) {
    this.board = board;
    this.piece = piece;
    this.type = type;
  }

  public Board getBoard() { return board; }

  public Piece getPiece() { return piece; }

  public PlayerType getType() { return type; }
}
