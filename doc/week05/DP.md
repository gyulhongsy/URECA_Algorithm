# 동적 프로그래밍 (Dynamic Programming)

## 개념
큰 문제를 작은 문제로 나누어 해결하고, 계산한 결과를 저장하여 재사용하는 알고리즘

### 왜 사용하는가?
일반 재귀는 같은 계산을 여러번 반복할 수 있다. 하지만 DP는 이미 계산한 값을 저장하여 중복 계산을 방지한다.

### DP VS 분할정복
| 구분 | 동적 계획법 (DP) | 분할정복 |
|------|------------------|----------|
| 공통점 | 큰 문제를 작은 문제로 나누어 해결 | 큰 문제를 작은 문제로 나누어 해결 |
| 중복 계산 | O | X |
| 결과 저장 | O (메모이제이션) | X |
| 특징 | 이전 결과 재사용 | 독립적으로 해결 |
| 대표 예시 | 피보나치, LIS, LCS | 병합 정렬, 퀵 정렬, 이진 탐색 |

### 대표 문제
- 피보나치 수열
- 계단 오르기
- 동전 문제
- 배낭 문제 (Knapsack)

## 구현

### 조건
- 중복 부분 문제: 같은 작은 문제가 반복해서 등장
- 최적 부분 구조: 작은 문제의 정답으로 큰 문제 해결 가능

### 문제 접근 방법
1. 작은 문제로 나눌 수 있는가?
2. 같은 계산이 반복되는가?
3. 상태(dp 배열)를 어떻게 정의할까?
4. 점화식을 만들 수 있는가?

### 점화식
DP에서는 현재 상태를 이전 상태로 표현하는 식이 중요하다. => 점화식 정의가 중요
- 피보나치의 점화식 예시
  ```
  dp[i] = dp[i-1] + dp[i-2]
  ```

### 구현 방식
1. Top-Down (Memoization)
   - 재귀 사용
   - 계산 결과 저장
2. Bottom-Up (Tabulation)
   - 작은 문제부터 반복문으로 계산
---

# 예제 문제 풀이 (1) - 백준 1436번 1로 만들기

## 문제
정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
2. X가 2로 나누어 떨어지면, 2로 나눈다.
3. 1을 뺀다.

정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.

## 입력
첫째 줄에 1보다 크거나 같고, 10<sup>6</sup>보다 작거나 같은 정수 N이 주어진다.

## 출력
첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

## 예제 입출력
<img width="756" height="173" alt="Image" src="https://github.com/user-attachments/assets/b71fbb4b-e876-470e-9108-771e6ffef771" />

## 힌트
10의 경우에 10 -> 9 -> 3 -> 1로 3번 만에 만들 수 있다.

## 정답 코드 (Java)
```
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		dp = new int[N+1]; //0번지 버림
		scan.close();
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = Math.min(dp[i], dp[i-1] + 1);			
			if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
			if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
		} //for - dp
		
		System.out.println(dp[N]);
	} //main
}
```

## 회고
- 처음에 동적 프로그래밍을 생각하지 못해서 단순히 각 조건에 따라 계산하는 식을 작성하였다.
- 그랬더니 최소 연산 횟수가 나오지 않아 동적 프로그래밍으로 로직을 다시 짜게 되었다.
- 동적 프로그래밍의 개념과 구현에 대한 이해를 할 수 있었다.
---

# 예제 문제 풀이 (2) - 백준 2579번 계단 오르기

## 문제
계단 오르기 게임은 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임이다. 
<그림 1>과 같이 각각의 계단에는 일정한 점수가 쓰여 있는데 계단을 밟으면 그 계단에 쓰여 있는 점수를 얻게 된다.
<img width="440" height="239" alt="Image" src="https://github.com/user-attachments/assets/47fbbe74-daac-4cde-9eb1-77795532011a" />
예를 들어 <그림 2>와 같이 시작점에서부터 첫 번째, 두 번째, 네 번째, 여섯 번째 계단을 밟아 도착점에 도달하면 총 점수는 10 + 20 + 25 + 20 = 75점이 된다.
<img width="412" height="248" alt="Image" src="https://github.com/user-attachments/assets/6685e455-2821-4a3d-98c0-a1b212d0cc8b" />
계단 오르는 데는 다음과 같은 규칙이 있다.
1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
2. 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
3. 마지막 도착 계단은 반드시 밟아야 한다.

따라서 첫 번째 계단을 밟고 이어 두 번째 계단이나, 세 번째 계단으로 오를 수 있다. 하지만, 첫 번째 계단을 밟고 이어 네 번째 계단으로 올라가거나, 
첫 번째, 두 번째, 세 번째 계단을 연속해서 모두 밟을 수는 없다.

각 계단에 쓰여 있는 점수가 주어질 때 이 게임에서 얻을 수 있는 총 점수의 최댓값을 구하는 프로그램을 작성하시오.

## 입력
입력의 첫째 줄에 계단의 개수가 주어진다.
둘째 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다. 
계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10,000이하의 자연수이다.

## 출력
첫째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값을 출력한다.

## 예제 입출력
<img width="1210" height="235" alt="Image" src="https://github.com/user-attachments/assets/279905fb-8cb7-4c6e-a920-93676675ecec" />

