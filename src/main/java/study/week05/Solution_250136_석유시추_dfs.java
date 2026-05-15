package study.week05;

public class Solution_250136_석유시추_dfs {
	
	static int N, M, cnt;
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int solution(int[][] land) {
        int answer = Integer.MIN_VALUE;
        
        N = land.length; //세로
        M = land[0].length; //가로
                
        for (int y = 0; y < M; y++) {
        	visited = new boolean[N][M];
        	cnt = 0;
        	
        	for (int x = 0; x < N; x++) {
        		if (land[x][y] == 1 && !visited[x][y]) {
        			cnt++;
        			dfs(x, y, land);
        			
        		} //if - 1을 만나면
        	} //for - x
        	
        	answer = Math.max(answer, cnt);
        } //for - y
        
        return answer;
    }
	
	private static void dfs(int x, int y, int[][] land) {
		visited[x][y] = true;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M
					&& land[nx][ny] == 1 && !visited[nx][ny]) {
				cnt++;
				dfs(nx, ny, land);
			} // if - 주변에서 석유 찾음
		} //for - d
	} //dfs

	public static void main(String[] args) {
		int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
		
		solution(land);
	}
}
