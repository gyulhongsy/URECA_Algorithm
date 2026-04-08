## BFS & DFS 정리

### DFS (Depth-First Search): 깊이 우선 탐색

#### 특징
- 한 방향으로 끝까지 탐색한 후, 더 이상 갈 수 없으면 되돌아오는 방식
- 재귀 함수또는 스택(Stack)을 사용하여 구현
- 모든 경우의 수를 탐색하거나 경로를 찾는 문제에 적합

#### java에서 구현 (재귀 방식)
```
static void dfs(int v) {
		isVisited[v] = true;
		sb.append(v).append(" ");
		
		for (int node = 1; node <= N; node++) {
			if (graph[v][node] == 1 && !isVisited[node]) {
				dfs(node);
			}
		}
	}
```
---
### BFS (Breath-First Search): 너비 우선 탐색

#### 특징
- 가까운 노드부터 탐색하는 방식
- 큐(Queue)를 사용하여 구현
- 최단 거리 혹은 최소 이동 문제에 적합

#### java에서 구현
```
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
```
---
### 예제 적용 코드

#### 백준 1260번 DFS와 BFS
```
import java.io.*;
import java.util.*;

public class Main {
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
```
#### 회고
- dfs는 재귀를 사용하여 구현하였다.
- 방문 체크를 중복으로 하고 있지 않은지 확인이 필요했다.
- 백준의 런타임 에러를 방지하기 위하여 입력은 `BufferdReader`와 `StringTokenizer` 방식으로,<br>
  출력은 `StringBuilder` 방식으로 변경하였다.

