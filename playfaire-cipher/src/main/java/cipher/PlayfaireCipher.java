package cipher;

public class PlayfaireCipher {

  private String key;
  private char[][] grid;

  private static String ALL_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";

  public static void main(String[] args) {
    PlayfaireCipher c =
        new PlayfaireCipher("The Lost numbers are: 4, 8, 15, 16, and 23");
    for (int i = 0; i < c.grid.length; i++) {
      for (int j = 0; j < c.grid[i].length; j++) {
        System.out.print(c.grid[i][j] + ", ");
      }
      System.out.println("");
    }
    String encrypted = c.encrypt("Hello World");
    System.out.println(encrypted);
    System.out.println(c.decode(encrypted));
  }

  public PlayfaireCipher(String key) {
    this.key = removeDuplicates(cleanString(key));
    this.grid = constructGrid(this.key);
  }

  public String decode(String message) {
    StringBuilder res = new StringBuilder();

    for (int i = 0; i < message.length(); i = i + 2) {
      int[] co1 = findLetterInGrid(message.charAt(i));
      int r1 = co1[0], c1 = co1[1];

      int[] co2 = findLetterInGrid(message.charAt(i + 1));
      int r2 = co2[0], c2 = co2[1];

      int relation = sameRowColumn(co1, co2);
      if (relation == -1) {
        res.append(grid[r1][c1 != 0 ? c1 - 1 : grid[r1].length - 1]);
        res.append(grid[r2][c2 != 0 ? c2 - 1 : grid[r2].length - 1]);
      } else if (relation == 1) {
        res.append(grid[r1 != 0 ? r1 - 1 : grid.length - 1][c1]);
        res.append(grid[r2 != 0 ? r2 - 1 : grid.length - 1][c2]);
      } else {
        res.append(grid[r1][c2]);
        res.append(grid[r2][c1]);
      }
    }

    return res.toString();
  }

  public String encrypt(String message) {
    message = cleanString(message);
    StringBuilder res = new StringBuilder();

    StringBuilder pairedBuilder = new StringBuilder();
    for (int i = 0; i < message.length() - 1; i++) {
      pairedBuilder.append(message.charAt(i));
      if (message.charAt(i) == message.charAt(i + 1)) {
        pairedBuilder.append("x");
      }
    }
    pairedBuilder.append(message.charAt(message.length() - 1));
    if (pairedBuilder.length() % 2 == 1) {
      pairedBuilder.append("x");
    }
    String paired = pairedBuilder.toString();

    for (int i = 0; i < paired.length(); i = i + 2) {
      int[] co1 = findLetterInGrid(paired.charAt(i));
      int r1 = co1[0], c1 = co1[1];

      int[] co2 = findLetterInGrid(paired.charAt(i + 1));
      int r2 = co2[0], c2 = co2[1];

      int relation = sameRowColumn(co1, co2);
      if (relation == -1) {
        res.append(grid[r1][c1 != grid[r1].length ? c1 + 1 : 0]);
        res.append(grid[r2][c2 != grid[r1].length ? c2 + 1 : 0]);
      } else if (relation == 1) {
        res.append(grid[r1 != grid.length ? r1 + 1 : 0][c1]);
        res.append(grid[r2 != grid.length ? r2 + 1 : 0][c2]);
      } else {
        res.append(grid[r1][c2]);
        res.append(grid[r2][c1]);
      }
    }

    return res.toString();
  }

  private int sameRowColumn(int[] co1, int[] co2) {
    // same row
    if (co1[0] == co2[0]) {
      return -1;
    }
    // same column
    if (co1[1] == co2[1]) {
      return 1;
    }
    return 0;
  }

  private int[] findLetterInGrid(char c) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == c) {
          return new int[] {i, j};
        }
      }
    }
    return new int[] {-1, -1};
  }

  private char[][] constructGrid(String s) {
    String left = ALL_CHARS;

    char[][] res = new char[6][6];
    int k = 0;
    int l = 0;
    for (int i = 0; i < res.length; i++) {
      for (int j = 0; j < res[i].length; j++) {
        if (k < s.length()) {
          int index = left.indexOf(s.charAt(k));
          res[i][j] = left.charAt(index);
          left = left.substring(0, index) + left.substring(index + 1);
          k++;
        } else {
          res[i][j] = left.charAt(l);
          l++;
        }
      }
    }

    return res;
  }

  private String removeDuplicates(String s) {
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (res.toString().indexOf(s.charAt(i)) == -1) {
        res.append(s.charAt(i));
      }
    }
    return res.toString();
  }

  private String cleanString(String s) {
    StringBuilder cleaned = new StringBuilder();

    s = s.toLowerCase();
    for (int i = 0; i < s.length(); i++) {
      if ("1234567890qwertyuiopasdfghjklzxcvbnm".contains(
              s.substring(i, i + 1))) {
        cleaned.append(s.charAt(i));
      }
    }

    return cleaned.toString();
  }
}
