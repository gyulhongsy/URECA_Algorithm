## BFS & DFS 개념 정리

### DFS (Depth-First Search): 깊이 우선 탐색

#### 특징
- 한 방향으로 끝까지 탐색한 후, 더 이상 갈 수 없으면 되돌아오는 방식
- 재귀 함수또는 스택(Stack)을 사용하여 구현
- 모든 경우의 수를 탐색하거나 경로를 찾는 문제에 적합

#### java에서 구현한 코드 (재귀 함수 사용)
```
public static void DFS(int v) {
  if (isVisited[v] == false) {
      System.out.print(v + " ");
      isVisited[v] = true;
  }

  for (int i = 0;, i < graph[v].size(); i=++) {
    if (isVisited[graph[v].get(i)]) continue;
    DFS(graph[v].get(i));
  }
}
```
---
### BFS (Breath-First Search): 너비 우선 탐색

#### 특징
- 가까운 노드부터 탐색하는 방식
- 큐(Queue)를 사용하여 구현
- 최단 거리 혹은 최소 이동 문제에 적합

#### java에서 구현한 코드
```
public void static BFS(int root) {
  Queue<Integer> que = new LinkedList<Integer>();
  que.add(root);
  isVisited[root] = true;

  while (!que.inEmpty()) {
    int v = que.poll();
    System.out.print(v + " ");

    for (int i = 0; i < graph[v].size(); i++) {
      if (isVisited[graph[v].get(i)] == true) continue;
      que.add(graph[v].get(i));
      isVisited[graph[v].get(i)] = true;
    }
  }
}
```
---
