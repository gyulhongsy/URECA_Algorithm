# 순열

## 순열의 개념
서로 다른 n개 중에서 r개를 **순서 있게** 나열
```
nPr = n! / (n-r)!
```

## 순열 구현 로직
**각 자리에 들어갈 값을 하나씩 선택하면서 이미 사용한 값은 다시 선택하지 않도록 관리** <br>
=> 순열을 담을 배열을 만들어 재귀를 통해 한 자리씩 값을 채워나가고, 원소의 사용 여부는 따로 배열을 만들어 관리한다.

# 순열 문제 풀이(1) - 백준 10974번 모든 순열

## 문제
N이 주어졌을 때, 1부터 N까지의 수로 이루어진 순열을 사전순으로 출력하는 프로그램을 작성하시오.

## 입력
첫째 줄에 N(1 ≤ N ≤ 8)이 주어진다. 

## 출력
첫째 줄부터 N!개의 줄에 걸쳐서 모든 순열을 사전순으로 출력한다.

## 예제 입력, 출력
<img width="1182" height="284" alt="Image" src="https://github.com/user-attachments/assets/6649dbed-5374-45a4-bdf1-33cd5cf4a4e4" />

## 정답 코드 (java)
```
import java.util.Scanner;

public class Main {
	
	static int N;
	static int[] input;
	static int[] number;
	static boolean[] isSelected;
	
	private static void permutation(int idx) {
		if (idx >= N) {
			for (int i = 0; i < N; i++) {
				System.out.print(number[i] +" ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue;
			
			number[idx] = input[i];
			isSelected[i] = true;
			permutation(idx+1);
			isSelected[i] = false;
		}
	} //permutation

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		input = new int[N];
		number = new int[N];
		isSelected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = i+1;
		} //for - input
		
		permutation(0);
		
		scan.close();
	} //main

}
```

## 회고
- 원소들은 `input` 배열에 넣어 관리하였다.
- 추출한 조합은 `number` 배열에 넣어 관리하였다.
- 중복 방지를 위해 원소의 선택 여부를 `isSelected` 배열을 만들어 관리하였다.

## 알고리즘 분류
- 브루트포스 알고리즘
- 백트래킹
---
# 순열 문제 풀이 (2) - 백준 15649번 N과 M (1)

## 문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
- 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열

## 입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

## 출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 
중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

## 예제 입력, 출력
<img width="1166" height="610" alt="Image" src="https://github.com/user-attachments/assets/16442b1f-140d-4601-a849-0a938a875f71" />
<img width="1155" height="720" alt="Image" src="https://github.com/user-attachments/assets/7a2f1ab3-93b1-41ca-9756-25fa977d9a31" />

## 정답 코드 (java)
```
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int[] input;
	static int[] number;
	static boolean[] isSelected;
	
	private static void permutation(int idx) {
		if (idx >= M) {
			for (int i = 0; i < M; i++) {
				System.out.print(number[i] +" ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue;
			
			number[idx] = input[i];
			isSelected[i] = true;
			permutation(idx+1);
			isSelected[i] = false;
		}
	} //permutation

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		input = new int[N];
		number = new int[M];
		isSelected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = i+1;
		} //for - input
		
		permutation(0);
		
		scan.close();
	} //main

}
```

## 알고리즘 분류
- 백트래킹
---

# 순열 문제 풀이 (2) - 백준 15651번 N과 M (3)

## 문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
- 1부터 N까지 자연수 중에서 M개를 고른 수열
- 같은 수를 여러 번 골라도 된다.

## 입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)

## 출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 
중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

## 예제 입력, 출력
<img width="1153" height="704" alt="Image" src="https://github.com/user-attachments/assets/68cc970d-efb5-4b62-a9c2-09b7899adb65" />
<img width="1156" height="802" alt="Image" src="https://github.com/user-attachments/assets/b6d22b9b-910d-4534-abf5-42f97862d00b" />

## 정답 코드 (java)
```
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int[] input;
	static int[] number;
	static StringBuilder sb = new StringBuilder();
	
	private static void permutation(int idx) {
		if (idx >= M) {
			for (int i = 0; i < M; i++) {
				sb.append(number[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			number[idx] = input[i];
			permutation(idx+1);
		}
	} //permutation

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		input = new int[N];
		number = new int[M];
		
		for (int i = 0; i < N; i++) {
			input[i] = i+1;
		} //for - input
		
		permutation(0);
		System.out.println(sb.toString());
		
		scan.close();
	} //main

}
```

## 회고
- 중복이 허용되므로 요소의 선택 여부를 관리할 필요가 없어 `isSelected` 배열을 없앴다.
- 출력해야 할 수가 많아져 시간 초과 문제를 해결하기 위해 출력 부분을 `StringBuilder` 로 변경했다.

## 알고리즘 분류
- 백트래킹
