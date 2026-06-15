package study.week09;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Solution_49189_가장먼노드 {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>()); //0번지 버림

        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        } //for - insert graph

        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];

        que.add(1);
        visited[1] = true;

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    que.add(next);
                }
            } //for - next
        } //while - bfs

        int max = 0;
        for (int d : dist) {
            if (d > max) {
                max = d;
                answer = 1;
            } else if (d == max) answer++;
        } //for - max

        return answer;
    }
}
