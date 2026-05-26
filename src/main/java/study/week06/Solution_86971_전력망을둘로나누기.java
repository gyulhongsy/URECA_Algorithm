package study.week06;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Solution_86971_전력망을둘로나누기 {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] wire : wires) {
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        } //for - insert graph

        for (int i = 0; i < wires.length; i++) {
            //간선 끊기
            int excludeU = wires[i][0];
            int excludeV = wires[i][1];

            int cnt = 0;
            boolean[] visited = new boolean[n+1];
            Queue<Integer> que = new ArrayDeque<>();

            que.add(1); //1번 노드부터 탐색
            visited[1] = true;
            cnt++;

            while (!que.isEmpty()) {
                int cur = que.poll();
                for (int next : graph[cur]) {
                    if ((cur == excludeV && next == excludeU) || (cur == excludeU && next == excludeV)) continue;

                    if (!visited[next]) {
                        visited[next] = true;
                        cnt++;
                        que.add(next);
                    } //if
                } //for - next
            } //while - bfs

            int diff = Math.abs(cnt - (n-cnt));
            answer = Math.min(answer, diff);
        } //for - i

        return answer;
    }
}
