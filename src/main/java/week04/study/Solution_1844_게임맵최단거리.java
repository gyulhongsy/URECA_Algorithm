package week04.study;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_1844_게임맵최단거리 {
	public int solution(int[][] maps) {
        int answer = 0;
        int N = maps.length;
        int M = maps[0].length;
        Queue<int[]> que = new LinkedList<int[]>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        que.offer(new int[] {0, 0});
        
        while (!que.isEmpty()) {
        	int[] cur = que.poll();
        	int x = cur[0];
        	int y = cur[1];
        	
        	for (int d = 0; d < 4; d++) {
        		int nx = x + dx[d];
        		int ny = y + dy[d];
        		
        		if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
        		
        		if (maps[nx][ny] == 1) {
        			maps[nx][ny] = maps[x][y] + 1;
        			que.offer(new int[] {nx, ny});
        		}
        	} //for
        } //while
        if (maps[N-1][M-1] == 1) answer = -1;
        else answer = maps[N-1][M-1];
        
        return answer;
    } //solution
}