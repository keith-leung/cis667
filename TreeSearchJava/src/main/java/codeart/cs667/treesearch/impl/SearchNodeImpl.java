package codeart.cs667.treesearch.impl;

import codeart.cs667.treesearch.SearchNode;
import codeart.cs667.treesearch.SearchProblem;

import java.util.ArrayList;
import java.util.List;

public class SearchNodeImpl implements SearchNode {
    public SearchNodeImpl(SearchProblem problem, String currentState,
                          String action,
                          SearchNode parent,
                          double stepCost, double depth){
        this._children = new ArrayList<>();
        this._action = action;
        this._problem = problem;
        this._parent = parent;
        this._stepCost = stepCost;
        this._depth = depth;
        this._state = currentState;
    }

    private SearchProblem _problem;
    private String _state;
    private String _action;
    private SearchNode _parent;
    private List<SearchNode> _children;
    private double _stepCost;
    private double _depth;

    @Override
    public String getState() {
        return _state;
    }

    @Override
    public double getStepCost(){
        return _stepCost;
    }

    @Override
    public double getPathCost() {
        if(_parent == null)
            return _stepCost;
        return _stepCost + this._parent.getPathCost();
    }

    @Override
    public double getPathRisk(){
        return this.getPathCost() + _problem.getHeuristic(_state);
    }

    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public boolean isGoal() {
        return this._problem.isGoal(this._state);
    }

    @Override
    public SearchNode getParent() {
        return this._parent;
    }

    @Override
    public List<SearchNode> getChildren() {
        return this._children;
    }

    @Override
    public String[] getPath() {
        if(this._parent == null)
            return new String[]{};

        List<String> path = new ArrayList<>();
        path.add(this._action);
        String[] array = new String[path.size()];
        path.toArray(array);
        return array;
    }

    @Override
    public int compareTo(Object o) {
        if(o == null)
            return 1;
        if (o instanceof SearchNode){
            SearchNode node = (SearchNode)o;
            return (int) (this.getPathRisk() - node.getPathRisk());
        }
        return -1;
    }
}
