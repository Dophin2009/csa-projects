package pac.game.entity.mobile.ai;

import java.util.List;
import pac.game.Level;
import pac.game.entity.mobile.Ghost;
import pac.game.entity.mobile.Pacman;
import util.Node;

public class OrangeAI extends GhostAI {

  public OrangeAI(Ghost ghost, Level board, Pacman pac) {
    super(ghost, board, pac);
  }

  @Override
  protected double[] chase() {
    if (getGhost().getRow() == getPac().getRow() &&
        getGhost().getCol() == getPac().getCol()) {
      return getGhost().coordinates();
    }

    List<Node> path = fastestPathToPac();
    if (path.size() < 15) {
      return random();
    }
    return path.get(0).coordinates();
  }

  @Override
  protected double[] flee() {
    return random();
  }
}
