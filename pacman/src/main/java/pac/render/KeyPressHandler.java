package pac.render;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressHandler implements EventHandler<KeyEvent> {

  private String lastKey = "LEFT";

  public void handle(KeyEvent event) {
    String key = event.getCode().toString();
    lastKey = key;
  }

  public String getLastKey() { return lastKey; }
}
