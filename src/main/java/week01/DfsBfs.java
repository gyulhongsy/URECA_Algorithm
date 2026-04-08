package week01;

import java.io.*;
import java.util.*;

public class DfsBfs {
	static int N, M, V;
	static int graph[][];
	static boolean isVisited[];
	
	static StringBuilder sb = new StringBuilder();
	
	// 재귀를 사용하여 구현
	static void dfs(int v) {
		isVisited[v] = true;
		sb.append(v).append(" ");
		
		for (int node = 1; node <= N; node++) {
			if (graph[v][node] == 1 && !isVisited[node]) {
				dfs(node);
			}
		}
	}
	
	// 큐를 사용하여 구현
	static void bfs(int start) {
		Queue<Integer> que = new LinkedList<Integer>();
		
		que.add(start);
		isVisited[start] = true;
		
		while (!que.isEmpty()) {
			int v = que.poll();
			sb.append(v).append(" ");
			
			for (int node = 1; node <= N; node++) {
				if (graph[v][node] == 1 && !isVisited[node]) {
					que.offer(node);
					isVisited[node] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 그래프 초기화
		graph = new int[N + 1][N + 1]; // 편의상 배열의 크기를 +1 해줌
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 양방항 그래프이므로 둘 다 체크
			graph[a][b] = graph[b][a] = 1;
		}
		
		isVisited = new boolean[N + 1];
		dfs(V);
		sb.append("\n");
		
		isVisited = new boolean[N + 1];
		bfs(V);
		
		System.out.println(sb);
		
	}

}
