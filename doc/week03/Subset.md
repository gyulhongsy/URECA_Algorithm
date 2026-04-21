# 부분집합 (Subset)

## 개념
- 어떤 집합에서 각 원소를 선택하거나 선택하지 않는 모든 경우
- 예) 원소 개수가 n개이면 부분집합의 개수는 2^n
- dfs를 통해 구현
- {1, 2, 3} 에서의 부분집합
  ```
  {}
  {1}
  {2}
  {3}
  {1,2}
  {1,3}
  {2,3}
  {1,2,3}
  ```

## 핵심 구조
- 해당 원소를 선택한 경우와 선택하지 않는 경우로 나누어 재귀함수를 각각 호출한다.
  ```java
  // 원소 선택
  selected[idx] = true;
  subset(idx + 1);

  // 원소 비선택
  selected[idx] = false;
  subset(idx + 1);
  ```
---

# 부분집합 예제 풀이 - 백준 2961번 도영이가 만든 맛있는 음식

## 문제
도영이는 짜파구리 요리사로 명성을 날렸었다. 이번에는 이전에 없었던 새로운 요리에 도전을 해보려고 한다. <br>
지금 도영이의 앞에는 재료가 N개 있다. 도영이는 각 재료의 신맛 S와 쓴맛 B를 알고 있다. 
여러 재료를 이용해서 요리할 때, 그 음식의 신맛은 사용한 재료의 신맛의 곱이고, 쓴맛은 합이다. <br>
시거나 쓴 음식을 좋아하는 사람은 많지 않다. 도영이는 재료를 적절히 섞어서 요리의 신맛과 쓴맛의 차이를 작게 만들려고 한다. 
또, 물을 요리라고 할 수는 없기 때문에, 재료는 적어도 하나 사용해야 한다. <br>
재료의 신맛과 쓴맛이 주어졌을 때, 신맛과 쓴맛의 차이가 가장 작은 요리를 만드는 프로그램을 작성하시오.

## 입력
첫째 줄에 재료의 개수 N(1 ≤ N ≤ 10)이 주어진다. 다음 N개 줄에는 그 재료의 신맛과 쓴맛이 공백으로 구분되어 주어진다. 
모든 재료를 사용해서 요리를 만들었을 때, 그 요리의 신맛과 쓴맛은 모두 1,000,000,000보다 작은 양의 정수이다.

## 출력
첫째 줄에 신맛과 쓴맛의 차이가 가장 작은 요리의 차이를 출력한다. 

## 예제 입출력
<img width="1147" height="587" alt="Image" src="https://github.com/user-attachments/assets/daed3ebb-1487-4bdc-b2f2-4cb4ab837684" />

## 정답 코드 (java)
```
package f_subset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_2961_도영이가만든맛있는음식 {

	static int N;
	static List<int[]> items = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			items.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		} //for
		br.close();
		
		subSet(0, 1, 0);
		System.out.println(min);
		
	} //main
	
	private static void subSet(int idx, int sour, int bitter) {
		if (idx >= N) {
			if (sour != 1 || bitter != 0) { //재료가 없는 경우는 제외
				min = Math.min(min, Math.abs(sour - bitter));
			}
			return;
		} //if - 탈출조건
		
		int[] current = items.get(idx);
		
		//현재 재료를 선택한 경우
		subSet(idx+1, sour * current[0], bitter + current[1]);

		//현재 재료를 선택하지 않은 경우
		subSet(idx+1, sour, bitter);

	} //subSet
}
```

## 회고
- 선택한 원소를 출력할 필요가 없기 때문에 `isSelected` 로 확인할 필요가 없다.
- 재료를 고른 여부는 `sour` , `bitter` 의 값을 매개변수로 바로 바꿔주면서 전달하였다.

## 알고리즘 분류
- 브루트포스 알고리즘
- 비트마스킹
- 백트래킹
