package study.week13;

public class Solution_12971_스티커모으기2 {
    public int solution(int sticker[]) {
        int n = sticker.length;
        
        if (n == 1) return sticker[0];
        
        int[] dp1 = new int[n+1]; //0번지 버림
        int[] dp2 = new int[n+1]; //0번지 버림
        
        //1. 첫 번째 스티커를 떼는 경우
        dp1[1] = sticker[0];
        dp1[2] = dp1[1];
        for (int i = 3; i <= n-1; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + sticker[i-1]);
        } //for - dp1
        dp1[n] = dp1[n-1];
        
        //2. 첫 번째 스티커를 떼지 않는 경우
        dp2[1] = 0;
        dp2[2] = sticker[1];
        for (int i = 3; i <= n; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + sticker[i-1]);
        } //for - dp2
        
        return Math.max(dp1[n], dp2[n]);
    }
}
