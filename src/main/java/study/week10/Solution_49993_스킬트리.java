package study.week10;

import java.util.ArrayList;
import java.util.List;

public class Solution_49993_스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String str : skill_trees) {
            boolean isMatched = true;
            int prevIdx = -1; //앞선 스킬의 위치
            boolean isMissed = false; //선행 스킬을 건너뛰었는지 체크

            for (char c : skill.toCharArray()) {
                int curIdx = str.indexOf(c);

                if (curIdx == -1) { //현재 스킬을 안배움
                    isMissed = true;
                } else { //현재 스킬을 배움
                    if (isMissed || curIdx < prevIdx) {
                        isMatched = false;
                        break;
                    } //if - 선행 스킬을 안배웠거나 순서가 바뀜

                    prevIdx = curIdx; //무사히 통과 -> 다음 스킬과 비교하기 위해 현재 위치 저장
                } //if
            } //for - 스킬 한 글자씩 검사

            if (isMatched) answer++;
        } //for - 스킬 트리에서 하나씩 검사

        return answer;
    }
}
