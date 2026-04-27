package week04;

import java.util.ArrayList;

public class Solution_PS_42840_모의고사 {

	public int[] solution(int[] answers) {
        int[][] students = new int[3][];
        ArrayList<Integer> answer = new ArrayList<>();
        int max = 0;
        
        students[0] = new int[] { 1, 2, 3, 4, 5 };
        students[1] = new int[] { 2, 1, 2, 3, 2, 4, 2, 5 };
        students[2] = new int[] { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
        
        for (int i = 0; i < students.length; i++) {
        	int cnt = 0;
        	for (int j = 0; j < answers.length; j++) {
        		if (answers[j] == students[i][j % students[i].length]) cnt++;
        	}
        	
        	if (cnt > max) {
        		answer.clear();
        		answer.add(i+1);
        		max = cnt;
        	} else if (cnt == max) {
        		answer.add(i+1);
        	} //if - cnt
        	
        } //for - set
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
	
}

/*
 회고
 - answer의 자료구조는 처음에 그냥 주어진 정수형 배열로 사용하였으나 정답자가 1명일 수도, 2명일 수도, 3명일 수도 있기 때문에 값을 넣기가 까다로웠다.
 따라서 ArrayList로 바꾸어 다시 구현하였다.
 */