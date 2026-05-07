# 분할 정복 (Divide and Conquer)

## 개념
큰 문제를 작은 문제로 나누어 해결한 뒤, 그 결과를 합쳐서 원래 문제를 해결하는 알고리즘

### 왜 사용하는가?
큰 문제를 한번에 해결하려고 하면 복잡하고 비효율적일 수 있다.

문제를 더 작은 단위로 나누면 구현이 쉬워지고, 시간 복잡도를 줄일 수 있다.

### 장점
- 문제를 단순화 가능
- 빠른 시간 복잡도
- 재귀 구조와 잘 어울림

### 단점
- 재귀 호출 비용 발생
- 스택 메모리 사용
- 구현이 어려울 수 있음

### 활용 문제 유형
- 정렬
- 탐색
- 큰 배열 처리
- 좌표 압축
- 행렬 계산

## 구현

### 구조
1. 문제를 작은 단위로 나눈다.
2. 작은 문제를 재귀적으로 해결한다.
3. 결과를 합친다.

### 대표 알고리즘
- 병합 정렬
  - 배열을 반으로 나눔
  - 각각 정렬
  - 다시 병함
- 퀵 정렬
  - pivot 기준 분할
  - 작은 값 / 큰 값 분리
- 이진 탐색: 정렬된 배열을 절반씩 탐색

---

# 예제 문제 풀이 (1) - 

## 문제 
아래 <그림 1>과 같이 여러개의 정사각형칸들로 이루어진 정사각형 모양의 종이가 주어져 있고, 각 정사각형들은 하얀색으로 칠해져 있거나 파란색으로 칠해져 있다. 
주어진 종이를 일정한 규칙에 따라 잘라서 다양한 크기를 가진 정사각형 모양의 하얀색 또는 파란색 색종이를 만들려고 한다.
<img width="205" height="213" alt="Image" src="https://github.com/user-attachments/assets/ae328f14-f4be-460e-856a-035b47213936" />
전체 종이의 크기가 NxN(N=2k, k는 1 이상 7 이하의 자연수) 이라면 종이를 자르는 규칙은 다음과 같다.

전체 종이가 모두 같은 색으로 칠해져 있지 않으면 가로와 세로로 중간 부분을 잘라서 <그림 2>의 I, II, III, IV와 같이 똑같은 크기의 네 개의 N/2 × N/2색종이로 나눈다. 
나누어진 종이 I, II, III, IV 각각에 대해서도 앞에서와 마찬가지로 모두 같은 색으로 칠해져 있지 않으면 같은 방법으로 똑같은 크기의 네 개의 색종이로 나눈다. 
이와 같은 과정을 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.
위와 같은 규칙에 따라 잘랐을 때 <그림 3>은 <그림 1>의 종이를 처음 나눈 후의 상태를, <그림 4>는 두 번째 나눈 후의 상태를, 
<그림 5>는 최종적으로 만들어진 다양한 크기의 9장의 하얀색 색종이와 7장의 파란색 색종이를 보여주고 있다.
<img width="631" height="513" alt="Image" src="https://github.com/user-attachments/assets/4a445f0a-2adb-4d37-994c-681f59070254" />
입력으로 주어진 종이의 한 변의 길이 N과 각 정사각형칸의 색(하얀색 또는 파란색)이 주어질 때 잘라진 하얀색 색종이와 파란색 색종이의 개수를 구하는 프로그램을 작성하시오

## 입력
첫째 줄에는 전체 종이의 한 변의 길이 N이 주어져 있다. N은 2, 4, 8, 16, 32, 64, 128 중 하나이다. 
색종이의 각 가로줄의 정사각형칸들의 색이 윗줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다. 
하얀색으로 칠해진 칸은 0, 파란색으로 칠해진 칸은 1로 주어지며, 각 숫자 사이에는 빈칸이 하나씩 있다.

## 출력
첫째 줄에는 잘라진 하얀색 색종이의 개수를 출력하고, 둘째 줄에는 파란색 색종이의 개수를 출력한다.

