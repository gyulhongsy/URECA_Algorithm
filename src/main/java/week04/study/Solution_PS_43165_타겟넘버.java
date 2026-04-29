package week04.study;

public class Solution_PS_43165_타겟넘버 {
	
	static int answer = 0;
    static int N;
    static int[] input = {0, 1};
    static boolean[] selected; //true: + , false: -
	
	public int solution(int[] numbers, int target) {
		
        N = numbers.length;
        selected = new boolean[N];
        
        subset(0, numbers, target);
        
        return answer;
    } //solution

	private void subset(int idx, int[] numbers, int target) {	
		if (idx >= N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (selected[i]) sum += numbers[i];
				else sum -= numbers[i];
			} //for - sum
			if (sum == target) answer++;
			return;
		} //if - 재귀 탈출
		
		selected[idx] = true;
		subset(idx+1, numbers, target);
		
		selected[idx] = false;
		subset(idx+1, numbers, target);
	} //subset

}
