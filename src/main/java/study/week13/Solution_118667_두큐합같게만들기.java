package study.week13;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution_118667_두큐합같게만들기 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        long total = (sum1 + sum2);
        long half = total / 2;
        
        if (total % 2 != 0) return -1;
        
        Queue<Integer> que1 = new ArrayDeque<>();
        Queue<Integer> que2 = new ArrayDeque<>();
        for (int i = 0; i < queue1.length; i++) {
            que1.offer(queue1[i]);
            que2.offer(queue2[i]);
        } //for - insert que
        
        int limit = (que1.size() + que2.size()) * 2;
        
        while (answer <= limit) {
            if (sum1 == half) {
                return answer;
            } else if (sum1 > half) {
                int cur = que1.poll();
                sum1 -= cur;
                que2.offer(cur);
                answer++;
            } else {
                int cur = que2.poll();
                sum1 += cur;
                que1.offer(cur);
                answer++;
            } //if ~ else
        } //while
        
        return -1;
    }
}
