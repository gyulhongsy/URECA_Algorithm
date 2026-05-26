package study.week06;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Solution_42586_기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();

        Queue<Integer> que = new ArrayDeque<>();

        for (int i = 0; i < progresses.length; i++) {
            int tmp = (100 - progresses[i]) % speeds[i];
            int days = 0;
            if (tmp == 0) days = (100 - progresses[i]) / speeds[i];
            else days = (100 - progresses[i]) / speeds[i] + 1;
            que.add(days);
        } //for - calc days

        while (!que.isEmpty()) {
            int curMax = que.poll();
            int cnt = 1;

            while (!que.isEmpty()) {
                if (que.peek() <= curMax) {
                    que.poll();
                    cnt++;
                }
                else break;
            } //while - 뒤의 작업 확인

            answer.add(cnt);
        } //while

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
