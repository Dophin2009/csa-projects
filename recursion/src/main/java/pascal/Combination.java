package pascal;

public class Combination {

  private long[][] pascal;

  public Combination(int n) {
    pascal = new long[n][];
    for (int i = 0; i < pascal.length; i++) {
      pascal[i] = new long[i + 1];
      for (int j = 0; j < pascal[i].length; j++) {
        pascal[i][j] = combination(i, j);
      }
    }
  }

  public long combination(int r, int c) {
    if (r == 0 || c == 0 || r == c) {
      pascal[r][c] = 1;
      return 1;
    }

    if (pascal[r][c] == 0) {
      pascal[r][c] = combination(r - 1, c - 1) + combination(r - 1, c);
    }
    return pascal[r][c];
  }

  public long[][] getPascal() { return pascal; }
}
