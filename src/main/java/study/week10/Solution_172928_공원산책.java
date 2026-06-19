package study.week10;

public class Solution_172928_공원산책 {
    public int[] solution(String[] park, String[] routes) {
        int N = park.length;
        int M = park[0].length();

        int[] cur = new int[2];

        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = park[i].charAt(j);
                if (map[i][j] == 'S') cur = new int[] {i, j};
            }
        } //for - insert map

        for (String str : routes) {
            char dir = str.charAt(0);
            int dist = str.charAt(2) - '0';

            int tmpI = cur[0];
            int tmpJ = cur[1];
            boolean possible = true;

            for (int i = 0; i < dist; i++) {
                if (dir == 'N') tmpI--;
                else if (dir == 'S') tmpI++;
                else if (dir == 'W') tmpJ--;
                else if (dir == 'E') tmpJ++;

                if (tmpI < 0 || tmpI >= N || tmpJ < 0 || tmpJ >= M || map[tmpI][tmpJ] == 'X') {
                    possible = false;
                    break;
                }
            } //for - dist만큼 한 칸씩 이동

            if (possible) {
                cur[0] = tmpI;
                cur[1] = tmpJ;
            } //if - update cur
        } //for - routes

        return cur;
    }
}
