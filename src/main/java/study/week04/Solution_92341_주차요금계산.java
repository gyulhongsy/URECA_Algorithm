package study.week04;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution_92341_주차요금계산 {
	public int[] solution(int[] fees, String[] records) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        HashMap<String, LocalTime> inTime = new HashMap<>();
        TreeMap<String, Integer> totalTime = new TreeMap<>();
        
        for (int i = 0; i < records.length; i++) {
        	StringTokenizer st = new StringTokenizer(records[i], " ");
        	LocalTime time = LocalTime.parse(st.nextToken());
        	String num = st.nextToken();
        	String io = st.nextToken();
        	
        	if (io.equals("IN")) inTime.put(num, time);
        	else if (io.equals("OUT")) {
        		int diffTime = (int)Duration.between(inTime.get(num), time).toMinutes();        		
        		totalTime.put(num, totalTime.getOrDefault(num, 0) + diffTime);
        		inTime.remove(num);
        	} //if ~ else if
        } //for
        
        if (!inTime.isEmpty()) {
        	LocalTime lastTime = LocalTime.parse("23:59");
        	for (Map.Entry<String, LocalTime> entry : inTime.entrySet()) {
        		String num = entry.getKey();
        		LocalTime time = entry.getValue();
        		
        		int diffTime = (int)Duration.between(time, lastTime).toMinutes();
        		totalTime.put(num, totalTime.getOrDefault(num, 0) + diffTime);
        	} //for each - map
        } //if
        
        int[] answer = new int[totalTime.size()];
        int i = 0;
        for (int time : totalTime.values()) {
        	int fee = 0;
        	if (time <= baseTime) fee = baseFee;
        	else fee = (int)(baseFee + Math.ceil((double)(time - baseTime) / unitTime) * unitFee);
        	answer[i++] = fee;
        }
        return answer;
    } //solution
}
