package pac.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import pac.game.entity.mobile.Direction;
import pac.game.entity.mobile.Ghost;
import pac.game.entity.mobile.MovingEntity;
import pac.game.entity.mobile.Pacman;
import pac.game.entity.mobile.ai.*;
import pac.game.entity.still.Fruit;
import pac.game.entity.still.Portal;
import pac.game.entity.still.Powerup;
import pac.game.entity.still.Space;
import util.Node;
import util.Util;

public class Level {

  private static final int GHOST_RELEASE_INTERVAL = 90;

  private Game game;
  private volatile Space[][] board;
  private volatile List<Ghost> ghosts;
  private volatile List<Fruit> fruits;
  private volatile Pacman pac;

  private List<Space> spawnArea;

  private long frame;
  private double score;
  private int numberOfDots;

  public Level(Game game, Space[][] board, List<Space> spawnArea,
               List<Fruit> fruits, List<Ghost> ghosts, Pacman pac) {
    this.game = game;
    this.board = board;
    this.spawnArea = spawnArea;
    this.fruits = fruits;
    this.ghosts = ghosts;
    this.pac = pac;
  }

  public Level(Game game, String layoutFile) {
    this.game = game;
    spawnArea = new ArrayList<>();
    fruits = new ArrayList<>();
    ghosts = new ArrayList<>();

    File f = new File(layoutFile);

    int rows = 0;
    int cols = 0;
    try (Scanner reader = new Scanner(f)) {
      while (reader.hasNextLine()) {
        rows++;
        cols = reader.nextLine().length();
      }
      board = new Space[rows][cols];
    } catch (FileNotFoundException ignored) {
    }

    try (Scanner reader = new Scanner(f)) {
      Portal p = null;

      int r = 0;
      while (reader.hasNextLine()) {
        char[] line = reader.nextLine().toCharArray();
        for (int c = 0; c < line.length; c++) {
          switch (line[c]) {
          case '<':
          case '>':
            Portal nP = new Portal(r, c, p);
            if (p != null) {
              p.setOther(nP);
            }
            board[r][c] = nP;
            p = nP;
            break;
          case '.':
            board[r][c] = Space.createBlank(r, c, 10);
            numberOfDots++;
            break;
          case '+':
            board[r][c] = Space.createPowerupSpace(r, c, POWER_PELLET(this));
            numberOfDots++;
            break;
          case 'F':
            Fruit fr = new Fruit(500, 210);
            fruits.add(fr);
            board[r][c] = Space.createBlank(r, c);
            board[r][c].setConsumable(fr);
            break;
          case 'P':
            board[r][c] = Space.createBlank(r, c);
            pac = new Pacman(r, c, true);
            break;
          case 'R':
            board[r][c] = Space.createBlank(r, c);
            Ghost redGhost = new Ghost(RedAI.class, r, c, this, null);
            ghosts.add(redGhost);
            break;
          case 'O':
            board[r][c] = Space.createBlank(r, c);
            Ghost orangeGhost = new Ghost(OrangeAI.class, r, c, this, null);
            ghosts.add(orangeGhost);
            break;
          case 'K':
            board[r][c] = Space.createBlank(r, c);
            Ghost pinkGhost = new Ghost(PinkAI.class, r, c, this, null);
            ghosts.add(pinkGhost);
            break;
          case 'Q':
            board[r][c] = Space.createBlank(r, c);
            Ghost otherGhost = new Ghost(OtherAI.class, r, c, this, null);
            ghosts.add(otherGhost);
            break;
          case 'S':
            Space s = Space.createBlank(r, c);
            board[r][c] = s;
            spawnArea.add(s);
            break;
          case '-':
            board[r][c] = Space.createWall(r, c);
            break;
          default:
            board[r][c] = Space.createBlank(r, c);
            break;
          }
        }
        r++;
      }

    } catch (FileNotFoundException ignored) {
    }

    for (Ghost g : ghosts) {
      g.getAi().setPac(pac);
    }
  }

