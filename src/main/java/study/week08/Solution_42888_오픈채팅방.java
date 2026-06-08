package study.week08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_42888_오픈채팅방 {
    public String[] solution(String[] record) {
        String[] answer = {};

        HashMap<String, String> names = new HashMap<>();
        List<String[]> inout = new ArrayList<>();

        for (String str : record) {
            StringTokenizer st = new StringTokenizer(str, " ");
            String order = st.nextToken();
            String id = st.nextToken();
            String name;

            switch (order) {
                case "Enter":
                    name = st.nextToken();
                    inout.add(new String[]{id, "님이 들어왔습니다."});
                    names.put(id, name);
                    break;
                case "Leave":
                    inout.add(new String[]{id, "님이 나갔습니다."});
                    break;
                case "Change":
                    name = st.nextToken();
                    names.replace(id, name);
                    break;
            } //switch - order
        } //for

        answer = new String[inout.size()];
        for (int i = 0; i < inout.size(); i++) {
            String id = inout.get(i)[0];
            String log = inout.get(i)[1];
            answer[i] = names.get(id) + log;
        } //for - input answer

        return answer;
    } //solution
}
