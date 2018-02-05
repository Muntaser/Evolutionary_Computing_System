package tree;


import settings.Settings;

import java.util.Objects;
import java.util.Random;

/**
 * Created by maverickedberg on 6/6/16.
 */
public final class ExpressionTree implements ICloneable<ExpressionTree> {

    private IExpressionTreeNode rootNode;
    private Random random = new Random();

    public ExpressionTree(IExpressionTreeNode rootNode) {
        this.rootNode = Objects.requireNonNull(rootNode);
    }

    public float evaluate(float x) {
        return rootNode.evaluate(x);
    }

    public String getStringRepresentation() {
        return rootNode.getStringRepresentation();
    }

    public void mutate() {
        getRandomNode().mutate();
    }

    public void crossover(ExpressionTree otherExpressionTree) {

        Objects.requireNonNull(otherExpressionTree);

        int randomHeight = random.nextInt(Settings.getTreeSize()) + 1;

        IExpressionTreeNode myCrossoverNode = getRandomNodeByHeight(randomHeight);
        int myCrossoverNodeIndex = getIndexByNode(myCrossoverNode);
        myCrossoverNode = myCrossoverNode.deepClone();

        IExpressionTreeNode theirCrossoverNode = otherExpressionTree.getRandomNodeByHeight(randomHeight);
        int theirCrossoverNodeIndex = otherExpressionTree.getIndexByNode(theirCrossoverNode);
        theirCrossoverNode = theirCrossoverNode.deepClone();

        setNodeByIndex(myCrossoverNodeIndex, theirCrossoverNode);
        otherExpressionTree.setNodeByIndex(theirCrossoverNodeIndex, myCrossoverNode);
    }

    @Override
    public ExpressionTree deepClone() {
        return new ExpressionTree(rootNode.deepClone());
    }

    private int getNodeCount() {
        return rootNode.getNodeCount();
    }

    private IExpressionTreeNode getRandomNode() {
        return getNodeByIndex(random.nextInt(getNodeCount()));
    }

    private IExpressionTreeNode getRandomNodeByHeight(int index) {

        if (index < 1 || index > Settings.getTreeSize()) {
            throw new IndexOutOfBoundsException();
        }

        return rootNode.getRandomNodeByHeight(index, 0);
    }

    private int getIndexByNode(IExpressionTreeNode node) {
        return rootNode.getIndexByNode(node, 0);
    }

    private IExpressionTreeNode getNodeByIndex(int index) {

        if (index < 0 || index > getNodeCount() - 1) {
            throw new IndexOutOfBoundsException();
        }

        return rootNode.getNodeByIndex(index, 0);
    }

    private void setNodeByIndex(int index, IExpressionTreeNode node) throws IndexOutOfBoundsException{

        Objects.requireNonNull(node);

        if (index < 0 || index > getNodeCount() - 1) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            rootNode = node;
        } else {
            rootNode.setNodeByIndex(index, 0, node);
        }
    }
}