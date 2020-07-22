package tour;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Knight {
	private Square currentSquare;
	private Square startingSquare;
	private boolean[][] board;

	/**
	 * Creates a Knight with board of size rows x columns.
	 * Sets the value of board to true in the Square represented
	 * by s. Sets all other board values to false.
	 * Sets currentSquare and startingSquare to s.
	 * @param s the starting Square for this Knight
	 * @param rows the number of rows in this Knight's board
	 * @param cols the number of columns in this Knight's board
	 */
	public Knight(Square s, int rows, int cols) {
		board = new boolean[rows][cols];
		board[s.getRow()][s.getColumn()] = true;
		currentSquare = s;
		startingSquare = s;
	}
	
	/**
	 * Returns this Knight's current Square.
	 * @return this Knight's current Square.
	 */
	public Square getCurrentSquare() {
		return currentSquare;
	}

	/**
	 * Returns this Knight's starting Square.
	 * @return this Knight's starting Square.
	 */
	public Square getStartingSquare() {
		return startingSquare;
	}

	/**
	 * Returns this Knight's board.
	 * @return this Knight's board.
	 */
	public boolean[][] getBoard() {
		return board;
	}

	/**
	 * Returns a list of Squares representing a Knights Tour for this Knight.
	 * @return a list of Squares representing a Knights Tour for this Knight
	 */
	public List<Square> solve() {
		List<Square> sequence = new ArrayList<>();
		
		int pos = 1;
		sequence.add(currentSquare);
		do {
			board[currentSquare.getRow()][currentSquare.getColumn()] = true;
			List<Square> possible = getPossibleSquares();
			if (possible.isEmpty()) {
				sequence.clear();
				sequence.add(startingSquare);
				pos = 1;
				currentSquare = startingSquare;
				clearBoard();
			}
			else {
				Square best = getBestSquare(possible);
				sequence.add(best);
				currentSquare = best;
				pos++;
			}
			System.out.println(this.toString());
		} while (pos < board.length * board[0].length);
		return sequence;
	}

	/**
	 * Determines if starting Square is reachable from current Square.
	 * @return true if starting Square is reachable from current Square, false otherwise
	 */
	public boolean startIsReachableFromCurrent() {
		return getPossibleSquares().contains(startingSquare);
	}
	
	/**
	 * Returns a Square with the smallest score in possible.
	 * If several Squares in possible have the same lowest score,
	 * one of these Squares is selected at random and returned.
	 * @param possible the list of Squares
	 * @return a Square with the smallest score in possible
	 */
	public Square getBestSquare(List<Square> possible) {
		Optional s = possible.stream().min(Comparator.comparingInt(Square::getScore));
		if (s.isPresent()) {
			return (Square) s.get();
		}
		return null;
	}

	/**
	 * Sets all values in this Knight's board to false.
	 */
	public void clearBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = false;
			}
		}
	}

	/**
	 * Returns a list of all Squares that are within one knight move of
	 * this Knight's current Square.
	 * Each Square in the returned list has been given a score representing
	 * the number of unvisited Squares that can be reached (with a knight move) from that Square.
	 * @return a list of all Squares that are within one knight move of
	 * this Knight's current Square
	 */
	public List<Square> getPossibleSquares() {
		List<Square> possible = getPossibleSquares(currentSquare.getRow(), currentSquare.getColumn());
        possible.forEach(s -> s.setScore(getScoreOfSquare(s.getRow(), s.getColumn())));
        return new ArrayList<>(possible);
	}

	public List<Square> getPossibleSquares(int row, int col) {
		List<Square> possible = new ArrayList<>();

		possible.add(new Square(row + 1, col + 2, 0));
		possible.add(new Square(row + 2, col + 1, 0));
		possible.add(new Square(row - 1, col + 2, 0));
		possible.add(new Square(row - 2, col + 1, 0));
		possible.add(new Square(row + 1, col - 2, 0));
		possible.add(new Square(row + 2, col - 1, 0));
		possible.add(new Square(row - 1, col - 2, 0));
		possible.add(new Square(row - 2, col - 1, 0));

		return possible.stream()
                .filter(s -> isValid(s.getRow(), s.getColumn()))
                .filter(s -> !board[s.getRow()][s.getColumn()])
				.collect(Collectors.toList());
	}

	/**
	 * Returns the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col.
	 * @param row the row
	 * @param col the column
	 * @return the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col
	 */
	public int getScoreOfSquare(int row, int col) {
		return getPossibleSquares(row, col).toArray().length;
	}

	/**
	 * Returns true if the square at row r, column c is in this Knight's board; returns false otherwise.
	 * @param r the row
	 * @param c the column
	 * @return true if the square at row r, column c is in this Knight's board; false otherwise
	 */
	public boolean isValid(int r, int c) {
		return r >= 0 && r < board.length && c >= 0 && c < board[r].length;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (boolean[] row : board) {
			for (boolean sq : row) {
				if (sq) {
					s.append(" 1 ");
				} else {
					s.append(" 0 ");
				}
			}
			s.append("\n");
		}
		return s.toString();
	}
}
