package study.week08;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_67257_수식최대화 {
    public long solution(String expression) {
        long answer = Integer.MIN_VALUE;

        List<Long> nums = new ArrayList<>(); //숫자를 저장할 배열
        List<Character> opr = new ArrayList<>(); //기호를 저장할 배열

        StringTokenizer st = new StringTokenizer(expression, "+-*", true);
        while (st.hasMoreTokens()) {
            nums.add(Long.parseLong(st.nextToken()));
            if (st.hasMoreTokens()) opr.add(st.nextToken().charAt(0));
        } //while - 숫자와 기호 각 배열에 넣기

        //연산자 우선순위 구하기
        char[] input = {'+', '-', '*'};
        boolean[] visited = new boolean[3];
        List<List<Character>> result = new ArrayList<>();
        permutation(input, visited, new ArrayList<>(), result);

        for (List<Character> oprList : result) {
            // 원본 데이터 복사
            List<Long> subNums = new ArrayList<>(nums);
            List<Character> subOpr = new ArrayList<>(opr);

            for (char o : oprList) {
                for (int i = 0; i < subOpr.size(); i++) {
                    if (subOpr.get(i) == o) {
                        long res = calculate(subNums.get(i), subNums.get(i+1), o);

                        subNums.remove(i);
                        subNums.remove(i);
                        subNums.add(i, res);
                        subOpr.remove(i);

                        i--; //리스트 요소가 하나 줄었으므로 인덱스 하나 줄임
                    } //if - 우선순위 연산자 만남
                } //for
            } //for - o

            answer = Math.max(answer, Math.abs(subNums.get(0)));
        } //for - result 순회

        return answer;
    } //solution

    private long calculate(Long a, Long b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b;
    } //calculate

    private void permutation(char[] input, boolean[] visited, ArrayList<Character> cur, List<List<Character>> result) {
        if (cur.size() == 3) {
            result.add(new ArrayList<>(cur));
            return;
        } //if - base case

        for (int i = 0; i < input.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cur.add(input[i]);

                permutation(input, visited, cur, result);

                cur.remove(cur.size()-1);
                visited[i] = false;
            } //if
        } //for
    } //permutation
}
