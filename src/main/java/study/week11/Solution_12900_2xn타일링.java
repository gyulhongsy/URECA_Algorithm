package study.week11;

public class Solution_12900_2xn타일링 {
    public int solution(int n) {
        if (n == 1) return 1;

        int DIV = 1_000_000_007;
        int[] dp = new int[n+1]; //0번지 버림

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % DIV;
        }

        return dp[n];
    }
}
