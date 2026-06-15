package study.week09;

import java.util.Stack;

public class Solution_131704_택배상자 {
    static int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int orderIdx = 0;

        for (int i = 1; i <= order.length; i++) {
            stack.push(i);

            while(!stack.isEmpty() && stack.peek() == order[orderIdx]) {
                stack.pop();
                answer++;
                orderIdx++;
            } //while - 트럭에 실을 수 있는 만큼 싣기
        } //for - 메인 벨트에서 박스가 차례로 들어옴

        return answer;
    } //solution

    static void main() {
        int[] order = {4, 3, 1, 2, 5};

        solution(order);
    } //main
}
