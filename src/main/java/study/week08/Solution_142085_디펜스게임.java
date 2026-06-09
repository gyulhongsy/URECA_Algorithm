package study.week08;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_142085_디펜스게임 {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        int size = enemy.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < size; i++) {
            n -= enemy[i];
            queue.add(enemy[i]);
            answer++;

            if (n < 0) {
                if (k > 0) {
                    n += queue.poll();
                    k--;
                } else {
                    answer--;
                    break;
                }
            } //if - 병사 수 마이너스
        } //for

        return answer;
    } //solution
}
