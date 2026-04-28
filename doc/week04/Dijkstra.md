# 다익스트라 알고리즘 (Dijkstra)

## 개념
가중치가 있는 그래프에서 하나의 시작 정점에서 다른 모든 정점까지의 최단 거리를 구하는 알고리즘 (단, 음수 간선이 없어야 함)

### 왜 필요한가?
- BFS는 간선 비용이 모두 같을 때만 최단 거리 보장
- 다익스트라는 가중치가 다른 경우에도 최단 거리 계산 가능

### 특징
- 매우 빠름
- 모든 간선이 양수

### 활용 문제
- 최단 경로 / 최소 비용 경로
- 버스 노선 최단 거리
- 네트워크 전송 시간
- 지도 이동 문제

## 구현
### 핵심 아이디어
- 현재까지의 최단 거리 중 가장 작은 노드를 선택하여 확정하는 방식
- 가장 가까운 노드부터 확정하고, 한 번 확정된 거리는 다시 바뀌지 않음

### 동작 과정
1. 시작 노드 거리 = 0, 나머지 노드 거리는 무한대로 설정
2. 가장 가까운 노드 선택
3. 해당 노드를 거쳐 가는 거리 갱신
4. 반복

### 핵심 자료 구조
- 거리 배열 `dist[]`
- 방문 배열 `visited[]`
- 우선순위 큐 `Priority Queue`
---
# 예제 문제 풀이 (1) - 백준 1753번 최단 경로

## 문제
방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 단, 모든 간선의 가중치는 10 이하의 자연수이다.

## 입력
첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 
둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다. 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 
이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다. 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.

## 출력
첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.

