package grade;

public class Slice {

  private Section s;

  private float pieStartAngle, pieEndAngle;
  private Color defaultColor, highlightColor;
  private boolean mouseInside;

  /**
   * Constructor.
   * @param s - the section of data represented.
   * @param pieStartAngle - the lower bound of the sector represented.
   * @param pieEndAngle - the upper bound of the sector represented.
   * @param defaultColor - the default color of the rectangle represented.
   * @param highlightColor - the color of the rectangle represented when the
   *     mouse pointer is not within the bounds.
   */
  public Slice(Section s, float pieStartAngle, float pieEndAngle,
               Color defaultColor, Color highlightColor) {
    this.s = s;
    this.pieStartAngle = pieStartAngle;
    this.pieEndAngle = pieEndAngle;
    this.defaultColor = defaultColor;
    this.highlightColor = highlightColor;
  }

  /**
   * Method to return a boolean value representing whether or not the mouse
   * pointer is within the bounds.
   * @param mouseX - the x position of the mouse pointer.
   * @param mouseY - the y position of the mouse pointer.
   * @return a boolean value representing whether or not the mouse pointer is
   *     within the bounds.
   */
  public boolean checkMouseInside(float mouseX, float mouseY) {
    float x = mouseX - Display.PIE_CENTER_X;
    float y = mouseY - Display.PIE_CENTER_Y;
    float r = Display.PIE_DIAMETER / 2;
    double angle = Math.atan2(y, x);

    if (x * x + y * y < r * r) {
      angle = (angle > 0 ? angle : 2 * Math.PI + angle);

      mouseInside = angle >= pieStartAngle && angle <= pieEndAngle;
    } else {
      mouseInside = false;
    }
    return mouseInside;
  }

  /**
   * Method to return the section of data represented.
   * @return the <code>Section</code> of data represented.
   */
  public Section getSection() { return s; }

  /**
   * Method to return the lower bound of the sector represented.
   * @return the lower bound of the sector represented.
   */
  public float getPieStartAngle() { return pieStartAngle; }

  /**
   * Method to return the upper bound of the sector represented.
   * @return the upper bound of the sector represented.
   */
  public float getPieEndAngle() { return pieEndAngle; }

  /**
   * Method to return the default color of the rectangle represented.
   * @return the default color of the rectangle represented.
   */
  public Color getDefaultColor() { return defaultColor; }

  /**
   * Method to return the color of the rectangle represented when the mouse
   * pointer is not within the bounds.
   * @return the color of the rectangle represented when the mouse pointer is
   *     not within the bounds.
   */
  public Color getHighlightColor() { return highlightColor; }

  /**
   * Method to return a boolean value representing whether or not the mouse
   * pointer is within the bounds.
   * @return a boolean value representing whether or not the mouse pointer is
   *     within the bounds.
   */
  public boolean isMouseInside() { return mouseInside; }

  /**
   * Method to set whether or not the mouse pointer is within the bounds.
   * @param mouseInside - a boolean value representing whether or not the mouse
   *     pointer is within the bounds.
   */
  public void setMouseInside(boolean mouseInside) {
    this.mouseInside = mouseInside;
  }
}
