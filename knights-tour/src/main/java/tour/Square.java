package tour;

public class Square {
	private boolean visited;
	private int row;
	private int column;
	private int score;
	
	/**
	 * Constructs a tour.Square with given row, column, and score.
	 * @param row the row
	 * @param column the column
	 * @param sc the score
	 */
	public Square(int row, int column, int sc) {
		this.row = row;
		this.column = column;
		this.score = sc;
	}

	/**
	 * Returns the row of this tour.Square
	 * @return the row of this tour.Square
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column of this tour.Square
	 * @return the column of this tour.Square
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Returns the score of this tour.Square
	 * @return the score of this tour.Square
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the score of this tour.Square to s
	 * @param s the score of this tour.Square
	 */
	public void setScore(int s) {
		score = s;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return row + ", " + column;
	}	
	
	/** 
	 * Returns true if this tour.Square and x have the same row and the same column.
	 * Otherwise, returns false.
	 */
	public boolean equals(Object x) {
		if (x.getClass() != Square.class) {
			throw new IllegalArgumentException();
		}
		Square s = (Square) x;
		return row == s.getRow() && column == s.getColumn();
	}
}
