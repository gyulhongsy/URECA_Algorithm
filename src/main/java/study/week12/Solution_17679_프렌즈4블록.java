package study.week12;

public class Solution_17679_프렌즈4블록 {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] blocks = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = board[i].charAt(j);
            }
        } //for - insert blocks

        while (true) {
            boolean[][] matched = new boolean[m][n];
            boolean hasPopped = false;

            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char c = blocks[i][j];

                    if (c == '-') continue; //이미 빈칸이면 건너뛰기

                    if (blocks[i][j + 1] == c && blocks[i + 1][j] == c && blocks[i + 1][j + 1] == c) {
                        matched[i][j] = true;
                        matched[i][j + 1] = true;
                        matched[i + 1][j] = true;
                        matched[i + 1][j + 1] = true;
                    } //if - 2x2 찾음
                }
            } //for - 2x2 검사

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matched[i][j]) {
                        answer++;
                        blocks[i][j] = '-';
                        hasPopped = true;
                    }
                }
            } //for - 블럭 터트리기

            if (!hasPopped) break;

            for (int j = 0; j < n; j++) {
                int writeIdx = m - 1; //바닥 위치부터 시작

                for (int i = m - 1; i >= 0; i--) {
                    if (blocks[i][j] != '-') {
                        blocks[writeIdx][j] = blocks[i][j];
                        writeIdx--;
                    } //if - 블록 찾음
                }

                for (int i = writeIdx; i >= 0; i--) {
                    blocks[i][j] = '-';
                } //for - 블록 밀고 남은 부분은 공백으로
            } //for - 빈칸 채우기
        } //while

        return answer;
    }
}
