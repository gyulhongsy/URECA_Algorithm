package week04;

import java.util.TreeSet;

public class Solution_PS_68644_두개뽑아서더하기 {

	public int[] solution(int[] numbers) {
        TreeSet<Integer> answer = new TreeSet<>();
        
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i+1; j < numbers.length; j++) {
            	answer.add(numbers[i] + numbers[j]);
            }
        } //for
        
        return answer.stream().mapToInt(Integer::intValue).toArray();

    }
}
/*
회고
- 처음에는 해쉬셋을 사용했으나 해쉬 셋은 충복 관리만 하고 정렬은 따로 해줘야 했다.
- 트리셋을 사용하여 중복 관리+자동 정렬 두 가지를 모두 해결하였다.
 */
