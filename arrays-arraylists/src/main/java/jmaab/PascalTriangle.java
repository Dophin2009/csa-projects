package jmaab;

import java.util.Arrays;

public class PascalTriangle {

  public static void main(String[] args) {
    int[][] triangle = pascalTriangle(11);
    for (int[] row : triangle) {
      System.out.println(Arrays.toString(row));
    }
  }

  /**
   * Method to produce Pascal's Triangle with given
   * number of rows.
   * @return a two-dimensional array of integers
   *         representing Pascal's Triangle with <code>n</code> rows.
   */
  public static int[][] pascalTriangle(int n) {
    int[][] triangle = new int[n][];
    for (int i = 0; i < triangle.length; i++) {
      triangle[i] = new int[i + 1];

      for (int j = 0; j < triangle[i].length; j++) {
        try {
          triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
        } catch (ArrayIndexOutOfBoundsException e) {
          triangle[i][j] = 1;
        }
      }
    }
    return triangle;
  }

  /**
   * Method to find the greatest positive
   * value within the given array.
   * @return the largest positive value;
   *         -1 if all elements are negative.
   */
  private static double positiveMax(double[][] m) {
    double max = m[0][0];
    for (double[] col : m) {
      for (double v : col) {
        if (v > max) {
          max = v;
        }
      }
    }
    return max;
  }

  /**
   * Method to test if given array "covers" the other.
   * One array is considered to "cover" another if each
   * element is greater than the corresponding element
   * for at least half of all the elements in m1.
   * @exception IllegalArgumentException if given arrays
   *            are not of equal dimensions.
   * @return true if above conditions are met;
   *         false if above conditions are not met.
   */
  private static boolean covers(double[][] m1, double[][] m2) {
    if (m1.length != m2.length) {
      throw new IllegalArgumentException("Given arrays are not of equal size.");
    }

    int count = 0;
    int elements = 0;
    for (int i = 0; i < m1.length; i++) {
      if (m1[i].length != m2[i].length) {
        throw new IllegalArgumentException(
            "Given arrays are not of equal size.");
      }
      for (int j = 0; j < m1[i].length; j++) {
        if (m1[i][j] > m2[i][j]) {
          count++;
        }
        elements++;
      }
    }

    return (double)count / elements >= 0.5;
  }
}
