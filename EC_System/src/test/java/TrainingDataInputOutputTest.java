import util.TrainingSetReader;

import java.io.IOException;

public class TrainingDataInputOutputTest {

  private TrainingSetReader dr = null;

  public TrainingDataInputOutputTest() throws IOException {
    dr = new TrainingSetReader();
    dr.getData().printContents();
  }

  public static void main(String[] args) throws IOException {
    TrainingDataInputOutputTest t = new TrainingDataInputOutputTest();
  }
}