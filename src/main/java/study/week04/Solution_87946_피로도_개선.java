package study.week04;

public class Solution_87946_피로도_개선 {
	
	static int answer = 0;
    static boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];

        dfs(k, 0, dungeons);

        return answer;
    } //solution

    static void dfs(int k, int cnt, int[][] dungeons) {
        answer = Math.max(answer, cnt);

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;

            if (k < dungeons[i][0]) continue;

            visited[i] = true;
            dfs(k-dungeons[i][1], cnt+1, dungeons);
            visited[i] = false;
        } //for - recursion
    } //dfs
}
