# LeetCode 3Sum

[문제 링크](https://leetcode.com/problems/3sum/)

## 초기 접근
- nums 배열의 인덱스에서 3개를 뽑아 조합을 만든다
- 만든 인덱스 조합으로 nums배열에 해당하는 숫자들의 합이 0이면 해당 숫자 배열을 리턴한다

### 초기 코드
```
import java.util.ArrayList;
import java.util.List;

class Solution {

    static int N, M;
    static List<Integer> number;

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();

        N = nums.length;
        M = 3;
        number = new ArrayList<>();

        Arrays.sort(nums);

        combination(0, 0, nums, answer);

        return answer;
    }

    private static void combination(int numI, int inI, int[] nums, List<List<Integer>> answer) {
        if (numI >= M) {
            int sum = 0;
            for (int n : number) {
                sum += n;
            } //for - sum

            if (sum == 0) {
                answer.add(new ArrayList<>(number));
            } //if
            return;
        } //if - base case

        for (int i = inI; i < N; i++) {
            if (i > inI && nums[i] == nums[i - 1]) continue; 

            number.add(nums[i]);
            combination(numI+1, i+1, nums, answer);
            number.removeLast();
        } //for
    } //combination
}
```

### 문제점
- 시간 초과

## 개선 방향
- 투 포인터 방식 이용
  1. 주어진 배열을 정렬
  2. 기준점 하나를 찍어 고정
  3. 남은 구간 양 끝에 `left` 와 `right` 포인터 배치
  4. 세 수의 합을 구해 합이 0보다 작다면 left를 오른쪽 한 칸으로 이동, 크다면 right를 왼쪽 한 칸으로 이동

### 개선 코드
```
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; //중복 제거

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) { //find answer
                    answer.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    //left와 right가 가리키는 숫자가 같으면 건너뛰기 (중복 제거)
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    //중복 제거 후 한 칸씩 이동
                    left++; right--;
                } //if - find answer
                else if (sum < 0) left++; //합이 더 작으면 큰 숫자 필요
                else right--; //합이 더 크면 작은 숫자 필요
            } //while - 투 포인터 탐색
        } //for - i

        return answer;
    } //treeSum
}
```

### 회고
- 투 포인터 방식이라는 것을 새롭게 알게 되었다.
- 앞으로는 알고리즘을 짜거나 선택할 때 시간 복잡도도 생각해야겠다.