## 예제 입출력
<img width="1150" height="303" alt="Image" src="https://github.com/user-attachments/assets/d36e4ef6-3e2f-40e1-83cc-d92cb7533bf1" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int to, weight;
		
		Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		} //Node
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		} //compareTo - 비용 오름차순
	} //class - Node

	static int V, E, K;
	static ArrayList<Node>[] graph;
	static int[] dist;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken()) + 1; //0번지 버림
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[V];
		dist = new int [V]; //0번지: 최소비용, 1번지: 직전노드
		for (int i = 1; i < V; i++) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, w));
		} //for - graph
		
		dijkstra();	
		
		br.close();
	} //main

	private static void dijkstra() {
		int INF = Integer.MAX_VALUE;
		
		Arrays.fill(dist, INF);
		dist[K] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(K, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			int now = cur.to;
			int cost = cur.weight;
			
			if (cost > dist[now]) continue;
			
			for (Node next : graph[now]) {
				if (dist[next.to] > dist[now] + next.weight) {
					dist[next.to] = dist[now] + next.weight;
					pq.offer(new Node(next.to, dist[next.to]));
				}
			} //for
		} //while
		
		for (int i = 1; i < V; i++) {
			if (dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		} //for - print		
	} //dijkstra
	
} //class
```

## 회고
- 처음에 graph의 자료구조를 그냥 정수형 이차원 배열로 작성했더니 메모리 초과가 발생하였다.
- 그래서 graph의 자료구조를 인접리스트로 바꿔서 다시 구현하였다.

## 알고리즘 분류
- 그래프 이론
- 최단 경로
- 다익스트라
---
# 예제 문제 풀이 (2) - 백준 4485번 녹색 옷 입은 애가 젤다지?

## 문제
젤다의 전설 게임에서 화폐의 단위는 루피(rupee)다. 그런데 간혹 '도둑루피'라 불리는 검정색 루피도 존재하는데, 이걸 획득하면 오히려 소지한 루피가 감소하게 된다!

젤다의 전설 시리즈의 주인공, 링크는 지금 도둑루피만 가득한 N x N 크기의 동굴의 제일 왼쪽 위에 있다. [0][0]번 칸이기도 하다. 
왜 이런 곳에 들어왔냐고 묻는다면 밖에서 사람들이 자꾸 "젤다의 전설에 나오는 녹색 애가 젤다지?"라고 물어봤기 때문이다. 
링크가 녹색 옷을 입은 주인공이고 젤다는 그냥 잡혀있는 공주인데, 게임 타이틀에 젤다가 나와있다고 자꾸 사람들이 이렇게 착각하니까 정신병에 걸릴 위기에 놓인 것이다.

하여튼 젤다...아니 링크는 이 동굴의 반대편 출구, 제일 오른쪽 아래 칸인 [N-1][N-1]까지 이동해야 한다. 
동굴의 각 칸마다 도둑루피가 있는데, 이 칸을 지나면 해당 도둑루피의 크기만큼 소지금을 잃게 된다. 
링크는 잃는 금액을 최소로 하여 동굴 건너편까지 이동해야 하며, 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.

링크가 잃을 수밖에 없는 최소 금액은 얼마일까?

## 입력
입력은 여러 개의 테스트 케이스로 이루어져 있다.

각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. (2 ≤ N ≤ 125) N = 0인 입력이 주어지면 전체 입력이 종료된다.

이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다. 
도둑루피의 크기가 k면 이 칸을 지나면 k루피를 잃는다는 뜻이다. 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.

## 출력
각 테스트 케이스마다 한 줄에 걸쳐 정답을 형식에 맞춰서 출력한다. 형식은 예제 출력을 참고하시오.

## 예제 입출력
<img width="1153" height="586" alt="Image" src="https://github.com/user-attachments/assets/b462430c-952e-43f8-b957-9fabc23ada2a" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] cave, money;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Node implements Comparable<Node> {
		int x, y, cost;
		
		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		} //Constructor
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	} //class - Node

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int test = 1;
		while (N != 0) {
			cave = new int[N][N];
			money = new int[N][N]; //(0,0)에서 각 칸 까지의 최소 비용을 저장하는 배열
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) cave[i][j] = Integer.parseInt(st.nextToken());
			} //for - cave
			
			dijkstra();
			System.out.println("Problem "+ test +": "+ money[N-1][N-1]);
			
			N = Integer.parseInt(br.readLine());
			test++;
		} //while
		br.close();
		return;
	} //main

	private static void dijkstra() {
		//비용 배열의 값을 최댓값으로 초기화
		for (int i = 0; i < N; i++) Arrays.fill(money[i], Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		money[0][0] = cave[0][0]; //첫 번째 칸에도 비용이 있음
		pq.offer(new Node(0, 0, money[0][0])); //첫 번째 칸(노드)을 큐에 넣음
		
		while (!pq.isEmpty()) {
			//검사할 현재 노드를 꺼냄
			Node cur = pq.poll();
			int x = cur.x, y = cur.y, cost = cur.cost;
			
			//현재 노드의 비용이 이미 더해진 비용보다 크면 무시
			if (cost > money[x][y]) continue;
			
			//현재 노드 기준으로 상하좌우 탐색
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				//탐색할 좌표가 경계를 넘어가면 무시
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
				int newCost = money[x][y] + cave[nx][ny];
				
				if (money[nx][ny] > newCost) {
					money[nx][ny] = newCost;
					pq.offer(new Node(nx, ny, newCost));
				} //if
			} //for
		} //while
	} //dijkstra
}
```

## 회고
- 이전에 풀었던 최단 경로 문제와 달리 입력 값의 크기가 작고, 2차원으로 값이 주어지기 때문에 굳이 인접 리스트를 사용할 필요가 없었다.
- 알고리즘 문제에서 클래스를 따로 만들어 푼 적이 거의 없었는데 이 문제를 풀어보면서 클래스를 활용해 볼 기회가 되었다.

## 알고리즘 분류
- 그래프 이론
- 그래프 탐색
- 최단 경로
- 다익스트라
- 격자 그래프
