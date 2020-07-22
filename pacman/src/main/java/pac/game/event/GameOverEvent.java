package pac.game.event;

public class GameOverEvent {

  private String message;

  public GameOverEvent(String message) { this.message = message; }

  public String getMessage() { return message; }

  public void setMessage(String message) { this.message = message; }
}
