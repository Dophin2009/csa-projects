package api.controller;

import api.service.TextGenerator;
import spark.Spark;

public class TextController {

  public TextController(final TextGenerator service) {
    Spark.get("/sent", (req, res) -> {
      int length = 10;
      if (req.queryParams("s") != null) {
        length = Integer.parseInt(req.queryParams("s"));
      }
      return service.getRandomSentence(length);
    });

    Spark.get("/sent-per", (req, res) -> service.getRandomSentence());
  }
}
