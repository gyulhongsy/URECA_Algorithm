package study.week04;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Stack;

public class Solution_176962_과제진행하기 {
	
	static class Work {
		String name;
		LocalTime start;
		int time;
		
		public Work(String name, LocalTime start, int time) {
			this.name = name;
			this.start = start;
			this.time = time;
		} //Constructor
	}
	
	public static void main(String[] args) {
		String[][] input = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
		
		solution(input);
	}
	
	static String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        Stack<Work> stack = new Stack<>();
        
        ArrayList<Work> works = new ArrayList<>();
        for (String[] p : plans) {
        	works.add(new Work(p[0], LocalTime.parse(p[1]), Integer.parseInt(p[2])));
        }
        works.sort((o1, o2) -> o1.start.compareTo(o2.start));
        
        LocalTime prevTime, curTime;
        stack.push(works.get(0));
        prevTime = works.get(0).start;
        int idx = 0;
        
        for (int i = 1; i < plans.length; i++) {
        	curTime = works.get(i).start;
        	
        	int diff = (int)Duration.between(prevTime, curTime).toMinutes();
        	
        	while (!stack.isEmpty() && diff >= stack.peek().time) {
        		diff -= stack.peek().time;
        		answer[idx++] = stack.pop().name;
        	} //while - 사이 시간 과제 처리
        	
        	if (!stack.isEmpty()) {
        		stack.peek().time -= diff;
        	} //if - 남은 시간 보다 과제에 요구되는 시간이 더 큰 경우
        	
        	stack.push(works.get(i));
        	prevTime = curTime;
        } //for
        
        while(!stack.isEmpty()) {
        	answer[idx++] = stack.pop().name;
        } //while - 남아 있는 과제들 처리
        
        return answer;
    }
}
