package study.week04;

public class Solution_87946_피로도 {
	
	static int N;
	static int answer = Integer.MIN_VALUE;
	static int[] output;
	static boolean[] visited;

	public int solution(int k, int[][] dungeons) {
        
        N = dungeons.length;
        output = new int[N]; //던전의 인덱스 저장
        visited = new boolean[N];
        
        permutaion(0, k, dungeons);
        
        return answer;
    } //solution

	private void permutaion(int idx, int k, int[][] dungeons) {
		if (idx >= N) {
			int cnt = 0;
			for (int x : output) {
				if (k > 0) {
					if (k >= dungeons[x][0]) {
						k -= dungeons[x][1];
						cnt++;
					} else break;
				} // if - cnt
			} // for - 던전 탐방
			answer = Math.max(answer, cnt);
		} //if - base case
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			output[idx] = i;
			permutaion(idx+1, k, dungeons);
			visited[i] = false;
		} //for - recursion
	} //permutation
}
