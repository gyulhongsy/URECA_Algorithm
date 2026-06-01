package study.week07;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_43163_단어변환 {

    static class Node {
        String word;
        int step; //변환 횟수

        public Node(String word, int step) {
            this.word = word;
            this.step = step;
        } //Constructure
    } //class - Node

    public int solution(String begin, String target, String[] words) {
        int N = words.length;
        boolean[] visited = new boolean[N];

        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(begin, 0));

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (cur.word.equals(target)) return cur.step;

            for (int i = 0; i < N; i++) {
                if (!visited[i] && isOneCharDiff(cur.word, words[i])) {
                    visited[i] = true;
                    que.add(new Node(words[i], cur.step+1));
                } //if
            } //for
        } //while

        return 0;
    } //solution

    private boolean isOneCharDiff(String cur, String next) {
        int diffCnt = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) != next.charAt(i)) diffCnt++;

            if (diffCnt > 1) return false;
        } //for - 문자 비교
        return diffCnt == 1;
    } //isOneCharDiff
}
