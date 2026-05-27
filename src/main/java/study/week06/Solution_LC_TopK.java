package study.week06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution_LC_TopK {
    static public int[] topKFrequent(int[] nums, int k) {
        int[] answer = new int[k];

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        } //for - insert map

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((k1, k2) -> map.get(k2) - map.get(k1));

        for (int i = 0; i < k; i++) {
            answer[i] = list.get(i);
        } //for

        return answer;
    }

    static void main() {
        int[] nums = {1,2,1,2,1,2,3,1,3,2};
        int k = 2;

        System.out.println(topKFrequent(nums, k));
    }
}
