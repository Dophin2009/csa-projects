import tour.Knight;
import tour.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//This class should be used to test the methods in square and main. The code in here can be modified and changed
//as you see fit. I have given you the code to test the entirety of your program but you may find it useful to 
//write your own test cases (or use JUnit).
public class Test {

	public static void main(String[] args) {
		Random randy = new Random();
		int startR = randy.nextInt(8);
		int startC = randy.nextInt(8);
		//Creates a tour.Knight at a random starting location
		Knight lancelot = new Knight(new Square(startR,startC,0),8,8);

		//call solve() method
		List<Square> answer = lancelot.solve();

		//prints out the squares in the answer
		for (Square sq: answer) {
			System.out.println(sq);
		}
		

		//This section prints out an 8 x 8 display of the position number 1 through 64 
		//in the order that the knight visits them
		int[][] result = new int[8][8];
		int pos = 0;
		//this loop puts the position number into the 2d array
		for (int k = 0; k < answer.size(); k++) { 
			int r = answer.get(k).getRow();
			int c = answer.get(k).getColumn();
			result[r][c] = pos+1;
			pos++;
		}
		
		//this loop displays the 2d array result
		for (int r = 0; r < result.length; r++) { 
			for (int c = 0; c < result[r].length; c++) {
				System.out.print(result[r][c] + "\t");
			}
			System.out.println();
		}

	}

}
