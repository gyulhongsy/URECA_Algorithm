package study.week10;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_87694_아이템줍기 {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[102][102];

        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2;
            int x2 = r[2] * 2, y2 = r[3] * 2;

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (x1 < i && x2 > i && y1 < j && y2 > j) map[i][j] = 2;
                    else if (map[i][j] != 2) map[i][j] = 1;
                }
            }
        } //for - 사각형들을 도화지에 그리기 (테두리: 1, 내부: 2)

        int startX = characterX * 2, startY = characterY * 2;
        int targetX = itemX * 2, targetY = itemY * 2;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[102][102];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        queue.add(new int[] {startX, startY, 0});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1], dist = cur[2];

            if (cx == targetX && cy == targetY) return dist / 2;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && nx < 102 && ny >= 0 && ny < 102) {
                    if (map[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny, dist+1});
                    }
                }
            }
        } //while - bfs

        return -1;
    }
}
