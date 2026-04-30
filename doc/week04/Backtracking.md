# 백트래킹 (Backtracking)

## 개념
- 모든 경우를 탐색하는 DFS 기반 알고리즘
- 조건에 맞지 않는 경우는 더 이상 탐색하지 않고 되돌아감
- 완전 탐색 + 가지치기

### 왜 필요한가?
완전 탐색(브루트 포스)은 모든 경우를 다 확인하기 때문에 비효율적이다. <br>
백트래킹은 정답이 될 수 없는 경우를 미리 제거하여 탐색 시간을 줄인다.

### 구현
가능한 경우만 탐색하고, 불가능한 경우는 즉시 중단
1. 선택
2. 재귀 호출
3. 원상 복구

위의 3단계가 반드시 필요

---
# 예제 문제 풀이 (1) - 백준 2580 스도쿠

## 문제
스도쿠는 18세기 스위스 수학자가 만든 '라틴 사각형'이란 퍼즐에서 유래한 것으로 현재 많은 인기를 누리고 있다. 
이 게임은 아래 그림과 같이 가로, 세로 각각 9개씩 총 81개의 칸으로 이루어진 정사각형 판 위에서 이뤄지는데, 게임 시작 전 일부 칸에는 1부터 9까지의 숫자 중 하나가 쓰여있다.
<img width="248" height="238" alt="Image" src="https://github.com/user-attachments/assets/8f09a986-c41e-4175-8429-ee29de4ae443" />
나머지 빈 칸을 채우는 방식은 다음과 같다.
1. 각각의 가로줄과 세로줄에는 1부터 9까지 숫자가 한 번씩만 나타나야 한다.
2. 굵은 선으로 구분되어 있는 3x3 정사각형 안에도 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.

위의 예의 경우, 첫째 줄에는 1을 제외한 나머지 2부터 9가지의 숫자들이 이미 나타나 있으므로 첫째 줄 빈칸에는 1이 들어가야 한다.
<img width="247" height="39" alt="Image" src="https://github.com/user-attachments/assets/5511d28d-6373-4cf5-a434-0853042bc747" />
또한 위쪽 가운데 위치한 3x3 정사각형의 경우에는 3을 제외한 나머지 숫자들이 이미 쓰여있으므로 가운데 빈 칸에는 3이 들어가야 한다.
<img width="92" height="86" alt="Image" src="https://github.com/user-attachments/assets/7fd662e3-f1ed-47c3-8f82-aba3eca8e9a0" />
이와 같이 빈 칸을 차례로 채워 가면 다음과 같은 최종 결과를 얻을 수 있다.
<img width="254" height="241" alt="Image" src="https://github.com/user-attachments/assets/06cb3d17-d29b-442a-8f5f-335d74bc6bb5" />
게임 시작 전 스도쿠 판에 쓰여 있는 숫자들의 정보가 주어질 때 모든 빈 칸이 채워진 최종 모습을 출력하는 프로그램을 작성하시오.

## 입력
아홉 줄에 걸쳐 한 줄에 9개씩 게임 시작 전 스도쿠판 각 줄에 쓰여 있는 숫자가 한 칸씩 띄워서 차례로 주어진다. 스도쿠 판의 빈 칸의 경우에는 0이 주어진다.
스도쿠 판을 규칙대로 채울 수 없는 경우의 입력은 주어지지 않는다.

## 출력
모든 빈 칸이 채워진 스도쿠 판의 최종 모습을 아홉 줄에 걸쳐 한 줄에 9개씩 한 칸씩 띄워서 출력한다.

스도쿠 판을 채우는 방법이 여럿인 경우는 그 중 하나만을 출력한다.

## 예제 입출력
<img width="984" height="281" alt="Image" src="https://github.com/user-attachments/assets/e836d610-07a2-4c39-89d2-b1809bdc7e95" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] sudoku = new int[9][9];
	static ArrayList<int[]> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				if (sudoku[i][j] == 0) list.add(new int[] {i, j});
			}
		} //for - sudoku
		
		dfs(0);
		System.out.println(sb);
	} //main

	private static void dfs(int idx) { //idx: list의 인덱스
		if (idx == list.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sudoku[i][j]).append(" ");
				}
				sb.append("\n");
			}
			return;
		} //if - 탈출조건
		
		int x = list.get(idx)[0];
		int y = list.get(idx)[1];
		
		for (int n = 1; n <= 9; n++) {
			if (isPossible(x, y, n)) {
				sudoku[x][y] = n;
				dfs(idx+1); //숫자가 맞으면 다음 빈칸으로
				sudoku[x][y] = 0; //숫자가 틀리면 다시 빈칸
			}
		} //for
		
	} //dfs

	private static boolean isPossible(int x, int y, int n) {
		//가로줄 검사
		for (int i = 0; i < 9; i++) if (sudoku[x][i] == n) return false;
		
		//세로줄 검사
		for (int i = 0; i < 9; i++) if (sudoku[i][y] == n) return false;
		
		//3x3 검사
		int sx = (x / 3) * 3;
		int sy = (y / 3) * 3;
		for (int i = sx; i < sx + 3; i++) {
			for (int j = sy; j < sy + 3; j++) {
				if (sudoku[i][j] == n) return false;
			}
		}
		return true;
	} //isPossible
}
```

---
# 예제 문제 풀이 (2) - 프로그래머스 N-Queen

## 문제 설명
가로, 세로 길이가 n인 정사각형으로된 체스판이 있습니다. 체스판 위의 n개의 퀸이 서로를 공격할 수 없도록 배치하고 싶습니다.

예를 들어서 n이 4인경우 다음과 같이 퀸을 배치하면 n개의 퀸은 서로를 한번에 공격 할 수 없습니다.
<img width="263" height="532" alt="Image" src="https://github.com/user-attachments/assets/c5a37710-f42b-44ed-9ac2-a5f5ab59d054" />
체스판의 가로 세로의 세로의 길이 n이 매개변수로 주어질 때, n개의 퀸이 조건에 만족 하도록 배치할 수 있는 방법의 수를 return하는 solution함수를 완성해주세요

제한사항
- 퀸(Queen)은 가로, 세로, 대각선으로 이동할 수 있습니다.
- n은 12이하의 자연수 입니다.

## 입출력 예
<img width="140" height="76" alt="Image" src="https://github.com/user-attachments/assets/c88a1e8a-98d2-425f-bb3a-0106131b4ba5" />

입출력 예 설명

입출력 예 #1

문제의 예시와 같습니다.

## 정답 코드 (java)
```
class Solution {
    
    static int N, answer = 0;
	static boolean[][] chess;

    public int solution(int n) {
        N = n;
        chess = new boolean[N][N];
        
        dfs(0);
        return answer;
    }
    
    private static void dfs(int row) {
		if (row >= N) {
			answer++;
			return; //탈출 조건
		}

		for (int col = 0; col < N; col++) {
			if (isPossible(row, col)) {
				chess[row][col] = true;
				dfs(row+1);
				chess[row][col] = false;
			}
		}
	}
    
    private static boolean isPossible(int row, int col) {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (chess[i][j]) {
					if (j == col) return false;
					if (Math.abs(row - i) == Math.abs(col - j)) return false;
				}
			}
		} //for
		
		return true;
	}
}
```
