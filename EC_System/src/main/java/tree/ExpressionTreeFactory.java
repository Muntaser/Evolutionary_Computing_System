package tree;

import settings.Settings;

import java.util.Objects;
import java.util.Random;

/**
 * Created by maverickedberg on 6/8/16.
 */
public final class ExpressionTreeFactory {

    private static int minTreeHeight = 3;

    private static Random random = new Random();

    public static ExpressionTree[] getRandomExpressionTreePopulation() {

        if (Settings.getGenerationSize() < 7) {
            try {
                throw new Exception("populationSize must be at least seven.");
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
        }

        ExpressionTree[] expressionTreePopulation = new ExpressionTree[Settings.getGenerationSize()];

        for (int i = 0; i < Settings.getGenerationSize(); i++) {
            expressionTreePopulation[i] = getRandomExpressionTree();
        }

        return expressionTreePopulation;
    }

    public static ExpressionTree[] getNextExpressionTreePopulation(ExpressionTree expressionTree1, ExpressionTree expressionTree2) {

        Objects.requireNonNull(expressionTree1);
        Objects.requireNonNull(expressionTree2);

        if (Settings.getGenerationSize() < 7) {
            try {
                throw new Exception("populationSize must be at least seven.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }        }

        ExpressionTree[] expressionTreePopulation = new ExpressionTree[Settings.getGenerationSize()];

        ExpressionTree crossover1 = expressionTree1.deepClone();
        ExpressionTree crossover2 = expressionTree2.deepClone();
        crossover(crossover1, crossover2);

        expressionTreePopulation[0] = expressionTree1;
        expressionTreePopulation[1] = expressionTree2;
        expressionTreePopulation[2] = crossover1;
        expressionTreePopulation[3] = crossover2;

        int expressionTree1MutationCount = ((int) Math.floor((Settings.getGenerationSize() - 4) / 3));
        int expressionTree2MutationCount = ((int) Math.ceil((Settings.getGenerationSize() - 4) / 3));

        for (int i = 4; i < 4 + expressionTree1MutationCount; i++) {

            ExpressionTree crossover1Mutation = crossover1.deepClone();
            crossover1Mutation.mutate();

            expressionTreePopulation[i] = crossover1Mutation;
        }

        for (int i = 4 + expressionTree1MutationCount; i < 4 + expressionTree1MutationCount + expressionTree2MutationCount; i++) {

            ExpressionTree crossover2Mutation = crossover2.deepClone();
            crossover2Mutation.mutate();

            expressionTreePopulation[i] = crossover2Mutation;
        }

        for (int i = 4 + expressionTree1MutationCount + expressionTree2MutationCount; i < Settings.getGenerationSize(); i++) {
            expressionTreePopulation[i] = getRandomExpressionTree();
        }

        return expressionTreePopulation;
    }

    public static float evaluate(ExpressionTree expressionTree, int x) {

        Objects.requireNonNull(expressionTree);

        return expressionTree.evaluate(x);
    }

    public static void mutate(ExpressionTree expressionTree) {

        Objects.requireNonNull(expressionTree);

        expressionTree.mutate();
    }

    public static void crossover(ExpressionTree expressionTree, ExpressionTree otherExpressionTree) {

        Objects.requireNonNull(expressionTree);
        Objects.requireNonNull(otherExpressionTree);

        expressionTree.crossover(otherExpressionTree);
    }

    public static ExpressionTree getClonedExpressionTree(ExpressionTree expressionTree) {

        Objects.requireNonNull(expressionTree);

        return expressionTree.deepClone();
    }

    private static ExpressionTree getRandomExpressionTree() {

        if (minTreeHeight <= 0) {
            try {
                throw new Exception("minTreeHeight must be greater than zero.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (minTreeHeight > Settings.getTreeSize()) {
            try {
                throw new Exception("minTreeHeight must be less than maxTreeHeight.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return new ExpressionTree(getRandomExpressionTreeNode(0));
    }

    public static IExpressionTreeNode getRandomExpressionTreeNode(int height) {

        if (height == (Settings.getTreeSize() - 1) || (height >= (minTreeHeight) - 1 && random.nextInt(Settings.getTreeSize() - minTreeHeight + 1) == 0)) {
            if (random.nextInt(2) == 0) {
                return new ExpressionTreeNodeLeaf(ExpressionTreeNodeLeafIntegers.getRandom());
            } else {
                return new ExpressionTreeNodeVariable();
            }
        }

        return new ExpressionTreeNodeBranch(ExpressionTreeNodeBranchOperands.getRandom(), getRandomExpressionTreeNode(height + 1), getRandomExpressionTreeNode(height + 1));
    }
}