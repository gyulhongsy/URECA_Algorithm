# LinkedList와 ListIterator를 활용한 커서 구현

## 문제 상황
문자열 중간에서 왼쪽 이동, 오른쪽 이동, 삽입, 삭제 기능이 필요한 편집기(커서) 문제를 구현해야 했다.
```
abc|de
(| = 커서 위치)
```
처음에는 LinkedList를 사용하고, 인덱스를 따로 계산하여 커서 이동과 중간 삽입을 처리했었다. 
하지만 시간 초과로 인해 로직을 변경하게 되었다.

### LinkedList 선택 이유
LinkedList는 노드 연결 구조라 중간 삽입과 중간 삭제 모두 시간 복잡도가 O(1)로 간편하다. (단, 위치를 알고 있을 때)

-> 커서 기반 편집에 적합하다고 생각했다.

그러나 인덱스를 따로 계산하는 과정에서 시간 초과가 발생했다.

### ListIterator를 사용한 이유
LinkedList에서 단순 인덱스 접근은 매번 순차 탐색을 하여 느리기 때문에 현재 위치를 유지하는 ListIterator를 사용하였다.

주요 메소드
- 이전 이동: `previous()`
- 다음 이동: `next()`
- 삽입: `add()`
- 삭제: `remove()`

## 핵심 원리
ListIterator는 단순 반복자가 아니라 **현재 위치(커서)를 기억하는 객체**이다. <br>
즉, `a b c | d e` 에서 현재 커서 기준으로
- `previous()` -> 왼쪽 이동
- `next()` -> 오른쪽 이동
- `add()` -> 커서 위치에 삽입 <br>
처럼 동작한다. <br>
따라서 모든 동작에서 시간 복잡도는 O(1)이 된다.

# 대표 문제 - 백준 5397번 키로거

## 문제 
창영이는 강산이의 비밀번호를 훔치기 위해서 강산이가 사용하는 컴퓨터에 키로거를 설치했다. 며칠을 기다린 끝에 창영이는 강산이가 비밀번호 창에 입력하는 글자를 얻어냈다.

키로거는 사용자가 키보드를 누른 명령을 모두 기록한다. 따라서, 강산이가 비밀번호를 입력할 때, 화살표나 백스페이스를 입력해도 정확한 비밀번호를 알아낼 수 있다. 

강산이가 비밀번호 창에서 입력한 키가 주어졌을 때, 강산이의 비밀번호를 알아내는 프로그램을 작성하시오. 강산이는 키보드로 입력한 키는 알파벳 대문자, 소문자, 숫자, 백스페이스, 화살표이다.

## 입력
첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스는 한줄로 이루어져 있고, 강산이가 입력한 순서대로 길이가 L인 문자열이 주어진다. 
(1 ≤ L ≤ 1,000,000) 강산이가 백스페이스를 입력했다면, '-'가 주어진다. 이때 커서의 바로 앞에 글자가 존재한다면, 그 글자를 지운다. 
화살표의 입력은 '<'와 '>'로 주어진다. 이때는 커서의 위치를 움직일 수 있다면, 왼쪽 또는 오른쪽으로 1만큼 움직인다. 나머지 문자는 비밀번호의 일부이다. 
물론, 나중에 백스페이스를 통해서 지울 수는 있다. 만약 커서의 위치가 줄의 마지막이 아니라면, 커서 및 커서 오른쪽에 있는 모든 문자는 오른쪽으로 한 칸 이동한다.

## 출력
각 테스트 케이스에 대해서, 강산이의 비밀번호를 출력한다. 비밀번호의 길이는 항상 0보다 크다

