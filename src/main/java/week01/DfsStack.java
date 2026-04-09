package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DfsStack {
	
	static int N, M, V;
	static int graph[][];
	static boolean isVisited[];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int start) {
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		
		while (!stack.isEmpty()) {
			int v = stack.pop();
			
			if (!isVisited[v]) {
				isVisited[v] = true;
				sb.append(v).append(" ");
				
				for (int next = N; next > 0; next--) {
					if (graph[v][next] == 1 && !isVisited[next]) {
						stack.push(next);
					}
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
		
		System.out.println(sb);
		
	}
	
}
