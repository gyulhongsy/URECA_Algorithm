package redbook;

import java.util.PriorityQueue;

public class Solution_실패율 {
	public static void main(String[] args) {
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		int N = 5;
		
		solution(N, stages);
	}
	
	static class Stage implements Comparable<Stage> {
		int num;
		double fail;
		
		public Stage(int num, double fail) {
			this.num = num;
			this.fail = fail;
		} //Constructor
		
		@Override
		public int compareTo(Stage o) {
			if (this.fail == o.fail) return this.num - o.num;
			return Double.compare(o.fail, this.fail);
		} //compareTo
	} //class - Stage
	
	static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        PriorityQueue<Stage> pq = new PriorityQueue<>();
        
        for (int c = 1; c <= N; c++) {
        	double chall = 0;
        	double player = 0;
	        for (int s : stages) {
	        	if (s == c) chall++;
	        	if (s >= c) player++;
	        } //for - 사용자 수 계산
	        double fail = (player == 0) ? 0 : ((chall / player) * 100);
	        
	        pq.add(new Stage(c, fail));
        } //for - 실패율 계산
        
        for (int i = 0; !pq.isEmpty(); i++) {
        	int cur = pq.poll().num;
        	answer[i] = cur;
        } //for
        
        return answer;
    }
}
