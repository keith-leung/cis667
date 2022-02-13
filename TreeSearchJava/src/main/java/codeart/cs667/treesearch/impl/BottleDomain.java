package codeart.cs667.treesearch.impl;

import codeart.cs667.treesearch.SearchNode;
import codeart.cs667.treesearch.SearchProblem;

public class BottleDomain implements SearchProblem {
    public BottleDomain(int capacities){
        this._capacities = capacities;
    }
    private int _capacities;

    private String pack(){
        return "";
    }

    private void unPack(){

    }

    @Override
    public SearchNode getRootNode() {
        return null;
    }

    @Override
    public double getHeuristic(String state) {
        return 0;
    }

    @Override
    public boolean isGoal(String state) {
        return false;
    }

    @Override
    public String[] getValidActions(String state) {
        return new String[0];
    }

    @Override
    public void performAction(String state, String action) {

    }
}
