package study.week11;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_154538_숫자변환하기 {
    public int solution(int x, int y, int n) {
        if (x == y) return 0;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[1_000_001];

        queue.add(new int[] {x, 0});
        visited[x] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int num = tmp[0];
            int cnt = tmp[1];

            int[] nextNums = {num+n, num*2, num*3};

            for (int next : nextNums) {
                if (next == y) return cnt+1;

                if (next < y && !visited[next]) {
                    visited[next] = true;
                    queue.add(new int[] {next, cnt+1});
                } //if - insert next
            } //for - next
        } //while - bfs

        return -1;
    }
}
