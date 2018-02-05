package tree;

import java.util.Random;

/**
 * Created by maverickedberg on 6/6/16.
 */
public enum ExpressionTreeNodeBranchOperands {

    Add(" + ") {
        @Override
        public float apply(float f1, float f2) {
            return (f1 + f2);
        }
    },
    Subtract(" - ") {
        @Override
        public float apply(float f1, float f2) {
            return (f1 - f2);
        }
    },
    Multiply(" • ") {
        @Override
        public float apply(float f1, float f2) {
            return (f1 * f2);
        }
    },
    Divide(" ÷ ") {
        @Override
        public float apply(float f1, float f2) {
            return (f1 / f2);
        }
    };

    private static Random random = new Random();
    private final String value;

    ExpressionTreeNodeBranchOperands(final String value) {
        this.value = value;
    }

    public static ExpressionTreeNodeBranchOperands getRandom() {
        return values()[random.nextInt(values().length)];
    }

    public String getValue() {
        return value;
    }

    public abstract float apply(float f1, float f2);
}
