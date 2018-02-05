import settings.Settings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;




public class TrainingSetGenerator {

  static int entries = 100;
  static int maxInput = 1000;
  static String path = ".\\training-set.txt";

  public static void generateData(int entries) {
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(path));
      for (int i = 0; i < entries; i++) {
        double input = Math.random() * maxInput;
        double output = (2*(Math.pow(input, 2) - 1)) / 3;
        bw.write(new Double(input).toString()+ Settings.getDataDelim()+new Double(output).toString()+"\r\n");
        bw.flush();

      }
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public static void main(String[] args) {
    generateData(entries);
  }
}
