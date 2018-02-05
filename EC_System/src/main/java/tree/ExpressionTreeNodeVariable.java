package tree;

import java.util.Objects;

/**
 * Created by maverickedberg on 6/8/16.
 */
public class ExpressionTreeNodeVariable implements IExpressionTreeNode {

    public ExpressionTreeNodeVariable() {
    }

    @Override
    public String getStringRepresentation() {
        return "x";
    }

    @Override
    public float evaluate(float x) {
        return x;
    }

    @Override
    public void mutate() {
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
    public ExpressionTreeNodeVariable deepClone() {
        return new ExpressionTreeNodeVariable();
    }
}
