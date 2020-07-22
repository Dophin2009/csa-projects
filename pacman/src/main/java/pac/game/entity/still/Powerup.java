package pac.game.entity.still;

public class Powerup extends Consumable {

  private Runnable effect;

  public Powerup(double points, Runnable effect) {
    super(points);
    this.effect = effect;
  }

  @Override
  public double consume() {
    effect.run();
    return super.consume();
  }

  public Runnable getEffect() { return effect; }

  public void setEffect(Runnable effect) { this.effect = effect; }
}
