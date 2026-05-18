package study.week05;

public class Solution_131127_할인행사 {
	static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        for (int i = 0; i <= discount.length - 10; i++) {
            int cnt = 0;
    		int[] tmpNum = number.clone();   		
        	for (int j = i; j < i + 10; j++) {
        		for (int k = 0; k < want.length; k++) {
        			if (discount[j].equals(want[k]) && tmpNum[k] > 0) {
        				cnt++;
        				tmpNum[k]--;
        			}
        		} //for - want
        	} //for - j
        	
        	if (cnt == 10) answer++;
        } //for - i
        
        return answer;
    }
	
	public static void main(String[] args) {
		String[] want = {"banana", "apple", "rice", "pork", "pot"};
		int[] number = {3, 2, 2, 2, 1};
		String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
		
		solution(want, number, discount);
	}
}
