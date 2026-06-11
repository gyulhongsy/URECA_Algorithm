package study.week09;

public class Solution_68645_삼각달팽이 {
    static int[] solution(int n) {
        int[][] nums = new int[n][n];

        int row = -1;
        int col = 0;
        int num = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) { //아래
                    row++;
                } else if (i % 3 == 1) { //오른쪽
                    col++;
                } else if (i % 3 == 2) { //대각선 왼쪽 위
                    row--;
                    col--;
                } //if ~ else

                nums[row][col] = num;
                num++;
            } //for - j
        } //for - 방향 전환 횟수

        int[] answer = new int[num-1];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] != 0) answer[k++] = nums[i][j];
                else continue;
            }
        } //for - insert answer

        return answer;
    } //solution

    static void main() {
        int n = 4;

        System.out.println(solution(n));
    }
}