## 예제 입출력
<img width="1214" height="345" alt="Image" src="https://github.com/user-attachments/assets/06170001-5c5a-479a-858e-acbd971e1b60" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, white, blue;
	static int[][] paper;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		} //for - input paper
		br.close();
		
		divide(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
	} //main

	private static void divide(int x, int y, int size) {
		int firstValue = paper[x][y];
		boolean isSame = true;
		
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (paper[i][j] != firstValue) {
					isSame = false;
					break;
				} //if
			} //for - j
			if (!isSame) break;
		} //for - i
		
		if (isSame) {
			if (firstValue == 0) white++;
			else blue++;
			return;
		} //if - color
		
		divide(x, y, size/2);
		divide(x, y+size/2, size /2);
		divide(x+size/2, y, size/2);
		divide(x+size/2, y+size/2, size/2);
		
	} //divide
}
```
---

# 예제 문제 풀이 (2) - 백준 1997번 쿼드트리

## 문제
흑백 영상을 압축하여 표현하는 데이터 구조로 쿼드 트리(Quad Tree)라는 방법이 있다. 
흰 점을 나타내는 0과 검은 점을 나타내는 1로만 이루어진 영상(2차원 배열)에서 같은 숫자의 점들이 한 곳에 많이 몰려있으면, 쿼드 트리에서는 이를 압축하여 간단히 표현할 수 있다.

주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고, 모두 1로만 되어 있으면 압축 결과는 "1"이 된다. 
만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하게 되며, 
이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다.

<img width="414" height="191" alt="Image" src="https://github.com/user-attachments/assets/93b232e2-2835-4904-8bae-4799919e1e91" />

위 그림에서 왼쪽의 영상은 오른쪽의 배열과 같이 숫자로 주어지며, 이 영상을 쿼드 트리 구조를 이용하여 압축하면 " ((0011) (0(0111)01)1) "로 표현된다. 
N XN 크기의 영상이 주어질 때, 이 영상을 압축한 결과를 출력하는 프로그램을 작성하시오.

## 입력
첫째 줄에는 영상의 크기를 나타내는 숫자 N 이 주어진다. N은 언제나 2의 제곱수로 주어지며, 1≤N < 64의 범위를 가진다. 두 번째 줄부터는 길이 N의 문자열이 N개 들어온다. 
각 문자열은 0 또는 1의 숫자로 이루어져 있으며, 영상의 각 점들을 나타낸다.

## 출력
영상을 압축한 결과를 출력한다.

## 예제 입출력
<img width="1325" height="379" alt="Image" src="https://github.com/user-attachments/assets/0ec40fbf-cc83-4c41-a97d-933c64004fbb" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N, black, white;
	static int[][] video;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		video = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				video[i][j] = str.charAt(j) - '0';
			}
		} //for - input video
		br.close();
		
		System.out.println(divide(0, 0, N));
		
	} //main

	private static String divide(int x, int y, int size) {
		int firstValue = video[x][y];
		boolean isSame = true;
		
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (video[i][j] != firstValue) {
					isSame = false;
					break;
				} //if - isSame
			} // for - j
			if (!isSame) break;
		} //for - i
		
		if(isSame) return String.valueOf(firstValue);
		else return "(" + 
			divide(x, y, size/2) +
			divide(x, y+size/2, size/2) +
			divide(x+size/2, y, size/2) + 
			divide(x+size/2, y+size/2, size/2) +
			")";
		
	} //divide
}
```
---

# 예제 문제 풀이 (3) - 백준 1047번 Z

## 문제
<img width="1065" height="487" alt="Image" src="https://github.com/user-attachments/assets/5cde1d5e-a3fe-42e3-8c87-54ee51efb733" />
<img width="1056" height="534" alt="Image" src="https://github.com/user-attachments/assets/8dd8933a-d602-4942-883c-d28ee31b6393" />

## 입력
첫째 줄에 정수 N, r, c가 주어진다.

## 출력
r행 열을 몇 번째로 방문했는지 출력한다.

## 제한
- 1 ≤ N ≤ 15
- 0 ≤ r, c < 2<sup>N</sup>

## 예제 입출력
<img width="1104" height="773" alt="Image" src="https://github.com/user-attachments/assets/36db2ea7-28a1-4149-a1bf-b4bc86460c0e" />

## 정답 코드 (java)
```
import java.util.Scanner;

public class Main {
	
	static int N, r, c, cnt;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		r = scan.nextInt();
		c = scan.nextInt();
		scan.close();
		
		int size = (int)Math.pow(2, N);
		
		divide(r, c, size);
		
	} //main

	private static void divide(int r, int c, int size) {
		if (size == 1) {
			System.out.println(cnt);
			return;
		} //if - base case
		
		int half = size / 2;
		
		if (r < half && c < half) { //좌상
			divide(r, c, half);
		} else if (r < half && c >= half) { //우상
			cnt += half * half;
			divide(r, c - half, half);
		} else if (r >= half && c < half) { //좌하
			cnt += 2 * (half * half);
			divide(r - half, c, half);
		} else if (r >= half && c >= half) { //우하
			cnt += 3 * (half * half);
			divide(r - half, c - half, half);
		} //if ~ else
	} //divide
}
```
---
