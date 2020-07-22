import java.util.ArrayList;
import java.util.List;

public class Equation {

  private Function f, g;

  public Equation(Function f, Function g) {
    this.f = f;
    this.g = g;
  }

  public List<Integer> solve() {
    List<Integer> solutions = new ArrayList<Integer>();
    for (int i = -100; i <= 100; i++) {
      if (f.getValue(i) == g.getValue(i)) {
        solutions.add(i);
      }
    }

    return solutions;
  }
}
