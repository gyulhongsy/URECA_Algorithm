package study.week12;

import java.util.Arrays;

public class Solution_42746_가장큰수 {
    public String solution(int[] numbers) {
        int n = numbers.length;

        String[] strNumbers = new String[n];
        for (int i = 0; i < n; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strNumbers, (o1, o2) -> (o2+o1).compareTo(o1+o2));

        if (strNumbers[0].equals("0")) return "0"; //입력 받은 숫자에 0만 있는 경우

        StringBuilder sb = new StringBuilder();
        for (String str : strNumbers) {
            sb.append(str);
        }

        return sb.toString();
    }
}