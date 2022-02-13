package codeart.cs667.treesearch.impl;

import codeart.cs667.treesearch.Frontier;
import codeart.cs667.treesearch.SearchNode;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PriorityFIFOFrontier implements Frontier {
    public PriorityFIFOFrontier() {
        _queueStates = new HashMap<>();
        _queue = new PriorityQueue<>(
                new Comparator<SearchNode>() {
                    @Override
                    public int compare(SearchNode o1, SearchNode o2) {
                        //default
                        return o1.compareTo(o2);
                    }
                });
    }

    private Map<String, String> _queueStates;
    private Queue<SearchNode> _queue;

    @Override
    public void push(SearchNode node) {
        if (this._queueStates.containsKey(node.getState())) {
            for (SearchNode search : _queue) {
                if (search.getState().equalsIgnoreCase(node.getState())
                        && search.getPathRisk() <= node.getPathRisk())
                    return;
            }
            _queue.removeIf(m -> m.getState().equalsIgnoreCase(node.getState()));
        }

        if (!_queueStates.containsKey(node.getState()))
            _queueStates.put(node.getState(), node.getState());
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
