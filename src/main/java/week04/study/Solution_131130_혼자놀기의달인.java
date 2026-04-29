package week04.study;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_131130_혼자놀기의달인 {
	
	static int N, cnt;
    static boolean[] visited;

	public int solution(int[] cards) {
        int answer = 0;
        
        N = cards.length;
        visited = new boolean[N];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int cur = 0; cur < N; cur++) {
        	if (!visited[cur]) {
        		cnt = 0;
        		pq.offer(dfs(cur, cards));
        	}
        } //for - 카드 그룹 수 세기
        
        if (pq.size() < 2) return 0;
        
        answer = pq.poll() * pq.poll();       
        return answer;
    } //solution

	private Integer dfs(int cur, int[] cards) {
		visited[cur] = true;
		cnt++;
		
		int next = cards[cur] - 1;
		if (visited[next]) return cnt;
		
		return dfs(next, cards);
	} //dfs

}

/*
 회고
 - 다음 상자를 선택할 때 재귀 함수를 사용하면 될 것 같아서 dfs를 선택하였다.
 - 우선순위 큐는 기본적으로 오름차순이라 내림차순으로 바꿔주기 위해 Collections.reverseOrder() 메소드를 사용했다.
 */