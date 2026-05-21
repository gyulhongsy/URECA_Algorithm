class Solution {
    public int solution(int n, int[] tops) {
        int MOD = 10007;
        
        int[] a = new int[n+1];
        int[] b = new int[n+1];
        
        a[0] = 1;
        b[0] = 0;
        
        for (int i = 0; i < n; i++) {
        	if (tops[i] == 1) {
        		a[i+1] = (a[i] * 3 + b[i] * 2) % MOD;
        		b[i+1] = (a[i] + b[i]) % MOD;
        	} else {
        		a[i+1] = (a[i] * 2 + b[i]) % MOD;
        		b[i+1] = (a[i] + b[i]) % MOD;
        	} //if ~ else
        } //for
        
        return (a[n] + b[n]) % MOD;
    }
}