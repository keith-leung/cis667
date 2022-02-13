package codeart.cs667.treesearch;

public interface Frontier {
    void push(SearchNode node);

    SearchNode pop();

    boolean isEmpty();
}
