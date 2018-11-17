package com.nqntan.algorithm.full;

import com.nqntan.algorithm.full.common.BellmanFord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestBellmanFord {

    /**
     * 6 10
     * 0 1 1
     * 1 2 5
     * 1 3 -2
     * 1 5 7
     * 2 5 -1
     * 3 0 2
     * 3 2 -1
     * 3 4 4
     * 4 3 3
     * 5 4 1
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Numbers of node
        int n = scanner.nextInt();
        // Numbers of edge
        int m = scanner.nextInt();

        int[] dist = new int[n];
        int[] path = new int[n];

        BellmanFord bellmanFord = new BellmanFord(n, m, dist, path, scanner);
        boolean isNotContainsNegativeCircle = bellmanFord.isNotContainedNegativeCircle();
        if (!isNotContainsNegativeCircle) {
            System.out.println("Contain negative circle");
            return;
        }
        bellmanFord.runBellmanFord(0);
        System.out.println(Arrays.toString(path));
        System.out.println(Arrays.toString(dist));

        List<Integer> pathRecorder = new ArrayList<>();
        bellmanFord.printPath(0, 4, pathRecorder);
        System.out.println(pathRecorder);
        System.out.println(dist[4]);
    }
}
