package pac.game.entity.mobile.ai;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import pac.game.Level;
import pac.game.entity.mobile.Ghost;
import pac.game.entity.mobile.Pacman;
import pac.game.entity.still.Space;
import util.AStar;
import util.Node;

public abstract class GhostAI {

  private GhostState state;

  private Ghost ghost;
  private Level board;
  private Pacman pac;

  public GhostAI(Ghost ghost, Level board, Pacman pac) {
    this.state = GhostState.CHASE;
    this.ghost = ghost;
    this.board = board;
    this.pac = pac;
  }

  protected abstract double[] chase();

  protected double[] flee() {
    Node close = fastestPathToPac().get(0);
    double[] chase = close.coordinates();

    List<Space> adjacent = getBoard().getAdjacentSpaces(
        (int)getGhost().getRow(), (int)getGhost().getCol());
    adjacent = adjacent.stream()
                   .filter(s -> {
                     if (!s.isWalkable()) {
                       return false;
                     }
                     return (int)s.getRow() != (int)chase[0] ||
                         (int)s.getCol() != (int)chase[1];
                   })
                   .collect(Collectors.toList());
    Space next = adjacent.get(new Random().nextInt(adjacent.size()));
    return next.coordinates();
  }

  public final double[] nextMove() {
    if (board.isSpawn((int)ghost.getRow(), (int)ghost.getCol())) {
      List<Node> path = fastestPathToPac();
      Node next = path.get(0);
      return next.coordinates();
    }

    if (state == GhostState.FLEE) {
      return flee();
    }
    return chase();
  }

  protected List<Node> fastestPathToPac() {
    Node[][] nodes = getBoard().mapToNodes();
    double[] backRC = getGhost().prevRowCol();
    nodes[(int)backRC[0]][(int)backRC[1]].setWalkable(false);

    return AStar.search(nodes, (int)getGhost().getRow(),
                        (int)getGhost().getCol(), (int)getPac().getRow(),
                        (int)getPac().getCol());
  }

  protected double[] random() {
    double[] backRC = getGhost().prevRowCol();
    Space back = getBoard().getBoard()[(int)backRC[0]][(int)backRC[1]];
    List<Space> adjacents = getBoard()
                                .getAdjacentSpaces((int)getGhost().getRow(),
                                                   (int)getGhost().getCol())
                                .stream()
                                .filter(s -> s.isWalkable() && s != back)
                                .collect(Collectors.toList());

    Space next = adjacents.get(new Random().nextInt(adjacents.size()));
    return next.coordinates();
  }

  public boolean atCrossroads() { return getBoard().atCrossroads(ghost); }

  public GhostState getState() { return state; }

  public void setState(GhostState state) { this.state = state; }

  public Ghost getGhost() { return ghost; }

  public Level getBoard() { return board; }

  public Pacman getPac() { return pac; }

  public void setPac(Pacman pac) { this.pac = pac; }
}
