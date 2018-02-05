package tree;

import java.util.Random;

/**
 * Created by maverickedberg on 6/8/16.
 */
public enum ExpressionTreeNodeLeafIntegers {

    Zero(0),
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9);

    private static Random random = new Random();
    private final int value;

    ExpressionTreeNodeLeafIntegers(final int value) {
        this.value = value;
    }

    public static ExpressionTreeNodeLeafIntegers getRandom() {
        return values()[random.nextInt(values().length)];
    }

    public int getValue() {
        return value;
    }
}
