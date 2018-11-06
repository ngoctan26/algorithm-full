package com.nqntan.algorithm.full.common;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Prim {

    private void prim(int src, int[][] graph) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int n = graph.length;
        int[] dist = new int[n];
        int[] path = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        queue.add(new Node(src, 0));
        dist[src] = 0;
        while (!queue.isEmpty()) {
            Node top = queue.poll();
            int u = top.id;
            visited[u] = true;
            for (int i = 0; i < graph[u].length; i++) {
                Node neighbor = new Node(i, graph[u][i]);
                if (!visited[neighbor.id] && neighbor.dist < dist[neighbor.id]) {
                    dist[neighbor.id] = neighbor.dist;
                    queue.add(neighbor);
                    path[neighbor.id] = u;
                }
            }
        }
    }

    private static int getMST(int[] dist, int[] path) {
        int n = dist.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (path[i] == -1) {
                continue;
            }
            ans += dist[i];
        }
        return ans;
    }

    private static class Node implements Comparable<Node> {
        public int id;
        public Integer dist;

        public Node(int id, Integer dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist.compareTo(o.dist);
        }
    }
}
