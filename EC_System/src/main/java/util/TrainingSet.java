package util;


import java.util.ArrayList;

public class TrainingSet {

  private ArrayList<TrainingData> set = new ArrayList<TrainingData>();

  public TrainingSet() {

  }

  public void addItem(TrainingData i) {
    set.add(i);
  }

  public void printContents() {
    for(TrainingData di : set) {
      System.out.println("x=" + di.getX() + "\ty=" + di.getY());
    }
  }
}