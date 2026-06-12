package study.week09;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_169199_리코쳇로봇 {
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        boolean[][] visited = new boolean[n][m];

        Queue<int[]> queue = new ArrayDeque<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (board[x].charAt(y) == 'R') {
                    queue.add(new int[]{x, y, 0});
                    visited[x][y] = true;
                }
            }
        } //for - 시작점 찾음

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int answer = cur[2];

            if (board[x].charAt(y) == 'G') return answer;

            for (int d = 0; d < 4; d++) {
                int nx = x;
                int ny = y;

                while (nx + dx[d] >= 0 && nx + dx[d] < n && ny + dy[d] >= 0 && ny + dy[d] < m &&
                        board[nx + dx[d]].charAt(ny + dy[d]) != 'D') {
                    nx += dx[d];
                    ny += dy[d];
                } //while - 부딪힐때까지 이동

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, answer+1});
                } //if - 멈춤
            } //for - d
        } //while - bfs

        return -1;
    }
}
