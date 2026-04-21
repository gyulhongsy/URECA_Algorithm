# 조합

## 조합의 개념
- n개의 원소 중에서 **순서를 고려하지 않고** r개를 선택
  ```
  nCr = n! / (r! * (n - r)!)
  ```
- 순서가 다르면 같은 경우로 취급
- 예) {1, 2, 3}에서 2개 선택 -> (1, 2), (1, 3), (2, 3) / (2, 1) 등은 같은 취급

## 조합 구현 로직
현재 원소를 선택했다면 현재 원소의 다음 원소부터 선택

# 조합 문제 풀이 (1) - 백준 15650번 N과 M (2)

## 문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
- 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
- 고른 수열은 오름차순이어야 한다.

## 입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

## 출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다. <br>
수열은 사전 순으로 증가하는 순서로 출력해야 한다.

## 예제 입출력
<img width="1149" height="587" alt="Image" src="https://github.com/user-attachments/assets/b30af361-5874-4f67-a572-633d4c0dfb37" />

## 정답 코드 (java)
```
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int[] input;
	static int[] number;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		input = new int[N];
		number = new int[M];
		
		for (int i = 0; i < N; i++) {
			input[i] = i+1;
		} //for - input
		scan.close();
		
		combination(0, 0);
		
	} //main
	
	private static void combination(int numI, int inI) {
		if (numI >= M) {
			for (int i = 0; i < M; i++) System.out.print(number[i] +" ");
			System.out.println();
			return;
		} //if
		
		for (int i = inI; i < N; i++) {
			number[numI] = input[i];
			combination(numI+1, i+1);
		} //for
	} //combination
}
```
## 회고
- 순열과 다르게 재귀함수의 매개변수에서 현재 뽑은 원소의 인덱스+1도 함께 넘겨주어야 했다.

## 알고리즘 분류
- 백트래킹
---
# 조합 문제 풀이 (2) -  백준 15652번 N과 M (4)

## 문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
- 1부터 N까지 자연수 중에서 M개를 고른 수열
- 같은 수를 여러 번 골라도 된다.
- 고른 수열은 비내림차순이어야 한다.
  - 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.

## 입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

## 출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다. <br>
수열은 사전 순으로 증가하는 순서로 출력해야 한다.

## 에제 입출력
<img width="1147" height="547" alt="Image" src="https://github.com/user-attachments/assets/ad72e374-ba4f-48fa-9f81-5f0cacc79e22" />
<img width="1151" height="358" alt="Image" src="https://github.com/user-attachments/assets/45e49459-bb6e-407c-b646-14ce3643e862" />

## 정답 코드 (java)
```
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int[] input;
	static int[] number;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		input = new int[N];
		number = new int[M];
		
		for (int i = 0; i < N; i++) {
			input[i] = i+1;
		} //for - input
		scan.close();
		
		combination(0, 0);
		
	} //main
	
	private static void combination(int numI, int inI) {
		if (numI >= M) {
			for (int i = 0; i < M; i++) System.out.print(number[i] +" ");
			System.out.println();
			return;
		} //if
		
		for (int i = inI; i < N; i++) {
			number[numI] = input[i];
			combination(numI+1, i);
		} //for
	} //combination
}
```

## 회고
- 기존의 조합과 다르게 중복이 허용되므로 현재 선택한 인덱스에 +1을 할 필요가 없었다.

## 알고리즘 분류
- 백트래킹
---
# 조합 문제 풀이 (3) - 백준 3040번 백설공주와 일곱 난쟁이

## 문제
매일 매일 일곱 난쟁이는 광산으로 일을 하러 간다. 난쟁이가 일을 하는 동안 백설공주는 그들을 위해 저녁 식사를 준비한다. 
백설공주는 의자 일곱개, 접시 일곱개, 나이프 일곱개를 준비한다. <br>
어느 날 광산에서 아홉 난쟁이가 돌아왔다. (왜 그리고 어떻게 아홉 난쟁이가 돌아왔는지는 아무도 모른다) 
아홉 난쟁이는 각각 자신이 백설공주의 일곱 난쟁이라고 우기고 있다. <br>
백설공주는 이런 일이 생길 것을 대비해서, 난쟁이가 쓰고 다니는 모자에 100보다 작은 양의 정수를 적어 놓았다. 
사실 백설 공주는 공주가 되기 전에 매우 유명한 수학자였다. 따라서, 일곱 난쟁이의 모자에 쓰여 있는 숫자의 합이 100이 되도록 적어 놓았다. <br>
아홉 난쟁이의 모자에 쓰여 있는 수가 주어졌을 때, 일곱 난쟁이를 찾는 프로그램을 작성하시오. (아홉 개의 수 중 합이 100이 되는 일곱 개의 수를 찾으시오)

## 입력
총 아홉개 줄에 1보다 크거나 같고 99보다 작거나 같은 자연수가 주어진다. 모든 숫자는 서로 다르다. 또, 항상 답이 유일한 경우만 입력으로 주어진다.

## 출력
일곱 난쟁이가 쓴 모자에 쓰여 있는 수를 한 줄에 하나씩 출력한다.

## 예제 입출력
<img width="1146" height="676" alt="Image" src="https://github.com/user-attachments/assets/5f32d88e-4b9b-4760-89b2-a78c40ca5b79" />

## 정답 코드 (java)
```
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int[] dwarfs;
	static int[] real;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		dwarfs = new int[9];
		real = new int[7];
		
		for (int i = 0; i < 9; i++) dwarfs[i] = scan.nextInt();
		scan.close();
		
		combination(0, 0);
		
	} //main
	
	private static void combination(int ri, int di) {
		if (ri >= 7) {
			int sum = Arrays.stream(real).sum();
			
			if (sum == 100) {
				for (int i = 0; i < 7; i++) {
					System.out.println(real[i]);
				}
			}
			return;
		} //if - 탈출조건
		
		for (int i = di; i < 9; i++) {
			real[ri] = dwarfs[i];
			combination(ri+1, i+1);
		} //for
	}
}
```
## 회고
- 난쟁이를 선택할 때 9개 중 7개 원소로 조합을 만드는 방법을 선택하였다.

## 알고리즘 분류
- 브루트포스 알고리즘
