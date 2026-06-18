package study.week10;

public class Solution_49993_스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        //남길 문자들(스킬)을 제외한 나머지 모든 문자를 찾는 정규식 생성
        String regex = "[^" + skill + "]";

        for (String st : skill_trees) {

            //스킬트리에서 skill과 상관없는 잡다한 스킬들을 빈 문자열로 치환하여 모두 제거
            String filtered = st.replaceAll(regex, "");

            //남은 스킬들이 정해진 순서인지 확인
            if (skill.startsWith(filtered)) answer++;
        }

        return answer;
    }
}
