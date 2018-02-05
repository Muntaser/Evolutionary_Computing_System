package tree;

import java.util.Objects;

/**
 * Created by maverickedberg on 6/8/16.
 */
public final class ExpressionTreeNodeLeaf implements IExpressionTreeNode {

    private ExpressionTreeNodeLeafIntegers integer;

    public ExpressionTreeNodeLeaf(ExpressionTreeNodeLeafIntegers integer) {
        this.integer = Objects.requireNonNull(integer);
    }

    @Override
    public String getStringRepresentation() {
        return String.valueOf(integer.getValue());
    }

    @Override
    public float evaluate(float x) {
        return ((float) integer.getValue());
    }

    @Override
    public void mutate() {
        this.integer = ExpressionTreeNodeLeafIntegers.getRandom();
    }

    @Override
    public int getNodeCount() {
        return 1;
    }

    @Override
    public int getIndexByNode(IExpressionTreeNode node, int currentIndex) {
        if (node == this) {
            return currentIndex;
        } else {
            return -1;
        }
    }

    @Override
    public IExpressionTreeNode getNodeByIndex(int index, int currentIndex) {
        return index == currentIndex ? this : null;
    }

    @Override
    public IExpressionTreeNode getRandomNodeByHeight(int index, int currentIndex) {
        return this;
    }

    @Override
    public boolean setNodeByIndex(int index, int currentIndex, IExpressionTreeNode node) {

        Objects.requireNonNull(node);

        return false;
    }

    @Override
    public ExpressionTreeNodeLeaf deepClone() {
        return new ExpressionTreeNodeLeaf(integer);
    }
}