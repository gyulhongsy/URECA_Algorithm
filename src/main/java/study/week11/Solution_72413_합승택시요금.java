package study.week11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_72413_합승택시요금 {

    static int INF = Integer.MAX_VALUE;

    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        } //Constructor
    } //class - Node

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;

        ArrayList<Node>[] graph;

        graph = new ArrayList[n + 1]; //0번지 버림
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        } //for - initialize graph

        for (int i = 0; i < fares.length; i++) {
            int f = fares[i][0];
            int t = fares[i][1];
            int w = fares[i][2];

            graph[f].add(new Node(t, w));
            graph[t].add(new Node(f, w));
        }

        //s, a, b 지점에서 각각 다익스트라 실행
        int[] distS = dijkstra(s, n, graph);
        int[] distA = dijkstra(a, n, graph);
        int[] distB = dijkstra(b, n, graph);

        for (int i = 1; i <= n; i++) {
            if (distS[i] == INF || distA[i] == INF || distB[i] == INF) continue;
            answer = Math.min(answer, distS[i]+distA[i]+distB[i]);
        } //for - 최소 비용 구하기

        return answer;
    }

    private int[] dijkstra(int start, int n, ArrayList<Node>[] graph) {
        int[] dist = new int[n+1]; //0번지 버림
        Arrays.fill(dist, INF);

        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));

        dist[start] = 0;
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (dist[cur.to] < cur.weight) continue;

            for (Node next : graph[cur.to]) {
                int cost = dist[cur.to] + next.weight;

                if (cost < dist[next.to]) {
                    dist[next.to] = cost;
                    queue.add(new Node(next.to, cost));
                } //if
            } //for - next
        } //while - dijkstra

        return dist;
    }
}
