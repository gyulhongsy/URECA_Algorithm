package study.week06;

import java.util.Arrays;

public class Solution_12923_숫자블록 {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin + 1)];

        for (int i = 0; i < answer.length; i++) {
            long num = begin + i; //실제 숫자

            if (num == 1) {
                answer[i] = 0;
                continue;
            }

            answer[i] = 1;

            for (long j = 2; j <= Math.sqrt(num); j++) {
                if (num % j == 0) {
                    if (num / j <= 10000000) {
                        answer[i] = (int)(num / j);
                    } else {
                        answer[i] = (int)j;
                    } //if ~ else - 1000만보다 작은 가장 큰 약수
                    break;
                } //if - 약수 후보 발견
            } //for - j
        } //for - i

        return answer;
    }
}
