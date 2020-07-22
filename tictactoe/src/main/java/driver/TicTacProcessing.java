package driver;

import controlP5.*;
import player.Player;
import player.TicTacMaster;
import processing.core.PApplet;
import processing.core.PImage;
import tictactoe.Board;
import tictactoe.GameType;
import tictactoe.Piece;

public class TicTacProcessing extends PApplet {

  private static final String IMAGE_FOLDER = "tictactoe/";

  private ControlP5 cp5;
  private float squareSize;
  private Slider boardSizeSlider;
  private DropdownList gameModeList;
  private Slider difficultySlider;
  private DropdownList pieceList;
  private DropdownList imageList;
  private Piece selected;

  private Board b;
  private Player p1, p2;
  private Player current;
  private PImage exImage, ohImage;
  private String[] imageSet;

  private int boardSize = 3;
  private GameType mode = GameType.HVH;

  private boolean active;

  public static void main(String[] args) {
    PApplet.main("driver.TicTacProcessing");
  }

  @Override
  public void setup() {
    background(255);
    init();
  }

  private void init() {
    clear();
    b = null;
    active = false;
    cp5 = new ControlP5(this);
    setupPrompts();
  }

  private void setupPrompts() {
    boardSizeSlider = new Slider(cp5, "boardSize");
    boardSizeSlider.setPosition(100, 140)
        .setSize(400, 40)
        .setRange(3, 10)
        .setColorBackground(color(175))
        .setColorForeground(color(175))
        .setColorActive(color(125))
        .setNumberOfTickMarks(8);

    gameModeList = new DropdownList(cp5, "modeSelect");
    gameModeList.addItem("vs. Human", GameType.HVH)
        .addItem("vs. TicTacBot", GameType.HVTTME);
    gameModeList.setPosition(100, 200)
        .setSize(400, 100)
        .setColorBackground(color(175))
        .setColorForeground(color(175))
        .setColorActive(color(125));

    difficultySlider = new Slider(cp5, "difficultySlider");
    difficultySlider.setPosition(100, 240)
        .setLabel("Difficult Slider")
        .setSize(400, 20)
        .setRange(1, 10)
        .setColorBackground(color(175))
        .setColorForeground(color(175))
        .setColorActive(color(125))
        .setNumberOfTickMarks(10);

    pieceList = new DropdownList(cp5, "pieceSelect");
    pieceList.addItem("EX", Piece.EX).addItem("OH", Piece.OH);
    pieceList.setPosition(100, 350)
        .setSize(400, 100)
        .setColorBackground(color(175))
        .setColorForeground(color(175))
        .setColorActive(color(125));

    imageList = new DropdownList(cp5, "imageList");
    imageList
        .addItem("iwinski vs. todd",
                 new String[] {"iwinski.png",
                               "todd.png"}) // Tell me lies, sweet little lies
        .addItem("pewds vs. tseries",
                 new String[] {"pewds.png", "tseries.png"}) // Subscribe
        .addItem("cd projekt vs. ea",
                 new String[] {"kicinski.png", "eabad.png"}) // EA bad
        .addItem(
            "league vs. dota 2",
            new String[] {"league.png", "dota2.png"}) // I don't play either
        .addItem("windows vs. mac",
                 new String[] {"microsoft.png", "apple.png"}) // Apple sucks
        .addItem("zero vs. nairo",
                 new String[] {"zero.png",
                               "nairo.png"}) // Nothing against Nairo of course
        .addItem(
            "faendal vs. sven",
            new String[] {"faendal.png", "sven.png"}) // Faendal's more useful
        .addItem("amd vs. nvidia", new String[] {"amd.png", "nvidia.png"})
        .addItem("pcmasterrace vs. console peasants",
                 new String[] {"pcmasterrace.png",
                               "console.png"}) // Long live the master race
        .addItem("gitlab vs. github",
                 new String[] {"gitlab.png", "github.png"}) // Private repos
        .addItem("reddit vs. 4chan",
                 new String[] {
                     "reddit.png",
                     "4chan.png"}) // 4chan is better; reddit more accessible
        .addItem("elon vs. mark", new String[] {"elon.png", "zuckerberg.png"});
    imageList.setPosition(100, 400)
        .setSize(400, 100)
        .setColorBackground(color(175))
        .setColorForeground(color(175))
        .setColorActive(color(125));
    // default setting
    imageSet = (String[])imageList.getItem(0).get("value");

    cp5.addButton("finishSetup")
        .activateBy(ControlP5Constants.RELEASE)
        .setColorBackground(color(175))
        .setColorForeground(color(175))
        .setColorActive(color(125));
  }

