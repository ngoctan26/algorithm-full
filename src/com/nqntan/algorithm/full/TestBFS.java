package com.nqntan.algorithm.full;

import com.nqntan.algorithm.full.common.BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestBFS {
	/*
	 
6 8
0 1
0 3
1 2
1 3
1 5
2 5
3 4
3 5
	 */
public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int V = in.nextInt();
	int E = in.nextInt();
	List<List<Integer>> graph = new ArrayList<List<Integer>>();
	for (int i = 0; i < V; i++) {
		graph.add(new ArrayList<>());
	}
	for (int i = 0; i < E; i++) {
		int u = in.nextInt();
		int vE = in.nextInt();
		graph.get(u).add(vE);
		graph.get(vE).add(u);
	}
	int s =0, f=5;
	BFS bfsInstance = new BFS(V, E, graph);
	bfsInstance.bfs(s);
	bfsInstance.printPathRecursive(s, f);
}
}
