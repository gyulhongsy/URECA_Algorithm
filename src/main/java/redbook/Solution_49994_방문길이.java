package redbook;

public class Solution_49994_방문길이 {
    public int solution(String dirs) {
        int answer = 0;

        int[][] map = new int[21][21];
        boolean[][] visited = new boolean[21][21];

        int cx = 10, cy = 10;
        for (char dir : dirs.toCharArray()) {
            int nx = cx, ny = cy;

            if (dir == 'U') nx = cx + 2;
            else if (dir == 'D') nx = cx - 2;
            else if (dir == 'R') ny = cy + 2;
            else if (dir == 'L') ny = cy - 2;

            if (nx >= 0 && nx < 21 && ny >= 0 && ny < 21) {
                int bx = (cx+nx) / 2, by = (cy+ny) / 2;

                if (!visited[bx][by]) {
                    visited[bx][by] = true;
                    answer++;
                } //if - 처음 가는 길

                cx = nx;
                cy = ny;
            } //if - 범위 안쪽인지
        } //for - dirs

        return answer;
    }
}
