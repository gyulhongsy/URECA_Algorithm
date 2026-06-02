package study.week07;

public class Solution_43238_입국심사 {
    public long solution(int n, int[] times) {
        long answer = 0;

        int maxTime = 0;
        for (int t : times) {
            if (t > maxTime) maxTime = t;
        } //for - maxTime

        long left = 1;
        long right = (long)maxTime * n;

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;

            for (int t : times) {
                sum += (mid / t);
            } //for - sum

            if (sum >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            } //if ~ else
        } //while - binary search

        return answer;
    } //solution
}
