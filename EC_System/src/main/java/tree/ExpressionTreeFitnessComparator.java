package tree;

import java.util.Comparator;

/**
 * Created by maverickedberg on 6/10/16.
 */
public class ExpressionTreeFitnessComparator implements Comparator<ExpressionTree> {

    @Override
    public int compare(ExpressionTree o1, ExpressionTree o2) {
        return ((Float) ExpressionTreeFitnessTrainer.getFitness(o1)).compareTo(
                (ExpressionTreeFitnessTrainer.getFitness(o2))
        );
    }
}
