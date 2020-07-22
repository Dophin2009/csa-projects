package grade;

import java.util.Arrays;
import java.util.Collections;
import processing.core.PApplet;

public class Display extends PApplet {

  public static final float HISTO_ORIGIN_X = 100, HISTO_ORIGIN_Y = 960;
  public static final float HISTO_SIZE_X = 800, HISTO_SIZE_Y = 900;
  public static final float PIE_CENTER_X = 400, PIE_CENTER_Y = 400;
  public static final float PIE_DIAMETER = 600;

  public static final Color[] COLORS = {
      new Color(255, 102, 102), new Color(255, 179, 102),
      new Color(0, 143, 255),   new Color(255, 102, 163),
      new Color(255, 255, 128), new Color(133, 224, 133)};

  private float xSize, ySize;

  private Dataset data = new Dataset("scores.txt");

  private Bar[] bars;
  private Slice[] slices;

  public static void main(String[] args) { PApplet.main(Display.class); }

  @Override
  public void settings() {
    fullScreen();
  }

  @Override
  public void setup() {
    background(0, 0, 0);
    setData(data);
  }

  /**
   * Method to initialize values.
   * @param dataset - the dataset being displayed.
   */
  private void setData(Dataset dataset) {
    Section[] sections = dataset.histogram(5, 1, 100);
    bars = new Bar[sections.length];
    slices = new Slice[sections.length];

    float barGap = (float)700 / sections.length;
    xSize = HISTO_SIZE_X / sections.length;
    ySize = HISTO_SIZE_Y / 23;

    float startAngle = 0;
    for (int i = 0; i < sections.length; i++) {
      float gray = map(i, 0, sections.length, 0, 255);
      if (i % COLORS.length == COLORS.length - 1) {
        Collections.shuffle(Arrays.asList(COLORS));
      }

      float x = HISTO_ORIGIN_X + ((xSize + barGap) * i);
      float y = HISTO_ORIGIN_Y - (sections[i].getValue() * ySize);
      bars[i] = new Bar(sections[i], x, x + xSize, y, HISTO_ORIGIN_Y,
                        new Color((int)gray), COLORS[i % COLORS.length]);

      float endAngle =
          startAngle + radians(360 * ((float)sections[i].getValue() /
                                      dataset.getValues().length));
      slices[i] = new Slice(sections[i], startAngle, endAngle,
                            new Color((int)gray), COLORS[i % COLORS.length]);
      startAngle = endAngle;
    }
  }

  /**
   * Looping method to display everything.
   */
  @Override
  public void draw() {
    for (int i = 0; i < bars.length; i++) {
      bars[i].setMouseInside(false);
      slices[i].setMouseInside(false);
      if (bars[i].checkMouseInside(mouseX, mouseY) ||
          slices[i].checkMouseInside(mouseX, mouseY)) {
        bars[i].setMouseInside(true);
        slices[i].setMouseInside(true);
      }
    }

    textSize(12);
    drawHistogram();
    drawPie();

    boolean specificStats = false;
    for (Bar b : bars) {
      if (b.isMouseInside()) {
        displayStats(b.getSection().getDataset());
        specificStats = true;
      }
    }
    if (!specificStats) {
      displayStats(data);
    }
  }

  /**
   * Method to draw the histogram.
   */
  private void drawHistogram() {
    background(0, 0, 0);

    // Plot graph
    for (Bar b : bars) {
      Section data = b.getSection();

      // Plot data
      Color c =
          (b.isMouseInside() ? b.getHighlightColor() : b.getDefaultColor());
      stroke(c.getR(), c.getG(), c.getB());
      fill(c.getR(), c.getG(), c.getB());

      rectMode(CORNER);
      rect(b.getHistoLowXBound(), b.getHistoLowYBound(), xSize,
           ySize * b.getSection().getValue(), 4);

      // Plot x-axis labels
      String label = String.format("%.0f", data.getLowerBound()) + " - " +
                     String.format("%.0f", data.getUpperBound());
      fill(219);
      rectMode(RADIUS);
      textAlign(CENTER);
      text(label, b.getHistoLowXBound() + xSize / 2, HISTO_ORIGIN_Y + 30,
           xSize + 10, 15);

      // Plot data labels
      String dataLabel = data.getValue() + "";
      text(dataLabel, b.getHistoLowXBound() + xSize / 2,
           HISTO_ORIGIN_Y - (data.getValue() * ySize) - 15, xSize + 10, 15);
    }
  }

  /**
   * Method to draw the pie graph.
   */
  private void drawPie() {
    for (Slice s : slices) {
      Color c =
          (s.isMouseInside() ? s.getHighlightColor() : s.getDefaultColor());
      stroke(c.getR(), c.getG(), c.getB());
      fill(c.getR(), c.getG(), c.getB());

      float enlargement = s.isMouseInside() ? 50 : 0;
      arc(PIE_CENTER_X, PIE_CENTER_Y, PIE_DIAMETER + enlargement,
          PIE_DIAMETER + enlargement, s.getPieStartAngle(), s.getPieEndAngle());
    }
  }

  /**
   * Method to display the stats of the given dataset.
   * @param d - the dataset whose stats should be displayed.
   */
  private void displayStats(Dataset d) {
    float x = width - 100;
    float y = 100;
    int textSize = 16;

    String[] stats = {
        "values: " + d.getValues().length,
        "min: " + String.format("%.2f", d.min()),
        "max: " + String.format("%.2f", d.max()),
        "range: " + String.format("%.2f", d.range()),
        "mean: " + String.format("%.2f", d.mean()),
        "median: " + String.format("%.2f", d.median()),
        "mode(s): " +
            Arrays.toString(d.mode()).replace("[", "").replace("]", ""),
        "variance: " + String.format("%.2f", d.variance()),
        "standard deviation: " + String.format("%.2f", d.standardDeviation())};

    fill(219);
    textAlign(RIGHT);
    textSize(textSize);
    for (int i = 0; i < stats.length; i++) {
      text(stats[i], x, y + i * (textSize + 5));
    }
  }
}
