package pac.game.entity;

import util.Util;

public abstract class Entity {

  private double row, col;

  public Entity(double row, double col) {
    this.row = row;
    this.col = col;
  }

  public abstract void update();

  public double[] coordinates() { return new double[] {getRow(), getCol()}; }

  public void setLocation(double row, double col) {
    this.row = row;
    this.col = col;
  }

  public void incrementRow(double val) { row = Util.round(row + val, 2); }

  public void incrementCol(double val) { col = Util.round(col + val, 2); }

  public double getRow() { return row; }

  public void setRow(double row) { this.row = row; }

  public double getCol() { return col; }

  public void setCol(double col) { this.col = col; }
}
