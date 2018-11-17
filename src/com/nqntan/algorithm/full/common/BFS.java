package com.nqntan.algorithm.full.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
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

0 1 5 
*/
public class BFS {
	private static List<Integer> path;
	private static List<Boolean> visited;
	private List<List<Integer>> graph;
	private int V;
	private int E;
	
	public BFS(int V, int E, List<List<Integer>> graph) {
		this.V = V;
		this.E = E;
		this.graph = graph;
		path = new ArrayList<>();
	}
	
	public void testBFS() {
		Scanner in = new Scanner(System.in);
		V = in.nextInt();
		E = in.nextInt();
		graph = new ArrayList<List<Integer>>();
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
		bfs(s);
		printPath(s, f);
	}

	public void printPath(int s, int f) {
		if (s == f) {
			System.out.println(s);
			return;
		}
		if (path.get(f) == -1) {
			System.out.println("No path");
			return;
		}
		List<Integer> b = new ArrayList<>();
		while (true) {
			b.add(f);
			f = path.get(f);
			if (s == f) {
				b.add(f);
				break;
			}
		}//end for
		for (int i = b.size() -1; i >= 0; i--) {
			System.out.print(b.get(i));
			System.out.print(" ");
		}
		
	}
	
	public void printPathRecursive(int s, int f) {
		if (s ==f) {
			System.out.print(f + " ");
		}else {
			if (path.get(f) == -1) {
				System.out.println("No path");
			}else {
				printPathRecursive(s, path.get(f));
				System.out.print(f + " ");
			}
		}
		
	}

	public void bfs(int s) {
		Queue<Integer> queue = new LinkedList<Integer>();
		path = new ArrayList<>();
		visited = new ArrayList<>();
		for (int i = 0; i <V; i++ ) {
			visited.add(false);
			path.add(-1);
		}
		visited.set(s, true);
		queue.add(s);
		while(!queue.isEmpty()) {
			int u = (int) queue.remove();
			List<Integer> uList = graph.get(u);
			for (int i =0; i< uList.size(); i++) {
				int v = uList.get(i);
				if (!visited.get(v)) {
					visited.set(v, true);
					path.set(v, u);
					queue.add(v);
				}
			}
		}
		
	}

}
