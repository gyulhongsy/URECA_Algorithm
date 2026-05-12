package study.week04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_12978_배달 {
	
	static class Edge implements Comparable<Edge> {
		int node;
		int cost;
		
		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		} //Constructor
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	} //Edge
	
	@SuppressWarnings("unchecked")
	public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        List<Edge>[] graph = new ArrayList[N+1]; //1번지 버림
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
                
        for (int[] e : road) {
        	graph[e[0]].add(new Edge(e[1], e[2]));
        	graph[e[1]].add(new Edge(e[0], e[2]));
        } //for - insert graph
        
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        
        while (!pq.isEmpty()) {
        	Edge cur = pq.poll();
        	int curNode = cur.node;
        	int curCost = cur.cost;
        	
        	if (curCost > dist[curNode]) continue;
        	
        	for (Edge next : graph[curNode]) {
        		int nextNode = next.node;
        		int nextCost = curCost + next.cost;
        		
        		if (dist[nextNode] > nextCost) {
        			dist[nextNode] = nextCost;
        			pq.offer(new Edge(nextNode, nextCost));
        		} //if
        	} //for - next
        } //while - dijkstra
        
        for (int d = 1; d <= N; d++) {
        	if (dist[d] <= K) answer++;
        } //for - count answer
        
        return answer;
    } //solution
}
