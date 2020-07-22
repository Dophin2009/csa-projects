package nqueen;

public class NQueensMain {

  public static void main(String[] args) {
    int n = 4;
    NQueensBoard board = new NQueensBoard(n);

    while (n != board.countQueens()) {
      board.printValues();
      if (!board.checkBoard()) { // no available moves
        board.refreshBoard();    // reset
      }
      board.findAndPlace();
    }
    board.printGrid();
  }
}
