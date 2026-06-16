package study.week09;

public class Solution_64062_징검다리건너기 {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int low = 1;
        int high = 200_000_000;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (canCross(stones, k, mid)) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            } //if ~ else - mid 명의 사람이 다리를 무사히 건널 수 있는지
        }

        return answer;
    }

    private boolean canCross(int[] stones, int k, int mid) {
        int zeroCnt = 0;

        for (int stone : stones) {
            if (stone < mid) {
                zeroCnt++;

                if (zeroCnt >= k) return false;
            } else {
                zeroCnt = 0;
            } //if ~ else - 돌의 숫자 비교
        }
        return true;
    }
}
