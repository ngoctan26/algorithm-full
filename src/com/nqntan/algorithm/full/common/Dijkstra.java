package com.nqntan.algorithm.full.common;

import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	
	public static int[] dist;
	public static int[] path;
	private List<List<Node>> graph;
	
    public Dijkstra(List<List<Node>> graph) {
    	this.graph = graph;
    }
    
	public static class Node implements Comparable<Node> {
		public Integer id;
		public Integer dist;
		public Node(int id, int dist) {
			this.id = id;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return this.dist.compareTo(o.dist);
		}
	}
	
	public void dijkstra(int s) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int n = graph.size();
		dist = new int[n];
		path = new int[n];
		for (int i =0; i <n; i++) {
			dist[i]= Integer.MAX_VALUE;
			path[i] = -1;
		}
		queue.add(new Node(s, 0));
		dist[s] = 0;
		while (!queue.isEmpty()) {
			Node top = queue.poll();
			int u = top.id;
			int w = top.dist;
			List<Node> uList = graph.get(u);
			for(int i =0; i<uList.size();i++) {
				Node neighbor = uList.get(i);
				if( w + neighbor.dist < dist[neighbor.id]) {
					dist[neighbor.id] = w + neighbor.dist;
					queue.add(new Node(neighbor.id, dist[neighbor.id]));
					path[neighbor.id] = u;
				}
			}
		}
	}

}
