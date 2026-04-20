# 재귀

## 개념
- 함수가 자기 자신을 호출하는 방식
- 반복문을 사용하지 않고 문제를 해결할 수 있음
- 문제를 더 작은 문제로 나누어 해결하는 방식

### 재귀의 구성 요소
1. 기저 조건 (Base Case): 재귀를 멈추는 조건
2. 재귀 호출 (Recursive Call): 문제를 더 작은 단위로 나누어 호출 <br>
-> 이 두 가지가 없으면 무한 호출 발생

### 대표 예시 - 팩토리얼
``` java
public static int factorial(int n) {
  if (n == 1) return 1; //base call
  return n * factorial(n-1); //recursive call
```
---

# 재귀 예제 문제 - 백준 1914번 하노이 탑

## 문제
세 개의 장대가 있고 첫 번째 장대에는 반경이 서로 다른 n개의 원판이 쌓여 있다. 각 원판은 반경이 큰 순서대로 쌓여있다.
이제 수도승들이 다음 규칙에 따라 첫 번째 장대에서 세 번째 장대로 옮기려 한다.

1. 한 번에 한 개의 원판만을 다른 탑으로 옮길 수 있다.
2. 쌓아 놓은 원판은 항상 위의 것이 아래의 것보다 작아야 한다.

이 작업을 수행하는데 필요한 이동 순서를 출력하는 프로그램을 작성하라. 단, 이동 횟수는 최소가 되어야 한다. <br>
아래 그림은 원판이 5개인 경우의 예시이다.
<img width="1103" height="222" alt="Image" src="https://github.com/user-attachments/assets/45ba1905-f2e8-4362-b30e-69a324d9d6bc" />

## 입력
첫째 줄에 첫 번째 장대에 쌓인 원판의 개수 N (1 ≤ N ≤ 100)이 주어진다.

## 출력
첫째 줄에 옮긴 횟수 K를 출력한다.

N이 20 이하인 입력에 대해서는 두 번째 줄부터 수행 과정을 출력한다. 
두 번째 줄부터 K개의 줄에 걸쳐 두 정수 A B를 빈칸을 사이에 두고 출력하는데, 이는 A번째 탑의 가장 위에 있는 원판을 B번째 탑의 가장 위로 옮긴다는 뜻이다. 
N이 20보다 큰 경우에는 과정은 출력할 필요가 없다.

## 예제 입출력
<img width="1153" height="308" alt="Image" src="https://github.com/user-attachments/assets/bb440c73-eafc-41ca-9098-eea6e7c3fae5" />

## 정답 코드 (java)
```
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	
	static int N;
	static BigInteger K;
	static StringBuilder move = new StringBuilder();
	
	static void hanoi(int no, int start, int to) {
		if (no == 1) {
			move.append(start).append(" ").append(to).append("\n");
			return;
		}
		
		if (no > 1) hanoi(no - 1, start, 6-start-to);
		move.append(start).append(" ").append(to).append("\n");
		if (no > 1) hanoi(no - 1, 6-start-to, to);
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		K = new BigInteger("2").pow(N).subtract(BigInteger.ONE);
		System.out.println(K);
		
		if (N <= 20) {
			hanoi(N, 1, 3);
			System.out.println(move.toString());
		}
		
		scan.close();
	}

}
```

## 회고
- n개의 원판이 있을 때 m-1개의 원판을 한 그룹으로 보고, 이들을 전부 옮겨야 n번째 원판을 옮길 수 있다. 이를 재귀적으로 구현하였다.
- 재귀함수의 매개 변수는 원판의 수, 시작 시점, 이동할 지점 이렇게 정해주었다.
- 직접 하노이의 탑 게임을 해보니 어떻게 동작해야 하는지 이해하기 쉬웠다.
- [하노이의 탑 게임 링크](https://www.ebsmath.co.kr/innovativelrms/web_lrms/content/resource/PC/28320/index.html?check=false&PKG_SNO=&PKG_ORG_NO=&RSC_SNO=28320&EVT_SNO=0&HIDE_RANK=N&HISTORY_TYPE=study&DEVICE_TYPE=MOB)

## 알고리즘 분류
- 임의 정밀도 / 큰 수 연산
- 재귀
