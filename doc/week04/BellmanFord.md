# 벨만-포드 알고리즘 (Bellman-Ford)

## 개념
- 가중치가 있는 그래프에서 하나의 시작 정점에서 다른 모든 정점까지의 최단 거리
- 다익스트라와 달리 음수 가중치 간선도 처리 가능
- 또한 음수 사이클 존재 여부까지 판별할 수 있음
- 음수 간선이 포함된 최단 경로 문제의 대표 알고리즘

### 왜 필요한가?
다익스트라는 현재 가장 짧은 거리를 가진 정점을 확정하는 방식이다. 하지만 음수 간선이 존재하면
```text
A -> B = 5
A -> C = 10
C -> B = -10
```
처음에는 B까지 최단 거리를 5로 확정했더라도
```
A -> C -> B = 0
```
더 짧은 경로가 뒤늦게 등장할 수 있다. 따라서 음수 간선이 있는 경우 다익스트라가 아닌 벨만-포드를 사용한다.

### 사용되는 문제 유형
- 음수 간선 포함 최단 경로
- 시간 여행 / 웜홀 문제
- 금융 손익 계산
- 음수 사이클 존재 여부 판별

## 구현

### 핵심 아이디어
1. 시작 노드의 최소 비용 = 0, 나머지 노드의 최소 비용은 무한대로 초기화
2. 노드 개수-1 만큼 최소 비용과 직전 노드 갱신
3. 마지막으로 2번 과정을 한번 더 수행하여 갱신되는 최소 비용이 있는지 확인 (음수 사이클 판별)

### 노드 개수-1 만큼 반복하는 이유
정점이 V개라면 최단 경로는 최대 V-1개의 간선만 사용하기 때문 => V-1번 반복으로도 충분

### 음수 사이클 판별
V-1번 반복 이후에도 거리 값이 줄어든다면 계속 비용을 줄일 수 있는 순환 구조가 존재한다는 뜻 => 음수 사이클 존재

---
# 예제 문제 풀이 - 백준 11657번 타임머신

## 문제
N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다. 
각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다. 
시간 C가 양수가 아닌 경우가 있다. C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.

1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.

## 입력
첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다. 
둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다. 

## 출력
만약 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력한다. 
그렇지 않다면 N-1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로 출력한다. 
만약 해당 도시로 가는 경로가 없다면 대신 -1을 출력한다.

## 예제 입출력
<img width="1152" height="667" alt="Image" src="https://github.com/user-attachments/assets/1c757a30-d7a7-4d75-bcc6-60df70b488ad" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class Edge {
		int from, to, cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		} //Constructor
		
		
	} //class - edge
	
	static int N, M;
	static ArrayList<Edge> graph = new ArrayList<>();
	static long[] time;
	static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()) + 1; //0번지 버림
		M = Integer.parseInt(st.nextToken());
		time = new long[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.add(new Edge(a, b, w));
		} //for - edges
		
		// bellmanFord() 결과가 true면 음수 사이클 존재 => -1 출력
		if (bellmanFord()) System.out.println(-1);
		
		else {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 2; i < N; i++) {
				//도달 불가능한 도시 (거리 무한대) => 못감
				if (time[i] == INF) sb.append(-1).append("\n");
				
				//갈 수 있으면 최단거리 출력
				else sb.append(time[i]).append("\n");
			} //for
			System.out.println(sb);
		} //else
				
		br.close();
	} //main

	private static boolean bellmanFord() { //true: 음수 사이클 있음, false: 음수 사이클 없음
		for (int i = 1; i < N; i++) time[i] = INF;
		time[1] = 0; //시작 도시
		
		for (int i = 1; i < N-1; i++) {
			for (Edge next : graph) {
				// 아직 출발점에서 못 가는 정점이면 패스
				if (time[next.from] == INF) continue;
				
				if (time[next.to] > time[next.from] + next.cost) {
					time[next.to] = time[next.from] + next.cost;
				} //if - 더 적게 걸리는 시간 계산
			} //for each - next
		} //for - 전체 간선을 N-1번 반복
		
		for (Edge next : graph) {
			// 출발지에서 도달 못한 곳은 무시
			if (time[next.from] == INF) continue;
			
			if (time[next.to] > time[next.from] + next.cost) {
				return true;
			} //if - N-1번 다 돌았는데 또 줄어듦 -> 음수 사이클 있음 => true 반환
		} //for-each - 음수 사이클 검사
		
		return false;
	} //bellmanFord
}
```

## 알고리즘 분류
- 그래프 이론
- 최단 경로
- 벨만-포드
