package com.nqntan.algorithm.full;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://www.spoj.com/problems/MST/fbclid=IwAR3v6yNaXQtE64Ko6P612SV6JKdB9cal0Skdbr7rkEntd190DuOOkjBVV8w
 */
public class TestMST {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfNode = scanner.nextInt();
        int numberOfEdge = scanner.nextInt();
        int tmpCount = 0;
        List<List<Node>> graph = new ArrayList<>();
        while (tmpCount < numberOfNode) {
            graph.add(new ArrayList<>());
            tmpCount++;
        }
        tmpCount = 0;
        while (tmpCount < numberOfEdge) {
            int start = scanner.nextInt() - 1;
            int end = scanner.nextInt() - 1;
            int cost = scanner.nextInt();
            graph.get(start).add(new Node(end, cost));
            graph.get(end).add(new Node(start, cost));
            tmpCount++;
        }

        prim(0, graph);
    }

    private static void prim(int src, List<List<Node>> graph) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int n = graph.size();
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
            for (int i = 0; i < graph.get(u).size(); i++) {
                Node neighbor = graph.get(u).get(i);
                int v = neighbor.id;
                int w = neighbor.dist;
                if (!visited[v] && w < dist[v]) {
                    dist[v] = w;
                    queue.add(new Node(v, w));
                    path[v] = u;
                }
            }
        }
        System.out.println(getMST(dist, path));
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
