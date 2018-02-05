package tree;

import settings.Settings;

import java.util.Map;
import java.util.Objects;

/**
 * Created by maverickedberg on 06/09/2016.
 */
public final class ExpressionTreeFitnessTrainer {

    public static float getFitness(ExpressionTree expressiontree) {

        Objects.requireNonNull(expressiontree);

        float fitness = 0;

        for (Map.Entry<Float, Float> entry : Settings.getFitnessTrainer().entrySet()) {
            fitness = fitness + Math.abs(entry.getValue() - expressiontree.evaluate(entry.getKey()));
        }

        Float value = new Float(fitness);
        return !value.isNaN() && !value.isInfinite() ? fitness : Float.MAX_VALUE;
    }
}
