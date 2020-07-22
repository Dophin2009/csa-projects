package fib;

public class Fibonacci {

  private int[] terms = new int[100];

  public static void main(String[] args) {
    Fibonacci fib = new Fibonacci();
    for (int n : fib.firstTerms(20)) {
      System.out.println(n);
    }
  }

  public int solve(int n) {
    if (n > terms.length) {
      int[] temp = new int[n];
      System.arraycopy(terms, 0, temp, 0, terms.length);
      terms = temp;
    }
    if (n == 0) {
      terms[0] = 0;
      return 0;
    }
    if (n == 1) {
      terms[1] = 1;
      return 1;
    }
    terms[n] = solve(n - 1) + terms[n - 2];
    return terms[n];
  }

  public int[] firstTerms(int n) {
    int[] first = new int[n];
    for (int i = 1; i <= n; i++) {
      first[i - 1] = solve(i);
    }
    return terms;
  }
}
