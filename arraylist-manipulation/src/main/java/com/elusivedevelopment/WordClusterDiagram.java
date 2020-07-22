package com.elusivedevelopment;

import processing.core.PApplet;

public class WordClusterDiagram extends PApplet {

  private ArrayListManipulation alm;

  public static void main(String[] args) {
    PApplet.main("com.elusivedevelopment.WordClusterDiagram");
  }

  @Override
  public void setup() {
    alm = new ArrayListManipulation("speech.txt");
    background(0);
  }

  @Override
  public void settings() {
    fullScreen();
    noLoop();
  }

  @Override
  public void draw() {
    background(0);
    int size = alm.withoutDuplicates().size();
    for (int i = 0; i < size; i++) {
      fill(255);
      textSize((float)(alm.wordFrequencies()[i] + 2));
      text(alm.withoutDuplicates().get(i),
           (float)Math.random() * width / 2 + (float)width / 4,
           (float)Math.random() * height / 2 + (float)height / 4);
    }
  }
}
