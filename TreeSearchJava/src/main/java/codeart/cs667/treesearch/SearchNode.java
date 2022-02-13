package codeart.cs667.treesearch;

import java.util.List;

public interface SearchNode extends Comparable{
    String getState();

    double getStepCost();

    double getPathCost();

    double getPathRisk();

    int getDepth();

    boolean isGoal();

    SearchNode getParent();

    List<SearchNode> getChildren();

    String[] getPath();
}
