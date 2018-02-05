package settings;


import java.util.HashMap;

public class Settings {

    private static String dataFilePath = "/Users/z001ttz/Desktop/training-set.txt";
    private static int generationSize = 10;
    private static int initialTreeSize = 5;
    private static String dataDelimiter = ";";
    private static int fitnessThreshold = 0;
    private static int executionLength = 15;
    private static HashMap<Float, Float> fitnessTrainer = new HashMap<Float, Float>();

    public static int getExecutionLength() {
        return executionLength;
    }

    public static void setExecutionLength(int time) {
        executionLength = time;
    }

    public static int getFitnessThreshold() {
        return fitnessThreshold;
    }

    public static void setFitnessThreshold(int ft) {
        fitnessThreshold = ft;
    }

    public static int getGenerationSize() {
        return generationSize;
    }

    public static void setGenerationSize(int size) {
        generationSize = size;
    }

    public static int getTreeSize() {
        return initialTreeSize;
    }

    public static void setTreeSize(int size) {
        initialTreeSize = size;
    }

    public static String getDataFilePath() {
        return dataFilePath;
    }

    public static void setDataFilePath(String s) {
        dataFilePath = s;
    }

    public static String getDataDelim() {
        return dataDelimiter;
    }

    public static void setDataDelim(String delim) {
        dataDelimiter = delim;
    }

    public static HashMap<Float, Float> getFitnessTrainer() {
        return fitnessTrainer;
    }

    public static void setFitnessTrainer(HashMap<Float, Float> trainer) {
        fitnessTrainer = trainer;
    }
}