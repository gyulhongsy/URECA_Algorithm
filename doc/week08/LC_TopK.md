# LeetCode Top K

[문제 링크](https://leetcode.com/problems/top-k-frequent-elements/)

## 접근
- 해시 맵을 만들어 주어진 배열의 숫자를 키로 하는 맵 생성
- 배열을 돌면서 해시 맵에 데이터를 넣어줌
- 해시 맵의 value 값을 기준으로 정렬, 상위 k개 리턴

### 정답 코드
```
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
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
}
```

### 회고
- 맵 자체를 value 값을 기준으로 정렬할 수 없어서 따로 키값을 저장한 배열을 만든 뒤, 이 배열을 맵의 value 값을 기준으로 정렬하였다.
- 문제에 따라 적절한 자료구조를 선택하는 것도 중요하다는 것을 배웠다.
