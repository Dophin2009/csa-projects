package api;

import api.controller.TextController;
import api.service.TextGenerator;
import api.util.Configuration;

public class Application {

  public static void main(String[] args) {
    new Configuration();
    new TextController(new TextGenerator());
  }
}
