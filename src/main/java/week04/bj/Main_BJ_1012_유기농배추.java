package week04.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1012_유기농배추 {
	
	static int T, M, N, K;
	static int[][] field;
	static boolean[][] isVisited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			field = new int[M][N];
			isVisited = new boolean[M][N];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				field[a][b] = 1;
			} //for - field
			
			System.out.println(bfs());
		} //for
	} //main

	private static int bfs() {
		Queue<int[]> que = new ArrayDeque<>();
		int worm = 0;
		
		for (int x = 0; x < M; x++) {
			for (int y = 0; y < N; y++) {
				
				if (field[x][y] == 1 && !isVisited[x][y]) {
					que.offer(new int[] {x, y});
					isVisited[x][y] = true;
					worm++;
					
					while (!que.isEmpty()) {
						int[] cur = que.poll();
						
						int cx = cur[0];
						int cy = cur[1];
						
						for (int d = 0; d < 4; d++) {
							int nx = cx + dx[d];
							int ny = cy + dy[d];
							
							if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
							
							if (field[nx][ny] == 1 && !isVisited[nx][ny]) {
								isVisited[nx][ny] = true;
								que.offer(new int[] {nx, ny});
							} //if
						} //for - 상하좌우 탐색
					} //while - count worm
				} //if - 배추 발견
			} //for - y
		} //for - x
		return worm;
	} //bfs

}
