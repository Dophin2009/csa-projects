package driver;

import java.util.Scanner;
import player.Player;
import player.TicTacMaster;
import tictactoe.Board;
import tictactoe.GameType;
import tictactoe.Piece;

public class TicTacConsole {

  //    public static void main(String[] args) {
  //        TicTacConsole tac = new TicTacConsole();
  //        tac.setup();
  //        tac.run();
  //    }

  private Board b;
  private Scanner sc;

  private GameType mode;
  private Player p1, p2;

  private void setup() {
    sc = new Scanner(System.in);
    while (true) {
      try {
        System.out.println("Game mode... \n"
                           + "0. vs. Human \n"
                           + "1. vs. Randomized CPU \n"
                           + "2. vs. TicTacMaster");
        switch (sc.nextInt()) {
        case 0:
          mode = GameType.HVH;
          break;
        case 1:
          mode = GameType.HVRC;
          break;
        case 2:
          mode = GameType.HVTTM;
          break;
        default:
          throw new IllegalInputException();
        }
        break;
      } catch (IllegalInputException e) {
        tryAgain();
      }
    }

    while (true) {
      try {
        System.out.println("Board size (>2)...");
        int size = sc.nextInt();
        if (size < 3) {
          throw new IllegalInputException();
        }
        b = new Board(size);
        break;
      } catch (IllegalInputException e) {
        tryAgain();
      }
    }

    while (true) {
      try {
        Piece piece = Piece.EX;
        if (mode != GameType.HVH) {
          System.out.println("Select piece... \n"
                             + "0. EX \n"
                             + "1. OH");
          int input = sc.nextInt();
          if (input != 1 && input != 0) {
            throw new IllegalInputException();
          }
          piece = input == 0 ? Piece.EX : Piece.OH;
        }
        switch (mode) {
        case HVH:
          p1 = new Player(b, Piece.EX, Player.PlayerType.HUMAN);
          p2 = new Player(b, Piece.OH, Player.PlayerType.HUMAN);
          break;
        case HVRC:
        case HVTTM:
        case HVTTME:
          p1 = new Player(b, piece, Player.PlayerType.HUMAN);
          p2 =
              new TicTacMaster(b, (piece == Piece.EX) ? Piece.OH : Piece.EX, 4);
          break;
        }
        break;
      } catch (IllegalInputException e) {
        tryAgain();
      }
    }
  }

  private void run() {
    sc = new Scanner(System.in);
    boolean draw = true;

    Player current = p1.getPiece() == Piece.EX ? p1 : p2;
    while (b.emptySpaces().length > 0) {
      System.out.println(b.toString());
      if (turn(current)) {
        System.out.println(b.toString());
        winMessage(current);
        draw = false;
        break;
      }
      current = (current == p1) ? p2 : p1;
    }

    if (draw) {
      drawMessage();
    }
  }

  private boolean turn(Player p) {
    if (p.getType() == Player.PlayerType.HUMAN) {
      while (true) {
        try {
          System.out.println("Insert piece at...");
          String[] input = sc.nextLine().split(" ");
          b.place(p.getPiece(), Integer.parseInt(input[0]),
                  Integer.parseInt(input[1]));
          break;
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
          tryAgain();
        }
      }
    } else {
      int[] move;
      TicTacMaster c = (TicTacMaster)p;
      if (mode == GameType.HVRC) {
        move = c.randomMove();
      } else if (mode == GameType.HVTTM) {
        move = c.bestMove();
      } else {
        move = c.getMove();
      }
      b.place(p.getPiece(), move[0], move[1]);
    }
    return b.checkBoard(p.getPiece());
  }

  private void tryAgain() {
    System.out.println("Invalid input. Try again. \n");
  }

  private void winMessage(Player p) {
    switch (p.getPiece()) {
    case EX:
      System.out.print("EX");
      break;
    case OH:
      System.out.print("OH");
      break;
    }
    System.out.println(" has won!");
  }

  private void drawMessage() { System.out.println("Draw."); }

  public Board getBoard() { return b; }

  class IllegalInputException extends Exception {

    public IllegalInputException() { super(); }

    public IllegalInputException(String message) { super(message); }

    public IllegalInputException(String message, Throwable cause) {
      super(message, cause);
    }

    public IllegalInputException(Throwable cause) { super(cause); }

    public IllegalInputException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
    }
  }
}
