import tree.ExpressionTree;
import tree.ExpressionTreeFactory;
import tree.ExpressionTreeFitnessComparator;
import tree.ExpressionTreeFitnessTrainer;

import java.util.Arrays;
import java.util.Random;

public class DataStructureTest {
  private Random gen = new Random();
  private long testStartTime;
  private long testEndTime;
  private double treeValue;
  public static int generationTestNum = 1;
  public static int valueTestNum = 1;
  public static boolean treePrintOut = false;

  public DataStructureTest(){
    generationTest();
  }
  public void generationTest() {
    testStartTime = System.currentTimeMillis();

    int populationCounter = 1;
    ExpressionTree[] population = ExpressionTreeFactory.getRandomExpressionTreePopulation();
    ExpressionTreeFitnessComparator comparator = new ExpressionTreeFitnessComparator();

    Arrays.sort(population, comparator);

    if (ExpressionTreeFitnessTrainer.getFitness(population[0]) == 0) {
      System.out.println("Took " + 1 + " population to solve: " + population[0].getStringRepresentation());
    }

    while (ExpressionTreeFitnessTrainer.getFitness(population[0]) != 0) {

      if (populationCounter == 1000) {
        System.out.println("Resetting population...");
        population = ExpressionTreeFactory.getRandomExpressionTreePopulation();
        populationCounter = 1;
      } else {
        population = ExpressionTreeFactory.getNextExpressionTreePopulation(population[0], population[1]);
        populationCounter = populationCounter + 1;
      }


      Arrays.sort(population, comparator);
    }

    System.out.println("Took " + populationCounter + " population to solve: " + population[0].getStringRepresentation());

    testEndTime = System.currentTimeMillis();
    System.out.println("Generation Test " + generationTestNum);
    System.out.println("*****************");
    System.out.println("Test length: " + new Long(testEndTime-testStartTime).toString() + " ms");
    System.out.println();
    generationTestNum++;
  }



  public static void main(String[] args) {
    DataStructureTest dataStructureTest = new DataStructureTest();
  }


 }