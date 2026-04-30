# 위상 정렬 (Topological Sort)

## 개념
방향 그래프에서 노드들의 선후 관계를 고려하여 순서를 정렬하는 알고리즘

단, 사이클이 없는 그래프에서만 가능

### 왜 필요한가?
일반적인 정렬은 값의 크기를 기준으로 정렬하지만, 위상 정렬은 앞뒤 관계를 기준으로 순서를 정한다.

예시)
- 작업 순서
- 선수 과목
- 줄 세우기 문제

### 특징
- 방향 그래프 필요
- 사이클이 있으면 불가능
- 여러개의 정답 가능

## 구현
먼저 수행해야 하는 것 부터 정렬한다.

### 과정
1. 각 노드의 진입 차수 계산
2. 진입 차수 0인 노드를 큐에 삽입
3. 큐에서 하나 꺼내 결과에 추가
4. 연결된 노드의 진입 차수
5. 0이 되면 큐에 삽입
6. 반복

### DFS를 통해 구현할 수도 있다.
재귀+스택 사용
- DFS로 끝까지 탐색 후
- 돌아오면서 스택에 저장
---

# 예제 문제 풀이 -  백준 2252번 줄 세우기

## 문제
N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면 간단하겠지만, 마땅한 방법이 없어서 두 학생의 키를 비교하는 방법을 사용하기로 하였다.
그나마도 모든 학생들의 키를 비교해 본 것이 아니고, 일부 학생들의 키만을 비교해 보았다.

일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.

## 입력
첫째 줄에 N(1 <= N <= 32,000), M(1 <= M <= 100,000)이 주어진다. M은 키를 비교한 횟수이다. 다음 M개의 줄에는 키를 비교한 두 학생의 번호 A, B가 주어진다. 
이는 학생 A가 학생 B의 앞에 서야 한다는 의미이다.

학생들의 번호는 1번부터 N번이다.

## 출력
첫째 줄에 학생들을 앞에서부터 줄을 세운 결과를 출력한다. 답이 여러 가지인 경우에는 아무거나 출력한다.

## 예제 입출력
<img width="1187" height="370" alt="Image" src="https://github.com/user-attachments/assets/0c8881a5-c37e-4ba8-b029-5d1f02bbe287" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static ArrayList<Integer>[] graph;
	static int[] indegree;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()) + 1; //0번지 버림
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();
		indegree = new int[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			indegree[b]++;
		} //for
		
		Queue<Integer> que = new ArrayDeque<>();
		for (int i = 1; i < N; i++) {
			if (indegree[i] == 0) que.offer(i);
		} //for - prepare que
		
		StringBuilder sb = new StringBuilder();
		
		while (!que.isEmpty()) {
			int cur = que.poll();
			sb.append(cur).append(" ");
			
			for (int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				indegree[next]--;
				if (indegree[next] == 0) que.offer(next);
			} //for - insert nexts
		} //while
		
		System.out.println(sb);
	} //main
}
```
---
