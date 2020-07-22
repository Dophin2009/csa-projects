package api;

import api.service.TextGenerator;

public class TextGeneratorTest {

  public static void main(String[] args) {
    TextGenerator gen = new TextGenerator();
    System.out.println(gen.getRandomSentence());
    System.out.println(gen.getRandomSentence(10));
  }
}
