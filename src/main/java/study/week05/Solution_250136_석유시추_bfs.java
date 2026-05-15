package study.week05;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution_250136_석유시추_bfs {
	
	static int N, M;
	static int[][] group;
	static int[] size;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int solution(int[][] land) {
        int answer = Integer.MIN_VALUE;
        
        N = land.length; //세로
        M = land[0].length; //가로
        group = new int[N][M];
        size = new int[N * M + 1];
        
        int id = 1;
        
        for (int x = 0; x < N; x++) {
        	for (int y = 0; y < M; y++) {
        		if (land[x][y] == 1 && group[x][y] == 0) {
        			bfs(x, y, id, land);
        			id++;
        		} //if - bfs
        	} //for - y
        } //for - x
        
        for (int y = 0; y < M; y++) {
        	Set<Integer> set = new HashSet<>();
        	
        	for (int x = 0; x < N; x++) {
        		if (group[x][y] != 0) {
        			set.add(group[x][y]);
        		} //if - 몇 번째 석유 덩어리를 만났는지
        	} //for - x
        	
        	int sum = 0;
        	for (int s : set) sum += size[s];
        	
        	answer = Math.max(answer, sum);
        } //for - y
         
        return answer;
    }
	
	private static void bfs(int x, int y, int id, int[][] land) {
		Queue<int[]> que = new ArrayDeque<>();
		
		que.offer(new int[] {x, y});
		group[x][y] = id;
		
		int cnt = 1;
		
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M
						&& land[nx][ny] == 1 && group[nx][ny] == 0) {
					group[nx][ny] = id;
					cnt++;
					
					que.offer(new int[] {nx, ny});
				} //if - 상하좌우탐색
			} //for - d
		} //while
		
		size[id] = cnt;
	} //bfs

}
