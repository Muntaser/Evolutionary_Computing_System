package tree;

/**
 * Created by maverickedberg on 6/8/16.
 */
public interface IExpressionTreeNode extends ICloneable<IExpressionTreeNode> {

    String getStringRepresentation();

    float evaluate(float x);

    void mutate();

    int getNodeCount();

    int getIndexByNode(IExpressionTreeNode node, int currentIndex);

    IExpressionTreeNode getNodeByIndex(int index, int currentIndex);

    IExpressionTreeNode getRandomNodeByHeight(int index, int currentIndex);

    boolean setNodeByIndex(int index, int currentIndex, IExpressionTreeNode node);
}
