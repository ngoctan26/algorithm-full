package com.nqntan.algorithm.full;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.nqntan.algorithm.full.common.Dijkstra;
/*

 6 10
 0 1 1
 1 2 5
 1 3 2 
 1 5 7
 2 5 1
 3 0 2
 3 2 1
 3 4 4
 4 3 3
 5 4 1
 
 Output: 6
 */
public class TesDijkstra {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int s = 0, t = 4;
		List<List<Dijkstra.Node>> graph = new ArrayList<>();
		for(int i =0; i <n; i++) {
			graph.add(new ArrayList<Dijkstra.Node>());
		}
		for (int j = 0; j < m; j++) {
			if (sc.hasNext()) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				int w = sc.nextInt();
				graph.get(u).add(new Dijkstra.Node(v, w));
			}
		}
		Dijkstra dijkstraInstance = new Dijkstra(graph);
		dijkstraInstance.dijkstra(s);
		System.out.println(Dijkstra.dist[t]);
	}

}
