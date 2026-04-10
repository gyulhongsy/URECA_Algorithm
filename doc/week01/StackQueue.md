## Stack & Queue 정리

### Stack (스택)

#### 개념
- **LIFO (Last In First Out)** 구조
- 나중에 들어온 데이터가 먼저 나감
- 백트레킹 구조에 적합

#### stack 주요 메서드 (java 예시)
```
import java.util.Stack;

Stack<Integer> stack = new Stack<>();

stack.push(1); // 삽입
stack.push(2);

stack.pop(); // 제거 - 가장 나중에 들어온 데이터가 제거됨
stack.peek(); // 가장 위에 있는 데이터 확인
stack.clear(); // 스택의 모든 요소 삭제
stack.search(x); // 스택에서 x를 찾아 인덱스 반환 (없으면 -1)
stack.size(); // 스택의 데이터 갯수 반환
stack.isEmpty(); // 스택이 비어있는지
System.out.println(stack); // 스택 출력
```

#### 사용 예시
이전 백준 문제 풀이에서 재귀로 구현하였던 dfs 알고리즘을 스택으로 다시 구현하였다.
```
public static void dfs(int start) {
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		
		while (!stack.isEmpty()) {
			int v = stack.pop();
			
			if (!isVisited[v]) {
				isVisited[v] = true;
				sb.append(v).append(" ");
				
				for (int next = N; next > 0; next--) {
					if (graph[v][next] == 1 && !isVisited[next]) {
						stack.push(next);
					}
				}
			}
		}
	}
```
---
###  Queue (큐)

#### 개념
- **FIFO (First In First Out)** 구조
- 먼저 들어온 데이터가 먼저 나감
- 가까운 노드부터 탐색하기 위해 필요
- 프런트(front): 논리적인 맨 앞 요소의 인덱스
- 리어(rear): 논리적인 맨 뒤 요소 하나 뒤의 인덱스 (다음 요소를 인큐할 인덱스)

#### Queue 주요 메서드 (java 예시)
```
import java.util.*;

Queue<Integer> queue = new LinkedList<>();

queue.offer(1); //데이터 삽입
queue.add(2); // 데이터 삽입
queue.poll(); // 데이터 삭제
queue.remove(); // 데이터 삭제

queue.peek(); // 큐의 맨 요소 반환 (front)
queue.clear(); // 큐를 비움
queue.size(); // 큐의 데이터 갯수 반환
queue.isEmpty(); // 큐가 비어있는지
System.out.println(queue); // 큐 출력

// 큐를 LinkedList 타입으로 선언하면 front와 rear를 얻을 수 있음
LinkedList<Integer> que = new LinkedList<>();
que.getFirst(); // front
que.getLast(); // rear
```

#### 사용 예시
이전 백준 문제 풀이 당시 큐를 사용하여 bfs 알고리즘을 구현하였다.
```
static void bfs(int start) {
		Queue<Integer> que = new LinkedList<Integer>();
		
		que.add(start);
		isVisited[start] = true;
		
		while (!que.isEmpty()) {
			int v = que.poll();
			sb.append(v).append(" ");
			
			for (int node = 1; node <= N; node++) {
				if (graph[v][node] == 1 && !isVisited[node]) {
					que.offer(node);
					isVisited[node] = true;
				}
			}
		}
	}
```
