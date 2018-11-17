package com.nqntan.algorithm.full.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BellmanFord {
    private int[] dist;
    private int[] path;
    private List<Edge> graph;
    private int numberOfNode;
    private int numberOfEdge;

    public BellmanFord(int numberOfNode, int numberOfEdge, int[] dist, int[] path, Scanner scanner) {
        this.dist = dist;
        this.path = path;
        this.numberOfEdge = numberOfEdge;
        this.numberOfNode = numberOfNode;
        for (int i = 0; i < numberOfNode; i++) {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        graph = new ArrayList<>();
        // get edges data from scanner
        for (int i = 0; i < numberOfEdge; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.add(new Edge(u, v, w));
        }
    }

    public void runBellmanFord(int s) {
        int u, v, w;
        dist[s] = 0;
        for (int i = 0; i < numberOfNode; i++) {
            for (int j = 0; j < numberOfEdge; j++) {
                u = graph.get(j).source;
                v = graph.get(j).target;
                w = graph.get(j).weight;
                if (dist[u] != Integer.MAX_VALUE && (dist[u] + w < dist[v])) {
                    dist[v] = dist[u] + w;
                    path[v] = u;
                }
            }
        }
    }

    public boolean isNotContainedNegativeCircle() {
        int u, v, w;
        for (int i = 0; i < numberOfEdge; i++) {
            u = graph.get(i).source;
            v = graph.get(i).target;
            w = graph.get(i).weight;
            if (dist[u] != Integer.MAX_VALUE && (dist[u] + w < dist[v])) {
                return false;
            }
        }
        return true;
    }

    public void printPath(int s, int f, List<Integer> pathRecorder) {
        if (f == s) {
            pathRecorder.add(s);
            return;
        }
        if (path[f] == -1) {
            // No path
            return;
        }
        while (true) {
            pathRecorder.add(f);
            f = path[f];
            if (s == f) {
                pathRecorder.add(f);
                break;
            }
        }
    }

    private class Edge {
        public int source;
        public int target;
        public int weight;

        public Edge(int source, int target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }
}