package study.week12;

public class Solution_77885_2개이하로다른비트 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            StringBuilder sb = new StringBuilder("0" + Long.toBinaryString(numbers[i]));
            if (sb.charAt(sb.length()-1) == '0') {
                answer[i] = numbers[i] + 1;
            } else {
                for (int j = sb.length() - 1; j >= 0; j--) {
                    if (sb.charAt(j) == '0') {
                        sb.setCharAt(j, '1');
                        sb.setCharAt(j+1, '0');
                        break;
                    }
                } //for

                Long ansNum = Long.parseLong(sb.toString(), 2);
                answer[i] = ansNum;
            } //if ~ else
        } //for - numbers

        return answer;
    }
}
