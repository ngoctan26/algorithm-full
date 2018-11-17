package com.nqntan.algorithm.full.common;

import java.util.List;

public class DFS {
    private List<Boolean> visited;
    private List<List<Integer>> graph;
    private List<Integer> path;
    private Integer numberOfNode;

    public DFS(int numberOfNode, List<Boolean> visited, List<List<Integer>> graph, List<Integer> path) {
        this.numberOfNode = numberOfNode;
        this.visited = visited;
        this.graph = graph;
        this.path = path;

        for (int i = 0; i < numberOfNode; i++) {
            path.add(-1);
            visited.add(false);
        }
    }

    public void dfsRecursion(int s) {
        visited.set(s, true);
        for (int i = 0; i < graph.get(s).size(); i++) {
            int v = graph.get(s).get(i);
            if (!visited.get(v)) {
                path.set(v, s);
                dfsRecursion(v);
            }
        }
    }

    public void printPath(int s, int f, List<Integer> pathRecorder) {
        if (f == s) {
            pathRecorder.add(s);
            return;
        }
        if (path.get(f) == -1) {
            // No path
            return;
        }
        while (true) {
            pathRecorder.add(f);
            f = path.get(f);
            if (s == f) {
                pathRecorder.add(f);
                break;
            }
        }
    }
}
