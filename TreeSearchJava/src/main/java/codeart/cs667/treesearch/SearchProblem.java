package codeart.cs667.treesearch;

public interface SearchProblem {
    SearchNode getRootNode();

    double getHeuristic(String state);

    boolean isGoal(String state);

    String[] getValidActions(String state);

    void performAction(String state, String action);
}