  public void update(String key) {
    frame++;

    // release ghosts individually on interval
    if (frame <= GHOST_RELEASE_INTERVAL * ghosts.size() && frame > 0 &&
        frame % GHOST_RELEASE_INTERVAL == 0) {
      ghosts.get((int)(frame / GHOST_RELEASE_INTERVAL) - 1).setActive(true);
    }

    // update state of fruits
    fruits.forEach(f -> {
      f.update();
      if (f.getCountdown() <= 0) {
        f.setVisible(!f.isVisible());
        f.restartCountdown();
      }
    });

    // change direction of pacman depending on key press
    Direction newPacDir = Direction.fromString(key);
    if (canChangeDirection(pac, newPacDir)) {
      pac.setDirection(newPacDir);
    }

    // update pacman location
    if (canMove(pac)) {
      pac.update();

      int r = (int)pac.getRow();
      int c = (int)pac.getCol();
      Space s = board[r][c];
      if (s.getConsumable() != null) {
        double oldScore = score;
        if (s.getConsumable().getClass().equals(Fruit.class)) {
          fruits.remove((Fruit)s.getConsumable());
          score += s.consume();

          Fruit fr = new Fruit(500, 210);
          fruits.add(fr);
          s.setConsumable(fr);
        } else {
          numberOfDots--;
          score += s.consume();
        }
        if ((int)(oldScore / 10000) < (int)(score / 10000)) {
          game.gainLife();
        }
      }

      testTeleport(s, pac);
    }

    // update ghosts
    ghosts.forEach(g -> {
      int r = (int)g.getRow();
      int c = (int)g.getCol();
      Space s = board[r][c];
      testTeleport(s, g);

      Direction newGhostDir = g.nextDirection();
      if (newGhostDir != Direction.NONE) {
        if (canChangeDirection(g, newGhostDir)) {
          g.setDirection(newGhostDir);
        }

        if (canMove(g)) {
          g.update();
        }
      }

      // test for collision with pacman
      // there are some clipping issues
      if (pac.isActive() && r == (int)pac.getRow() && c == (int)pac.getCol()) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        if (g.getState() == GhostState.CHASE) {
          pac.respawn();
          pac.setActive(false);
          ghosts.forEach(gh -> gh.getAi().setState(GhostState.FLEE));

          game.loseLife();

          // ridiculously hacky way to create frame-synchronized wait
          executorService.submit(() -> {
            long endFrame = frame + 90;

            // exceedingly dumb way of creating countdown and
            // have compiler not optimize empty loop
            while (true) {
              System.out.println(frame);
              if (frame >= endFrame) {
                pac.setActive(true);
                ghosts.forEach(gh -> gh.getAi().setState(GhostState.CHASE));
                break;
              }
            }
          });
        } else {
          score += g.consume();
          g.respawn();
          g.setActive(false);

          ghosts.forEach(gh -> g.setValue(g.getValue() * 2));

          // same as above
          executorService.submit(() -> {
            long endFrame = frame + 150;

            while (true) {
              System.out.println(frame);
              if (frame >= endFrame) {
                g.setActive(true);
                g.getAi().setState(GhostState.CHASE);
                break;
              }
            }
          });
        }
      }
    });
  }

  private void testTeleport(Space s, MovingEntity e) {
    if (s.getClass() == Portal.class && e.atIntersection()) {
      ((Portal)s).teleport(e);
    }
  }

  private boolean canMove(MovingEntity e) {
    double[] nextRC = e.nextRowCol();
    int r = Math.min(Math.max(0, (int)nextRC[0]), board.length - 1);
    int c = Math.min(Math.max(0, (int)nextRC[1]), board[0].length - 1);
    Space next = board[r][c];
    return next.isWalkable();
  }

  private boolean canChangeDirection(MovingEntity e, Direction newDir) {
    if (e.canChangeDirection(newDir)) {
      Direction currentDirection = e.getDirection();
      e.setDirection(newDir);
      boolean canMove = canMove(e);
      e.setDirection(currentDirection);
      return canMove;
    }
    return false;
  }

  public boolean atCrossroads(MovingEntity e) {
    if (!e.atIntersection()) {
      return false;
    }

    List<Space> adjacents = getAdjacentSpaces((int)e.getRow(), (int)e.getCol());
    adjacents.removeIf(s -> !s.isWalkable());
    if (adjacents.size() <= 1) {
      return false;
    }

    double[] nextRC = e.nextRowCol();
    Space front = board[(int)nextRC[0]][(int)nextRC[1]];
    for (Space s : adjacents) {
      if (!collinear(s, front)) {
        return true;
      }
    }
    return false;
  }

  private boolean collinear(Space s1, Space s2) {
    double dR = Util.round(s1.getRow() - s2.getRow(), 2);
    double dC = Util.round(s1.getCol() - s2.getCol(), 2);
    return dR == 0 || dC == 0;
  }

  public boolean inBound(int r, int c) {
    return r >= 0 && r < board.length && c >= 0 && c < board[r].length;
  }

  public List<Space> getAdjacentSpaces(int r, int c) {
    List<int[]> adjCoordinates = new LinkedList<>();
    adjCoordinates.add(new int[] {r - 1, c});
    adjCoordinates.add(new int[] {r + 1, c});
    adjCoordinates.add(new int[] {r, c - 1});
    adjCoordinates.add(new int[] {r, c + 1});

    return adjCoordinates.stream()
        .filter(co -> inBound(co[0], co[1]))
        .map(co -> board[co[0]][co[1]])
        .collect(Collectors.toList());
  }

  public boolean isSpawn(int r, int c) {
    return spawnArea.contains(board[r][c]);
  }

  public Space[][] getBoard() { return board; }

  public void setBoard(Space[][] board) { this.board = board; }

  public List<Ghost> getGhosts() { return ghosts; }

  public void setGhosts(List<Ghost> ghosts) { this.ghosts = ghosts; }

  public Pacman getPac() { return pac; }

  public void setPac(Pacman pac) { this.pac = pac; }

  public Game getGame() { return game; }

  public void setGame(Game game) { this.game = game; }

  public double getScore() { return score; }

  public void setScore(double score) { this.score = score; }

  public long getFrame() { return frame; }

  public void setFrame(long frame) { this.frame = frame; }

  public int getNumberOfDots() { return numberOfDots; }

  public void setNumberOfDots(int numberOfDots) {
    this.numberOfDots = numberOfDots;
  }

  public Node[][] mapToNodes() {
    Space[][] bd = board;
    Node[][] nodes = new Node[bd.length][bd[0].length];
    for (int i = 0; i < bd.length; i++) {
      for (int j = 0; j < bd[i].length; j++) {
        Space e = bd[i][j];
        nodes[i][j] = new Node(i, j, e.isWalkable());
      }
    }
    return nodes;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    Arrays.stream(board).forEach(row -> {
      Arrays.stream(row).forEach(e -> {
        char c;
        if (!e.isWalkable()) {
          c = '#';
        } else if (e.getClass() == Portal.class) {
          c = 'P';
        } else if (e.getConsumable() != null) {
          if (e.getConsumable().getClass() == Powerup.class) {
            c = '+';
          } else {
            c = '.';
          }
        } else {
          c = ' ';
        }
        s.append(c).append(" ");
      });
      s.append("\n");
    });
    return s.toString();
  }

  private static Powerup POWER_PELLET(Level level) {
    return new Powerup(50, () -> {
      long currentFrame = level.frame;
      long endFrame = currentFrame + 150;
      level.ghosts.forEach(g -> g.getAi().setState(GhostState.FLEE));

      ExecutorService service = Executors.newSingleThreadExecutor();
      service.submit(() -> {
        long fr = level.frame;
        while (fr <= endFrame) {
          fr = level.frame;
          System.out.println(fr);
        }
        level.ghosts.forEach(g -> {
          if (g.isActive()) {
            g.getAi().setState(GhostState.CHASE);
          }
        });
      });
    });
  }
}
