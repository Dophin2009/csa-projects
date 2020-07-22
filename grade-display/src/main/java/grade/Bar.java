package grade;

public class Bar {

  private Section s;

  private float histoLowXBound, histoHighXBound, histoLowYBound,
      histoHighYBound;
  private Color defaultColor, highlightColor;
  private boolean mouseInside;

  /**
   * Constructor.
   * @param s - the section of data represented.
   * @param histoLowXBound - the lower x bound of the rectangle represented.
   * @param histoHighXBound - the upper x bound of the rectangle represented.
   * @param histoLowYBound - the lower y bound of the rectangle represented.
   * @param histoHighYBound - the upper y bound of the rectangle represented.
   * @param defaultColor - the default color of the rectangle represented.
   * @param highlightColor - the color of the rectangle represented when the
   *     mouse pointer is not within the bounds.
   */
  public Bar(Section s, float histoLowXBound, float histoHighXBound,
             float histoLowYBound, float histoHighYBound, Color defaultColor,
             Color highlightColor) {
    this.s = s;
    this.histoLowXBound = histoLowXBound;
    this.histoHighXBound = histoHighXBound;
    this.histoLowYBound = histoLowYBound;
    this.histoHighYBound = histoHighYBound;
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
    return mouseX > histoLowXBound && mouseX < histoHighXBound &&
        mouseY > histoLowYBound && mouseY < histoHighYBound;
  }

  /**
   * Method to return the section of data represented.
   * @return the <code>Section</code> of data represented.
   */
  public Section getSection() { return s; }

  /**
   * Method to return the lower x bound of the rectangle represented.
   * @return the lower x bound of the rectangle represented.
   */
  public float getHistoLowXBound() { return histoLowXBound; }

  /**
   * Method to return the upper x bound of the rectangle represented.
   * @return the upper x bound of the rectangle represented.
   */
  public float getHistoHighXBound() { return histoHighXBound; }

  /**
   * Method to return the lower y bound of the rectangle represented.
   * @return the lower y bound of the rectangle represented.
   */
  public float getHistoLowYBound() { return histoLowYBound; }

  /**
   * Method to return the upper y bound of the rectangle represented.
   * @return the upper y bound of the rectangle represented.
   */
  public float getHistoHighYBound() { return histoHighYBound; }

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
