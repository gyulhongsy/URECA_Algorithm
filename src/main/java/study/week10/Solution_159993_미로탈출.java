package study.week10;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_159993_미로탈출 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0 ,0, -1, 1};

    static int N, M;

    public int solution(String[] maps) {

        N = maps.length;
        M = maps[0].length();
        int[] start = new int[2];
        boolean[][] isVisited = new boolean[N][M];

        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = maps[i].charAt(j);
                if (map[i][j] == 'S') start = new int[] {i, j};
            }
        } //for - insert map

        int toLever = bfs(map, start, 'L');
        if (toLever == -1) return -1;

        int toExit = bfs(map, start, 'E');
        if (toExit == -1) return -1;

        return toLever + toExit;
    } //solution

    private int bfs(char[][] map, int[] start, char target) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[N][M];

        queue.add(new int[] {start[0], start[1], 0});
        isVisited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            int dist = cur[2];

            if (map[cx][cy] == target) {
                start[0] = cx;
                start[1] = cy;
                return dist;
            } //if - 목표지점 도착

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M &&
                    !isVisited[nx][ny] && map[nx][ny] != 'X') {
                    isVisited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, dist+1});
                }
            } //for - 사방탐색
        } //while - bfs

        return -1;
    } //bfs

}
