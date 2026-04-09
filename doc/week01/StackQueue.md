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
stack.indexOf(x); // 스택에서 x를 찾아 인덱스 반환 (없으면 -1)
stack.size(); // 스택의 데이터 갯수 반환
stack.isEmpty(); // 스택이 비어있는지
stack.dump(); // 스택의 모든 데이터를 가장 아래부터 순서대로 출력
```

#### 사용 예시
이전에 재귀로 구현하였던 dfs 알고리즘을 스택으로 다시 구현
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

