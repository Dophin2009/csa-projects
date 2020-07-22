import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import processing.core.PApplet;

public class Main extends PApplet {

  private final static int FPS = 1200;

  // Lists of colors to iterate through
  public static List<Color> darkColors = new ArrayList<Color>(),
                            lightColors = new ArrayList<Color>();
  // Select colors to serve as background and possibly fill
  private final static Color BLACK = new Color(0, 0, 0, true),
                             NAVY = new Color(20, 27, 65, true),
                             CHOCOLATE = new Color(84, 73, 75, true),
                             DARK_GREEN = new Color(0, 40, 40, true);
  // Select colors to serve as stroke and possibly fill
  private final static Color WHITE = new Color(255, 255, 255, false),
                             NEON_PINK = new Color(249, 0, 80, false),
                             MELLOW_YELLOW = new Color(244, 227, 178, false),
                             BLUE = new Color(47, 66, 170, false),
                             RASPBERRY = new Color(178, 57, 79, false),
                             SALMON = new Color(247, 165, 165, false),
                             LIGHT_GREEN = new Color(0, 163, 125, false);

  private final static ColorScheme DEFAULT_SCHEME =
      new ColorScheme(BLACK, WHITE, BLACK);
  private int[] background, stroke, fill;

  // Constants dictating the rows of movement and starting positions
  private final static float START_X = -50, START_Y = 80;
  private final static float ROW_GAP = Square.SQUARE_SIZE + 130;
  private final static float WALL_MARGIN = 100;

  // Constants dictating the counter to change color
  private final static int CC_INTERVAL_START = 3, CC_INTERVAL_END = 5;

  private List<Square> squares = new ArrayList<Square>();

  public static void main(String[] args) { PApplet.main("Main"); }

  public void settings() {
    fullScreen();
    // size(1280, 720);
  }

  public void setup() {
    noCursor();

    // Preliminary shuffle of color lists
    Collections.shuffle(darkColors);
    Collections.shuffle(lightColors);

    strokeWeight(3);
    setColorScheme(DEFAULT_SCHEME);
    frameRate(FPS);

    squares.add(new Square(START_X, START_Y, 0, 5, true));
  }

  private int changeSchemeTimer = 500;
  private int darkColorsIterator = 0, lightColorsIterator = 0;
  public void draw() {
    background(getCurrentBackground()[0], getCurrentBackground()[1],
               getCurrentBackground()[2]);

    // If last square's x coordinate is at START_X, create a new square behind
    // it
    if (squares.get(squares.size() - 1).getX() == START_X &&
        squares.size() <= 50) {
      squares.add(new Square(START_X - Square.SQUARE_SIZE - Square.SQUARE_GAP,
                             START_Y, 5, 5, false));
    }

    // Iterate through the list of squares
    for (Square s : squares) {
      s.setX(s.getX() + s.getxDirection());
      s.setY(s.getY() + s.getyDirection());
      s.draw(this);

      // When square reaches the margin, set it to move down
      if (s.getxDirection() != 0 &&
          (s.getX() == WALL_MARGIN && s.getxDirection() == -1 ||
           s.getX() == width - WALL_MARGIN && s.getxDirection() == 1)) {
        s.setxDirection(0);
        s.setyDirection(1);
        s.setCurrentRow(s.getCurrentRow() + 1);
      }

      // When square is moving down and reaches the next row, set it to move
      // sideways again
      float nextRow = START_Y + s.getCurrentRow() * ROW_GAP;
      if (s.getyDirection() != 0 && s.getY() == nextRow) {
        s.setyDirection(0);
        // Determine the direction the square will move
        if (s.getX() == WALL_MARGIN) {
          s.setxDirection(1);
        } else if (s.getX() == width - WALL_MARGIN) {
          s.setxDirection(-1);
        }
      }

      // When the square moves out of the window, place it to start again
      if (s.getY() > height + Square.SQUARE_SIZE) {
        // Determine the direction the square will move; opposite from the
        // previous time
        if (s.getStartSide() == Square.StartSide.LEFT) {
          s.setX(width + 50);
          s.setxDirection(-1);
          s.setStartSide(Square.StartSide.RIGHT);
        } else if (s.getStartSide() == Square.StartSide.RIGHT) {
          s.setX(-50);
          s.setxDirection(1);
          s.setStartSide(Square.StartSide.LEFT);
        }
        s.setY(START_Y);

        s.setyDirection(0);
        s.setCurrentRow(0);
      }
    }

    // When the randomized timer to change colors reaches 0, change colors
    if (changeSchemeTimer == 0) {
      Color fillColor;
      // If random number is less than 0.5, fill will be the same as background
      if (Math.random() < 0.5) {
        fillColor = darkColors.get(darkColorsIterator);
        // If random number is greater than 0.5, fill will be the same as stroke
      } else {
        fillColor = lightColors.get(lightColorsIterator);
      }
      setColorScheme(new ColorScheme(darkColors.get(darkColorsIterator),
                                     lightColors.get(lightColorsIterator),
                                     fillColor));
      darkColorsIterator++;
      lightColorsIterator++;
      // If the iterators completely finish the lists, set the iterator back to
      // 0 and shuffle the list of colors
      if (darkColorsIterator == darkColors.size()) {
        darkColorsIterator = 0;
        Collections.shuffle(darkColors);
      }
      if (lightColorsIterator == lightColors.size()) {
        lightColorsIterator = 0;
        Collections.shuffle(lightColors);
      }
      // Set timer to change colors to between 3 and 5 seconds
      changeSchemeTimer =
          (int)(Math.random() * FPS * (CC_INTERVAL_END - CC_INTERVAL_START)) +
          CC_INTERVAL_START * FPS;
    } else {
      changeSchemeTimer--;
    }
  }

  // Exits screensaver on click
  public void mouseClicked() { exit(); }

  public void setColorScheme(ColorScheme cs) {
    background = cs.getBackground();
    stroke = cs.getStroke();
    fill = cs.getFill();

    background(background[0], background[1], background[2]);
    stroke(stroke[0], stroke[1], stroke[2]);
    fill(fill[0], fill[1], fill[2]);
    tint(255, 255, 255, (int)(Math.random() * 100 + 126));
  }

  public int[] getCurrentBackground() { return background; }
}
