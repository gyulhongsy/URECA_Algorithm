package week03;

public class PS_주식가격 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
        	int sec = 0;
        	for (int j = i + 1; j < prices.length; j++) {
        		sec++;
        		if (prices[i] > prices[j]) break;
        	} //for - sec
        	answer[i] = sec;
        } //for - answer
        
        return answer;
    }
}
