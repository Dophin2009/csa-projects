package pac.game.entity.still;

public class Consumable implements pac.game.entity.Consumable {

  private double points;

  public Consumable(double points) { this.points = points; }

  public double consume() {
    double pts = this.points;
    this.points = 0;
    return pts;
  }

  public double getPoints() { return points; }

  public void setPoints(double points) { this.points = points; }
}