## 정답 코드 (Java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] stairs, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		stairs = new int[N+1];
		dp = new int[N+1];
		
		for (int i = 1; i <= N; i++) { //0번지 버림 (0번째 = 시작점 => 계단이 아님)
			stairs[i] = Integer.parseInt(br.readLine());
		} //for - insert stairs
		br.close();
		
		dp[1] = stairs[1];
		if (N >= 2) dp[2] = stairs[1] + stairs[2];
		if (N >= 3) dp[3] = Math.max(stairs[1] + stairs[3], stairs[2] + stairs[3]);
		
		for (int c = 4; c <= N; c++) { //계단은 3개 연속으로 오를 수 없음
			//전전 값과 현재 값의 합
			int a = dp[c-2] + stairs[c];
			
			//전전전 값, 전 값, 현재 값의 합
			int b = dp[c-3] + stairs[c-1] + stairs[c];
			
			dp[c] = Math.max(a, b);
		}//for - dp
		
		System.out.println(dp[N]);
	} //main
}
```
---

# 예제 문제 풀이 (3) - 백준 2294번 동전 2

## 문제
n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 원이 되도록 하고 싶다. 그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.

## 입력
첫째 줄에 n, k가 주어진다. (1≤n ≤ 100, 1 <k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 
동전의 가치는 100,000보다 작거나 같은 자연수이다. 가치가 같은 동전이 여러 번 주어질 수도 있다.

## 출력
첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.

## 예제 입출력
<img width="1204" height="203" alt="Image" src="https://github.com/user-attachments/assets/5d01806f-5418-4fd3-81c3-48b187879d75" />

## 정답 코드 (Java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, k;
	static int[] coins;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coins = new int[n];
		
		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		} //for - insert coins
		br.close();
		
		int[] dp = new int[k+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[0] = 0;
		for (int coin : coins) {
			for (int i = coin; i <= k; i++) {
				if (dp[i-coin] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i-coin] + 1);
				} //if - 만들 수 있는 경우에만
			}
		} //for - dp
		
		if (dp[k] == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(dp[k]);
	} //main
}
```

## 회고
- 지난번에 그리디 알고리즘에서 풀었던 동전 문제의 개선 버전과 동일하게 풀이했다. 
---

# 예제 문제 풀이 (4) - 백준 12865번 평범한 베낭

### 개념
제한된 무게 안에서 최대 가치를 구하는 문제

### 핵심 아이디어
현재 물건을 넣는다 / 안 넣는다 -> 두 경우를 비교하여 최댓값 선택

### 점화식
dp[i][w] = i번째 물건까지 고려했을 때 무게 w에서의 최대 가치

## 문제
이 문제는 아주 평범한 배낭에 관한 문제이다.

한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다. 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.

준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 
아직 행군을 해본적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.

## 입력
첫 줄에 물품의 수 N(1≤ N 100)과 준서가 버틸 수 있는 무게 K(1≤ K 100,000)가 주어진다. 
두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1≤W ≤ 100,000)와 해당물건의 가치 V(0 ≤ V < 1,000)가 주어진다.

입력으로 주어지는 모든 수는 정수이다.

## 출력
한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.

## 예제 입출력
<img width="966" height="184" alt="Image" src="https://github.com/user-attachments/assets/9e1c85bd-adb4-4557-badd-c7b9288172a8" />

## 정답 코드 (Java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[][] items;
	static int[][] dp;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "  ");
		
		N = Integer.parseInt(st.nextToken()); //물품의 수
		K = Integer.parseInt(st.nextToken()); //버틸 수 있는 무게
		
		items = new int[N+1][2]; //0번지 버림, 무게와 가치 2가지 저장
		dp = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		} //for - insert items
		br.close();
		
		for (int i = 1; i <= N; i++) {
			int curWeight = items[i][0];
			int curValue = items[i][1];
			
			for (int w = 1; w <= K; w++) {
				if (curWeight > w) { //현재 물건을 못 넣는 경우
					dp[i][w] = dp[i-1][w];
				} else { //현재 물건을 넣을 수 있는 경우
					dp[i][w] = Math.max(
							dp[i-1][w], //안 넣음
							dp[i-1][w-curWeight] + curValue //넣음
							//w-curWeight: 현재 넣은 물건 빼고 나머지 무게의 가치를 더함
					); //Math.max
				} //if ~ else
			} //for - w
		} //for - dp
		
		System.out.println(dp[N][K]);
	} //main
}
```

## 회고
- dp문제임에도 불구하고 처음에는 그리디 알고리즘처럼 가치가 높은 물건부터 넣으려고 했다.
- `dp[i-1][w-weight] + value` 의 의미를 이해하는 것이 어려웠지만 예제를 따라가며 이해할 수 있었다.
- 이전에 풀었던 동전 문제와 비슷하다고 생각했으나 배낭 문제는 물건을 한 번만 사용할 수 있다는 점에서 차이가 있었다.
- 따라서 현재 상태를 갱신할 때 `dp[i]` 가 아니라 `dp[i-1]` 을 사용한다는 점이 중요했다.
---
