public class Main {

  public static void main(String[] args) {

    Function f = new LinearFunction(2, 3);
    Function g = new LinearFunction(1, -2);

    Equation eq = new Equation(f, g);
    System.out.println(eq.solve());

    Function h = new QuadraticFunction(1, 0, 6);
    Function j = new LinearFunction(5, 0);

    Equation eq2 = new Equation(h, j);
    System.out.println(eq2.solve());
  }
}
