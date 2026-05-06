package week04.study;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_389479_서버증설횟수 {
	public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Queue<Integer> que = new ArrayDeque<>();
        
        for (int t = 0; t < 24; t++) {
        	while (!que.isEmpty() && que.peek() <= t) {
        		que.poll();
        	} //while - remove end server
        	
        	int needServer = players[t] / m;
        	if (needServer > que.size()) {
        		int add = needServer - que.size();
        		answer += add;
        		
        		for (int i = 0; i < add; i++) que.offer(t + k);
        	} //if need server    	
        } //for
        
        return answer;
    }
}
