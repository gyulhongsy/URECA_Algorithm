package week04.study;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_154540_무인도여행 {
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public int[] solution(String[] maps) {
		int[] answer = {};
        N = maps.length;
        M = maps[0].length();
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < M; j++) {
        		char c = maps[i].charAt(j);
        		if (c == 'X') map[i][j] = 0;
        		else map[i][j] = c - '0';
        	}
        } //for - map
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < M; j++) {
        		if (map[i][j] != 0 && !visited[i][j]) {
        			pq.offer(bfs(i, j));
        		}
        	}
        } //for
        
        if (pq.size() == 0) pq.offer(-1);
        int K = pq.size();
        answer = new int[K];
        for (int i = 0; i < K; i++) {
        	answer[i] = pq.poll();
        }
        
        return answer;
    }

	private Integer bfs(int i, int j) {
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {i, j});
		visited[i][j] = true;
		int sum = map[i][j];
		
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || ny < 0 || nx >= N || ny>= M) continue;
				
				if (map[nx][ny] != 0 && !visited[nx][ny]) {
					sum += map[nx][ny];
					visited[nx][ny] = true;
					que.offer(new int[] {nx, ny});
				} //if
			} //for
		} //while		
		return sum;
	} //bfs

}
