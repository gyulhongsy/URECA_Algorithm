# 시뮬레이션 (Simulation)

## 개념
- 문제에서 주어진 상황을 그대로 코드로 구현하는 알고리즘
- 정해진 규칙을 따라 상태를 계속 변화시키며 결과를 도출
- 복잡한 계산보다 정확한 구현이 중요

### 특징
- 알고리즘 이론보다는 구현 능력이 중요
- 조건, 규칙, 순서를 정확히 반영해야 함
- 실수(인덱스, 범위, 순서)가 발생하기 쉬움

### 왜 어려운가?
- 문제 길이가 길고 조건이 많음
- 작은 실수로 오답 발생
- 디버깅이 어려움

### 자주 하는 실수
- 범위 체크 안함
- 순서 틀림
- 상태 갱신 타이밍 오류
- 동시에 일어나야 하는 처리를 순차적으로 처리

### 해결 팁
- 문제를 단계별로 나누기
- 직접 손이나 그림으로 시뮬레이션 해보기
- 작은 입력으로 테스트 해보기

### 활용 문제 유형
- 격자 이동 (로봇, 상어, 뱀)
- 게임 구현
- 시간 흐름 문제
- 구현 문제

## 구현

### 흐름
1. 입력 받기
2. 상태 정의 (배열, 좌표 등)
3. 규칙 구현
4. 반복 수행
5. 결과 출력

### 자주 사용하는 요소
- 2차원 배열(격자)
- 방향 배열(dx, dy)
- 반복문 + 조건문
- 클래스 / 구조체 (상태 관리)
---

# 예제 문제 풀이 - 백준 16234번 인구 이동

## 문제
NxN크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다. 각각의 땅에는 나라가 하나씩 존재하며, 행 열에 있는 나라에는 A[r][c]명이 살고 있다. 인접한 나라 사이에는 국경선이 존재한다. 
모든 나라는 1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.

오늘부터 인구 이동이 시작되는 날이다.

인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
- 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
- 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
- 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
- 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
- 연합을 해체하고, 모든 국경선을 닫는다.
  
각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.

## 입력
첫째 줄에 N, L, R이 주어진다. (1≤N ≤ 50, 1≤ LR 100)

둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 열에 주어지는 정수는 A[r][c]의 값이다. (0≤ A[r][c] <100)

인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.

## 출력
인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력한다.

## 예제 입출력
<img width="1368" height="206" alt="Image" src="https://github.com/user-attachments/assets/55aad1d1-023d-4cf4-ba79-36b2e9ac1d2f" />
<img width="1370" height="479" alt="Image" src="https://github.com/user-attachments/assets/df925807-67b6-4c3f-b613-cc1b52882bee" />
<img width="1377" height="75" alt="Image" src="https://github.com/user-attachments/assets/e092ba2e-50b4-4043-b95c-b925527f48be" />
<img width="1373" height="459" alt="Image" src="https://github.com/user-attachments/assets/4d549a5d-8f05-4f5b-bcbb-0a792c51f627" />

## 정답 코드 (Java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, L, R;
	static int[][] nations;
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		nations = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				nations[i][j] = Integer.parseInt(st.nextToken());
			}
		} //for - nations
		
		int day = 0;
		
		while (true) {
			visited = new boolean[N][N];
			boolean moved = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						
						//bfs
						Queue<int[]> que = new ArrayDeque<>();
						ArrayList<int[]> union = new ArrayList<>();
						
						que.offer(new int[] {i, j});
						visited[i][j] = true;
						
						union.add(new int[] {i, j});
						int sum = nations[i][j];
						
						while (!que.isEmpty()) {
							int[] cur = que.poll();
							int x = cur[0];
							int y = cur[1];
							
							for (int d = 0; d < 4; d++) {
								int nx = x + dx[d];
								int ny = y + dy[d];
								
								if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
								if (visited[nx][ny]) continue;
								
								int diff = Math.abs(nations[x][y] - nations[nx][ny]);
								if (diff >= L && diff <= R) {
									visited [nx][ny] = true;
									que.offer(new int[] {nx, ny});
									union.add(new int[] {nx, ny});
									sum += nations[nx][ny];
								} //if - add union
							} //for - 상하좌우탐색
						} //while
						
						if (union.size() > 1) {
							int avg = sum / union.size();
							
							for (int[] pos : union) {
								nations[pos[0]][pos[1]] = avg;
							}
							moved = true;
						} //if - 인구이동 처리
					} //if - bfs
				} //for - j
			} //for - i
			
			if (!moved) break; //인구 이동이 더 이상 없으면 종료
			day++;
		} //while - count day
		
		System.out.println(day);
	} //main
}
```

## 회고
- 알고리즘보다는 구현 능력이 중요했다.
---
