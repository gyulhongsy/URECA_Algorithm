package week01;

import java.io.*;
import java.util.*;

public class DfsBfs {
	static int N, M, V;
	static ArrayList<Integer> graph[];
	static boolean isVisited[];
	
	// 재귀함수 사용하여 구현
	static void DFS(int v) {
		if (isVisited[v] == false) {
			System.out.print(v + " ");
			isVisited[v] = true;
		}
		
		for (int i = 0; i < graph[v].size(); i++) {
			if (isVisited[graph[v].get(i)]) continue;
			DFS(graph[v].get(i));
		}
	} // DFS

	static void BFS(int root) {
		Queue<Integer> que = new ArrayDeque<Integer>();
		
		que.add(root);
		isVisited[root] = true;
		
		while (!que.isEmpty()) {
			int node = que.poll();
			System.out.print(node+" ");
			
			for (int i = 0; i < graph[node].size(); i++) {
				if (isVisited[graph[node].get(i)] == true) continue;
				que.add(graph[node].get(i));
				isVisited[graph[node].get(i)] = true;
			}
		}
	} // BFS
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 그래프 초기화
		graph = new ArrayList[N+1];
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			// 노드의 양쪽 노드 값을 저장
			graph[v1].add(v2);
			graph[v2].add(v1);
		}
		
		isVisited = new boolean[N+1];
		DFS(V);
		System.out.println();
		
		isVisited = new boolean[N+1];
		BFS(V);
		
	} // main

}
