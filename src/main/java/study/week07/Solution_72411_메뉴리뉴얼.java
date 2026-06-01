package study.week07;

import java.util.*;

public class Solution_72411_메뉴리뉴얼 {

    static int N, M;
    static char[] courses;
    static HashMap<String, Integer> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < orders.length; i++) {
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = new String(chars);
        } //for - 주문 오름차순 정렬

        for (int i = 0; i < course.length; i++) {
            map.clear();
            M = course[i];
            for (int j = 0; j < orders.length; j++) {
                N = orders[j].length();
                courses = new char[M];

                combination(0, 0, orders[j]);
            } //for - combination

            if (!map.isEmpty()) {
                int maxCount = Collections.max(map.values());
                if (maxCount >= 2) {
                    for (String key : map.keySet()) {
                        if (map.get(key) == maxCount) answer.add(key);
                    }
                } //if - insert answer
            } //if
        } //for - course

        Collections.sort(answer);
        return answer.toArray(new String[0]);
    } //solution

    private void combination(int courI, int inI, String orders) {
        if (courI >= M) {
            String courseStr = new String(courses);
            map.put(courseStr, map.getOrDefault(courseStr, 0) + 1);
            return;
        } //if - base case

        for (int i = inI; i < N; i++) {
            courses[courI] = orders.charAt(i);
            combination(courI+1, i+1, orders);
        } //for
    } //combination
}
