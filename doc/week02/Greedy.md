# Greedy Algorithm (그리디 알고리즘)

## 개념과 특징
- 탐욕(greedy) 알고리즘
- 매 순간 가장 최선의 선택을 하는 알고리즘
- 전체 최적해를 구하기 위해 현재 최적의 선택을 반복
- 지금 당장 가장 좋아 보이는 것을 선택 => 항상 정답을 보장하지는 않음
- 빠르고 구현이 간단함
- 일정한 공식 없이 창의성을 요구하는 유형

## 적용 조건
1. 탐욕적 선택 속성: 현재 선택이 이후 선택에 영향을 주지 않음
2. 최적 부분 구조: 부분 문제의 최적해가 전체 최적해로 이어짐

## 대표 문제 유형
- 거스름돈 문제
- 회의실 배정
- 최소 동전 개수
- 배낭 문제의 일부 케이스
---
# 회의실 배정 - 백준 문제 풀이

## 문제
한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다. 
각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자. 
단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 
회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.

## 입력
첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다. 
둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.
시작 시간과 끝나는 시간은 231-1보다 작거나 같은 자연수 또는 0이다.

## 출력
첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.

## 알고리즘 분류
- 그리디 알고리즘
- 정렬

## 예제 입력 1
```
11
1 4
3 5
0 6
5 7
3 8
5 9
6 10
8 11
8 12
2 13
12 14
```

## 예제 출력 1
```
4
```

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		time = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			time[i] = new int[] {Integer.parseInt(st.nextToken()), 
								Integer.parseInt(st.nextToken())};			
		} // for - initialize time
		
		Arrays.sort(time, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) return o1[0] - o2[0]; //회의 종료 시간이 같으면 시작 시간 비교
				else return o1[1] - o2[1]; //회의 종료 시간을 기준으로 비교
			}
		}); // Arrays.sort
		
		int cnt = 0;
		int prev_end_time = 0;
		for (int i = 0; i < N; i++) {
			if (prev_end_time <= time[i][0]) {
				prev_end_time = time[i][1];
				cnt++;
			}
		} // for
		
		System.out.println(cnt);
		br.close();
	} // main
}
```

## 회고 
- 이 문제를 풀며 그리디 알고리즘의 개념을 처음 정리하게 되었다.
- 그리디 알고리즘을 구현하기 위해서 문제마다 정렬 기준을 세워야 한다.
- 정렬은 `Comparator`인터페이스를 재정의하여 구현한다. (람다식 사용)
