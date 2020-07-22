package tictactoe;

import tictactoe.tictactoe.Board;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.tictactoe.Piece;

public class BoardTest {

    Board b = new Board(3);

    @Before
    public void setup() {
        b.place(Piece.EX, 0, 0);
//        b.place(Board.EX, 1, 0);
//        b.place(Board.EX, 2, 0);
        System.out.println(b.toString());
    }

    @Test
    public void checkTest() {
        Assert.assertFalse(b.checkBoard(Piece.EX));
    }

}
