# Union-Find (Disjoint Set)

## 개념
- 서로소 집합(Disjoint Set)을 관리하는 자료구조
- 여러 원소가 있을 때 어떤 원소들이 같은 집합인지 빠르게 확인 가능
- 핵심 목적
  - 집합 합치기 -> Union
  - 같은 집합인지 확인하기 -> Find
### 왜 필요할까?
일반적으로 그룹 여부를 확인하려면 탐색이 필요할 수 있다. <br>
하지만 Union-Find를 사용하면 합치는 과정과 같은 그룹인지 확인하는 과정이 빠르다. <br>
따라서 연결 관계가 자주 바뀌는 문제에서 매우 효율적!
### 핵심 연산
1. **Find(x)** : x가 속한 집합의 대표(root)를 찾음
2. **Union(a, b)** : a와 b가 속한 집합을 하나로 합침
---
# 예제 문제 풀이 (1) - 백준 1717번 집합의 표현

## 문제
초기에 {n+1}개의 집합 {0}, {1}, {2}, ... , {n} 이 있다. 여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다. <br>
집합을 표현하는 프로그램을 작성하시오.

## 입력
첫째 줄에 n, m이 주어진다. m은 입력으로 주어지는 연산의 개수이다. 다음 m개의 줄에는 각각의 연산이 주어진다. 합집합은 0 a b의 형태로 입력이 주어진다. 
이는 a가 포함되어 있는 집합과, $가 포함되어 있는 집합을 합친다는 의미이다. 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b의 형태로 입력이 주어진다. 
이는 a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산이다.

## 출력
1로 시작하는 입력에 대해서 a와 b가 같은 집합에 포함되어 있으면 "YES" 또는 "yes"를, 그렇지 않다면 "NO" 또는 "no"를 한 줄에 하나씩 출력한다.

## 제한
- 1 <= n <= 1000000
- 1 <= m <= 100000
- 0 <= a, b <= n
- a, b는 정수
- a와 b는 같을 수도 있다.

## 예제 입출력
<img width="1146" height="326" alt="Image" src="https://github.com/user-attachments/assets/add990d1-fe0e-4031-ac7c-a15a5e00202f" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		for (int i = 0; i <= n; i++) parent[i] = i;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (cmd == 0) {
				union(a, b);
			} else {
				if (find(a) == find(b)) System.out.println("YES");
				else System.out.println("NO");
			} //if - cmd			
		} //for
		br.close();
	} //main
	
	private static void union(int a, int b) {
		int root1 = find(a);
		int root2 = find(b);
		parent[root2] = root1;
	} //union

	public static int find(int x) {		
		if (parent[x] == x) {
			return x;
		} //if - base case
		
		parent[x] = find(parent[x]);		
		return parent[x];
	} //find
} //class
```
## 회고
- union-find 알고리즘의 union과 find 함수를 구현하기에 아주 좋은 문제였다.

## 알고리즘 분류
- 자료 구조
- 분리 집합
---
# 예제 문제 풀이 (2) - 백준 24391번 귀찮은 해강이

## 문제
해강이는 앙중대학교에 다닌다. 해강이는 이번 학기에 강의코드 1번부터 N번까지 N개의 강의를 듣고 있다. <br>
모든 강의는 강의코드와 동일한 번호의 건물에서 진행된다. 예를 들어, 강의코드가 1인 강의는 1번 건물에서 진행되고, 강의코드가 N-1인 강의는 N-1번 건물에서 진행된다. <br>
해강이는 밖에 나오는 것을 싫어해서, 강의 시간표 순서대로 모든 강의를 들으면서 한 건물에서 밖으로 나와서 다른 건물로 이동하는 횟수를 최소화하고 싶다. 
앙중대학교에는 다행히도 서로 연결되어 있는 건물들이 있어, 이 건물끼리는 밖으로 나오지 않고 이동할 수 있다. <br>
해강이의 강의 시간표가 주어질 때, 밖에 나오는 것을 싫어하는 해강이를 위해 최소 몇 번만 밖에 나오면 되는지 구해보자. 맨 처음 강의를 들으러 이동하는 횟수는 세지 않는다.

## 입력
첫째 줄에 강의의 개수 N(1 ≤ N ≤ 105)과 연결되어 있는 건물의 쌍의 개수 M(0 ≤ M ≤ 105)이 공백으로 구분되어 주어진다. <br>
두 번째 줄부터는 M줄에 걸쳐 i와 j(1 ≤ i, j ≤ N)가 주어진다. 이는 i번 건물과 j번 건물이 연결되어 있다는 의미이다. 
건물이 자기 자신과 연결되거나, 이미 연결된 건물의 쌍이 다시 주어지는 경우는 없다. <br>
마지막 줄에는 N개의 강의코드 Ai(1 ≤ Ai ≤ N)로 이루어진 강의 시간표가 공백으로 구분되어 주어진다.

## 출력
해강이가 밖에 나와야 하는 최소 횟수를 출력한다.

## 예제 입출력
<img width="1150" height="228" alt="Image" src="https://github.com/user-attachments/assets/e8ee3422-caf0-4dd3-a5ce-3b2986a00ccb" />

## 정답 코드 (java)
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] parent, lecs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lecs = new int[N];
		
		parent = new int[N+1]; //인덱스를 값과 일치시키기 위해 0번지 버림
		for (int i = 1; i <= N; i++) parent[i] = i;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
		} //for - union
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) lecs[i] = Integer.parseInt(st.nextToken());
		
		int cnt = 0; //건물 이동 횟수
		for (int i = 0; i < N-1; i++) {
			if (find(lecs[i]) != find(lecs[i+1])) cnt++;
		} // for - count cnt
		
		System.out.println(cnt);
	} //main

	private static void union(int a, int b) {
		int root1 = find(a);
		int root2 = find(b);
		parent[root2] = root1;
	} //union

	private static int find(int x) {
		if (parent[x] == x) return x;
		
		parent[x] = find(parent[x]);
		
		return parent[x];
	} //find

} //class
```
## 회고
- 공부했던 union-find 알고리즘으로 쉽게 해결할 수 있었다.

## 알고리즘 분류
- 자료 구조
- 그래프 이론
- 그래프 탐색
- 분리 집합