## 예제 입출력
<img width="1152" height="174" alt="Image" src="https://github.com/user-attachments/assets/ab662820-a12e-4f09-8925-789e175d07ee" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
	
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String str;
		while (N-- > 0) { //N을 하나씩 줄이면서 0이 될때까지
			str = br.readLine();
			
			LinkedList<Character> pw = new LinkedList<>();
			ListIterator<Character> it = pw.listIterator();
			
			for (char c : str.toCharArray()) {
				if (c == '<') {
					if (it.hasPrevious()) it.previous();
				} else if (c == '>') {
					if (it.hasNext()) it.next();
				} else if (c == '-') {
					if (it.hasPrevious()) {
						it.previous();
						it.remove();
					}
				} else {
					it.add(c);
				}
			} //for - make pw
			
			StringBuilder sb = new StringBuilder();
			for (char c : pw) sb.append(c);
			System.out.println(sb);
		} //for 
		
		br.close();
	} //main
}
```

## 알고리즘 분류
- 자료 구조
- 스택
- 연결 리스트

## 회고
- Iterator를 이론으로만 알고 넘어갔었는데, 위치 정보를 활용하면 커서처럼 활용할 수 있다는 것을 알게 되었다.
---
# 비슷한 문제 풀이 - 백준 1406번 에디터

## 문제
한 줄로 된 간단한 에디터를 구현하려고 한다. 이 편집기는 영어 소문자만을 기록할 수 있는 편집기로, 최대 600,000글자까지 입력할 수 있다.

이 편집기에는 '커서'라는 것이 있는데, 커서는 문장의 맨 앞(첫 번째 문자의 왼쪽), 문장의 맨 뒤(마지막 문자의 오른쪽), 또는 문장 중간 임의의 곳(모든 연속된 두 문자 사이)에 위치할 수 있다. 
즉 길이가 L인 문자열이 현재 편집기에 입력되어 있으면, 커서가 위치할 수 있는 곳은 L+1가지 경우가 있다.

이 편집기가 지원하는 명령어는 다음과 같다.
```
- L: 커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
- D: 커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
- B: 커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨).
  삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
- P $: $라는 문자를 커서 왼쪽에 추가함
```
초기에 편집기에 입력되어 있는 문자열이 주어지고, 그 이후 입력한 명령어가 차례로 주어졌을 때, 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 구하는 프로그램을 작성하시오. 
단, 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치하고 있다고 한다.

## 입력
첫째 줄에는 초기에 편집기에 입력되어 있는 문자열이 주어진다. 이 문자열은 길이가 N이고, 영어 소문자로만 이루어져 있으며, 길이는 100,000을 넘지 않는다. 
둘째 줄에는 입력할 명령어의 개수를 나타내는 정수 M(1 ≤ M ≤ 500,000)이 주어진다. 
셋째 줄부터 M개의 줄에 걸쳐 입력할 명령어가 순서대로 주어진다. 명령어는 위의 네 가지 중 하나의 형태로만 주어진다.

## 출력
첫째 줄에 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 출력한다.

## 예제 입출력
<img width="1146" height="622" alt="Image" src="https://github.com/user-attachments/assets/11b77f6a-3236-4b7b-851a-8cfa6c254a00" />
<img width="1148" height="433" alt="Image" src="https://github.com/user-attachments/assets/66bb941a-7fcf-4e0b-8057-3602be28e905" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		String init = st.nextToken();
		
		LinkedList<Character> str = new LinkedList<>();
		for (char c : init.toCharArray()) str.add(c);
		ListIterator<Character> cur = str.listIterator(str.size());

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char order = st.nextToken().charAt(0);
			
			if (order == 'P') {
				char c = st.nextToken().charAt(0);
				cur.add(c);
			} else if (order == 'L') {
				if (cur.hasPrevious()) cur.previous();
			} else if (order == 'D') {
				if (cur.hasNext()) cur.next();
			} else if (order == 'B') {
				if (cur.hasPrevious()) {
					cur.previous();
					cur.remove();
				}
			} //if - order
		} //for
		br.close();
		
		StringBuilder sb = new StringBuilder();
		for (char c : str) sb.append(c);
		System.out.println(sb.toString());
		
	} //main

}
```

## 알고리즘 분류
- 자료 구조
- 스택
- 연결 리스트
