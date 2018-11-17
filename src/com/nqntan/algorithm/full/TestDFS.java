package com.nqntan.algorithm.full;

import com.nqntan.algorithm.full.common.DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestDFS {

    /**
     * 6 8
     * 0 1
     * 0 3
     * 1 2
     * 1 3
     * 1 5
     * 2 5
     * 3 4
     * 3 5
     *
     * @param args
     */

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        List<Boolean> visited = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        // Number of node
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            graph.add(i, new ArrayList<>());
        }

        // Number of Edge
        int m = scanner.nextInt();
        int tmpCount = 0;
        while (tmpCount < m) {
            int startEdge = scanner.nextInt();
            int endEdge = scanner.nextInt();
            graph.get(startEdge).add(endEdge);

            tmpCount++;
        }


        DFS dfs = new DFS(n, visited, graph, path);
        dfs.dfsRecursion(0);
        List<Integer> pathRecorder = new ArrayList<>();
        dfs.printPath(0, 5, pathRecorder);
        System.out.println(pathRecorder);
    }
}
