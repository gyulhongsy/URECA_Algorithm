# 최소 신장 트리 (Minimum Spanning Tree)

## 개념
그래프의 모든 정점을 연결하면서 사이클이 발생하지 않고 간선 비용의 합이 최소인 트리

### 왜 필요한가?
모든 정점을 최소 비용으로 연결해야 하는 문제에서 사용

- 도시 전기 연결
- 네트워크 구축
- 도로 연결

## 크루스칼 (Kruscal Algorithm)

### 개념
- 비용이 작은 간선부터 선택하는 방식
- 사이클이 생기지 않으면 선택
- 간선 중심 알고리즘

### 특징
- 간선 적을 때 유리
- Union-Find 필수
- 구현 비교적 간단

### 동작 과정
1. 간선을 비용 기준 정렬
2. 가장 작은 간선 선택
3. 사이클 확인
4. 가능하면 연결
5. 반복

## 프림 (Prim Algorithm)

### 개념
- 하나의 정점에서 시작
- 가장 가까운 정점을 계속 연결
- 정점 중심 알고리즘

### 특징
- 정점 많고 간선 많은 경우 유리
- 다익스트라와 유사한 구조
- PriorityQueue 사용

### 동작 과정
1. 시작 정점 선택
2. 연결 가능한 간선 중 최소 비용 선택
3. 새로운 정점 추가
4. 반복

---

# 크루스칼 예제 문제 풀이 - 백준 1197번 최소 스패닝 트리

## 문제
그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.

최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.

## 입력
첫째 줄에 정점의 개수 V(1 <V < 10,000)와 간선의 개수 E(1E100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 
이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.

그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다. 
최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.

## 출력
첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.

## 예제 입출력
<img width="1328" height="231" alt="Image" src="https://github.com/user-attachments/assets/2b9ff3e3-dac3-437d-873d-31dcafe9e6e6" />

## 정답 코드 (Java)
```
import java.io.*;
import java.util.*;

// MST, Kruskal, Sort, union-find
public class Main {

	static int V, E; // 정점의 수, 간선의 수
	static ArrayList<Edge> edges = new ArrayList<>();
	static int[] parent;
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		} //Constructor
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		} //compareTo
	} //Edge

	public static void main(String[] args) throws Exception {
		BufferedReader bufRead = new BufferedReader( new InputStreamReader( System.in ) );
		StringTokenizer token = new StringTokenizer( bufRead.readLine(), " " );

		V = Integer.parseInt(token.nextToken());
		E = Integer.parseInt(token.nextToken());
		
		parent = new int[V+1];
		for (int i = 1; i <= V; i++) parent[i] = i;

		for (int i = 0; i < E; i++) {
			token = new StringTokenizer( bufRead.readLine(), " " );
			int f = Integer.parseInt( token.nextToken() );
			int t = Integer.parseInt( token.nextToken() );
			int w = Integer.parseInt( token.nextToken() );
			
			edges.add(new Edge(f, t, w));
		} // for
		bufRead.close();
		
		Collections.sort(edges);
		
		int total = 0;
		int cnt = 0;
		
		for (Edge e : edges) {
			if (find(e.from) != find(e.to)) {
				union(e.from, e.to);
				total += e.weight;
				cnt++;
			} //if
			
			if (cnt == V - 1) break; //모든 정점이 이어짐
		} //for
		
		System.out.println(total);
		
	} // main

	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	} //find

	private static void union(int from, int to) {
		int a = find(from);
		int b = find(to);
		
		if (a != b) parent[b] = a;
	} //union

} // class
```
---

# 프림 예제 문제 풀이 - 백준 1992번 네트워크 연결

## 문제
도현이는 컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축하려 한다. 하지만 아쉽게도 허브가 있지 않아 컴퓨터와 컴퓨터를 직접 연결하여야 한다. 
그런데 모두가 자료를 공유하기 위해서는 모든 컴퓨터가 연결이 되어 있어야 한다. 
(a와 b가 연결이 되어 있다는 말은 a에서 b로의 경로가 존재한다는 것을 의미한다. a에서 b를 연결하는 선이 있고, b와 c를 연결하는 선이 있으면 a와 는 연결이 되어 있다.)

그런데 이왕이면 컴퓨터를 연결하는 비용을 최소로 하여야 컴퓨터를 연결하는 비용 외에 다른 곳에 돈을 더 쓸 수 있을 것이다. 
이제 각 컴퓨터를 연결하는데 필요한 비용이 주어졌을 때 모든 컴퓨터를 연결하는데 필요한 최소비용을 출력하라. 모든 컴퓨터를 연결할 수 없는 경우는 없다.

## 입력
첫째 줄에 컴퓨터의 수 N (1≤N≤ 1000)가 주어진다.

둘째 줄에는 연결할 수 있는 선의 수 M (1≤ M≤ 100,000)가 주어진다.

셋째 줄부터 M+2번째 줄까지 총 M개의 줄에 각 컴퓨터를 연결하는데 드는 비용이 주어진다. 
이 비용의 정보는 세 개의 정수로 주어지는데, 만약에 abc가 주어져 있다고 하면a컴퓨터와 b컴퓨터를 연결하는데 비용이 c (1≤c ≤ 10,000) 만큼 든다는 것을 의미한다. a와 b는 같을 수도 있다.


## 출력
모든 컴퓨터를 연결하는데 필요한 최소비용을 첫째 줄에 출력한다.

## 예제 입출력
<img width="1209" height="392" alt="Image" src="https://github.com/user-attachments/assets/984d7024-fbda-4a5d-bfc4-a7f83e1997e2" />

## 힌트
이 경우에 1-3, 2-3, 3-4, 4-5, 4-6을 연결하면 주어진 output이 나오게 된다.

## 정답 코드 (Java)
```
import java.io.*;
import java.util.*;

// MST, Prim, PriorityQueue
public class Main {

	static class Edge implements Comparable<Edge>{
		int node, cost;

		Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	} // class

	static List<Edge>[] graph;
	static boolean[] isVisited;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		StringTokenizer st = null;

		int V = Integer.parseInt(br.readLine()); // 정점 수
		int E = Integer.parseInt(br.readLine()); // 간선 수

		graph = new ArrayList[V+1]; // 그래프 초기화 (인접 리스트) - 0번지 버림
		for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>(); // for

		for (int i = 0; i < E; i++) { // 간선 입력 (양방향)
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		} // for

		isVisited = new boolean[V+1];

		int totalCost = 0; //최소 비용
		int usedNodes = 0; //계산에 사용된 간선 수 -> V-1이면 탈출

		// PriorityQueue
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(1,0));
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (isVisited[cur.node]) continue;
			
			isVisited[cur.node] = true;
			totalCost += cur.cost;
			usedNodes++;
			
			if (usedNodes == V) break;
			
			for (Edge n : graph[cur.node]) {
				if (!isVisited[n.node]) {
					pq.add(new Edge(n.node, n.cost));
				} //if
			} //for
		} //while

		System.out.println(totalCost);
	} // main

} // class
```
---
