package codeart.cs667.treesearch.impl;

import codeart.cs667.treesearch.Frontier;
import codeart.cs667.treesearch.SearchNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FIFOFrontier implements Frontier {

    public FIFOFrontier(){
        _queue = new LinkedList<>();
        _queueStates = new HashMap<>();
    }

    private Map<String, String> _queueStates;
    private Queue<SearchNode> _queue;

    @Override
    public void push(SearchNode node) {
        if(this._queueStates.containsKey(node.getState()))
            return;
        _queueStates.put(node.getState(),node.getState());
        _queue.add(node);
    }

    @Override
    public SearchNode pop() {
        return _queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return _queue.isEmpty();
    }
}
