package study.week06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_LC_3Sum {
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
