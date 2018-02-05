package tree;

import java.util.Objects;
import java.util.Random;

/**
 * Created by maverickedberg on 6/8/16.
 */
public final class ExpressionTreeNodeBranch implements IExpressionTreeNode {

    private static Random random = new Random();
    private ExpressionTreeNodeBranchOperands operand;
    private IExpressionTreeNode leftNode;
    private IExpressionTreeNode rightNode;

    public ExpressionTreeNodeBranch(final ExpressionTreeNodeBranchOperands operand, IExpressionTreeNode leftNode, IExpressionTreeNode rightNode) {
        this.operand = Objects.requireNonNull(operand);
        this.leftNode = Objects.requireNonNull(leftNode);
        this.rightNode = Objects.requireNonNull(rightNode);
    }

    @Override
    public String getStringRepresentation() {
        return "(" + leftNode.getStringRepresentation() + operand.getValue() + rightNode.getStringRepresentation() + ")";
    }

    @Override
    public float evaluate(float x) {
        return operand.apply(leftNode.evaluate(x), rightNode.evaluate(x));
    }

    @Override
    public void mutate() {
        this.operand = ExpressionTreeNodeBranchOperands.getRandom();
    }

    @Override
    public int getNodeCount() {
        return leftNode.getNodeCount() + rightNode.getNodeCount() + 1;
    }

    @Override
    public int getIndexByNode(IExpressionTreeNode node, int currentIndex) {

        if (node == this) {
            return currentIndex;
        }

        int index = leftNode.getIndexByNode(node, currentIndex + 1);

        if (index != -1) {
            return index;
        } else {
            return rightNode.getIndexByNode(node, currentIndex + leftNode.getNodeCount() + 1);
        }
    }

    @Override
    public IExpressionTreeNode getNodeByIndex(int index, int currentIndex) {

        if (index == currentIndex) {
            return this;
        }

        IExpressionTreeNode node;

        node = leftNode.getNodeByIndex(index, currentIndex + 1);
        if (node != null) {
            return node;
        } else {
            return rightNode.getNodeByIndex(index, currentIndex + leftNode.getNodeCount() + 1);
        }
    }

    @Override
    public IExpressionTreeNode getRandomNodeByHeight(int index, int currentIndex) {

        if (index == currentIndex) {
            return this;
        }

        if (random.nextInt(2) == 0) {
            return leftNode.getRandomNodeByHeight(index, currentIndex + 1);
        } else {
            return rightNode.getRandomNodeByHeight(index, currentIndex + 1);
        }
    }

    @Override
    public boolean setNodeByIndex(int index, int currentIndex, IExpressionTreeNode node) {

        Objects.requireNonNull(node);

        if ((currentIndex + 1) == index) {
            leftNode = node;
            return true;
        }

        if ((currentIndex + leftNode.getNodeCount() + 1) == index) {
            rightNode = node;
            return true;
        }

        return leftNode.setNodeByIndex(index, currentIndex + 1, node) ||
            rightNode.setNodeByIndex(index, currentIndex + leftNode.getNodeCount() + 1, node);
    }

    @Override
    public ExpressionTreeNodeBranch deepClone() {
        return new ExpressionTreeNodeBranch(operand, leftNode.deepClone(), rightNode.deepClone());
    }
}