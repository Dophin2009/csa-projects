package tictactoe;

import tictactoe.player.TicTacMaster;
import tictactoe.tictactoe.Board;
import tictactoe.tictactoe.Piece;

public class AITest {

    public static void main(String[] args) {
        Board b = new Board(3);
        TicTacMaster tic = new TicTacMaster(b, Piece.EX);
        TicTacMaster tac = new TicTacMaster(b, Piece.OH);

        TicTacMaster current = tic;
        while (b.emptySpaces().length > 0) {
            int[] move = current.bestMove();
            b.place(current.getPiece(), move[0], move[1]);
            System.out.println(b.toString());
            if (b.checkBoard(current.getPiece())) {
                break;
            }
            current = (current.equals(tic)) ? tac : tic;
        }

    }

}