  public void controlEvent(ControlEvent event) {
    Controller c = event.getController();
    if (c.getName().equals("boardSize")) {
      boardSize = (int)event.getValue();
    }
    if (c.getName().equals("modeSelect")) {
      mode = (GameType)gameModeList.getItem((int)event.getValue()).get("value");
    }
    if (c.getName().equals("pieceSelect")) {
      selected = (Piece)pieceList.getItem((int)event.getValue()).get("value");
    }
    if (c.getName().equals("imageList")) {
      imageSet =
          (String[])imageList.getItem((int)event.getValue()).get("value");
    }
    if (c.getName().equals("finishSetup")) {
      cp5.hide();

      b = new Board(boardSize);
      squareSize = (float)width / b.getSize();

      if (mode == GameType.HVH) {
        p1 = new Player(b, Piece.EX, Player.PlayerType.HUMAN);
        p2 = new Player(b, Piece.OH, Player.PlayerType.HUMAN);
      } else {
        p1 = new Player(b, selected, Player.PlayerType.HUMAN);
        Piece opponent = selected == Piece.EX ? Piece.OH : Piece.EX;
        p2 = new TicTacMaster(b, opponent, (int)difficultySlider.getValue());
      }
      current = p1.getPiece() == Piece.EX ? p1 : p2;

      if (p1.getPiece() == Piece.EX) {
        exImage = loadImage(IMAGE_FOLDER + imageSet[0]);
        ohImage = loadImage(IMAGE_FOLDER + imageSet[1]);
      } else {
        exImage = loadImage(IMAGE_FOLDER + imageSet[1]);
        ohImage = loadImage(IMAGE_FOLDER + imageSet[0]);
      }
      exImage.resize((int)squareSize, (int)squareSize);
      ohImage.resize((int)squareSize, (int)squareSize);

      active = true;
    }
  }

  @Override
  public void settings() {
    size(600, 600);
  }

  @Override
  public void draw() {
    background(255);
    try {
      drawBoard();
    } catch (NullPointerException ignored) {
    }
    if (active) {
      if (mode != GameType.HVH &&
          current.getType() == Player.PlayerType.COMPUTER) {
        cpuMove();
        checkOver();
        current = (current == p1) ? p2 : p1;
      }
    }
  }

  @Override
  public void mousePressed() {
    if (active && current.getType() == Player.PlayerType.HUMAN) {
      try {
        placePiece(current, (int)(mouseX / squareSize),
                   (int)(mouseY / squareSize));
        if (!checkOver()) {
          current = (current == p1) ? p2 : p1;
        }
      } catch (IllegalArgumentException ignored) {
      }
    }
  }

  @Override
  public void keyPressed() {
    switch (key) {
    case 'r':
      init();
      break;
    default:
      break;
    }
  }

  private void cpuMove() {
    int[] move;
    TicTacMaster c = (TicTacMaster)current;
    if (mode == GameType.HVRC) {
      move = c.randomMove();
    } else {
      move = c.getMove();
    }
    b.place(current.getPiece(), move[0], move[1]);
  }

  private void placePiece(Player p, int r, int c) {
    b.place(p.getPiece(), r, c);
  }

  private void drawBoard() {
    for (int i = 0; i < b.getSize(); i++) {
      for (int j = 0; j < b.getSize(); j++) {
        stroke(0);
        fill(255);
        rect(i * squareSize, j * squareSize, squareSize, squareSize);
        if (b.getBoard()[i][j] != Piece.EMPTY) {
          PImage load = b.getBoard()[i][j] == Piece.EX ? exImage : ohImage;
          image(load, i * squareSize, j * squareSize);
        }
      }
    }
  }

  private boolean checkOver() {
    boolean over = false;
    if (b.checkBoard(current.getPiece()) || b.emptySpaces().length == 0) {
      over = true;
      active = false;
    }
    return over;
  }
}
