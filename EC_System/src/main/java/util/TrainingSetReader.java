package util;

import settings.Settings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TrainingSetReader {

  private BufferedReader reader = null;
  private String currentLine = null;
  private TrainingSet data = null;
  private String[] tokens = null;
  private TrainingData di = null;

  public TrainingSetReader() throws IOException{

      data = new TrainingSet();
      reader = new BufferedReader(new FileReader(Settings.getDataFilePath()));
      while((currentLine = reader.readLine()) != null) {
        tokens = currentLine.split(Settings.getDataDelim());
        di = new TrainingData(new Double(tokens[0]), new Double(tokens[1]));
        data.addItem(di);
      }
    }


  public TrainingSet getData() {
    return data;
  }
}