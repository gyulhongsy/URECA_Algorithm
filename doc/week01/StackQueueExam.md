## 백준 10828번 - 스택

### 문제
정수를 저장하는 스택을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.

명령은 총 다섯 가지이다.

- push X: 정수 X를 스택에 넣는 연산이다.
- pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
- size: 스택에 들어있는 정수의 개수를 출력한다.
- empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
- top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.

### 입력
첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 
둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 
주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 
문제에 나와있지 않은 명령이 주어지는 경우는 없다.

### 출력
출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.

### 정답 코드 (java)
```
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] str = new String[2];
		Stack<Integer> stack = new Stack<>();
		
		int N = scan.nextInt();
		scan.nextLine();
		
		for(int i = 0; i < N; i++) {
			str = scan.nextLine().split(" ");
			
			switch(str[0]) {
			case "push": stack.push(Integer.parseInt(str[1])); break;
			case "pop": 
				if (stack.isEmpty()) System.out.println("-1");
				else System.out.println(stack.pop());
				break;
			case "size": System.out.println(stack.size()); break;
			case "empty":
				if (stack.isEmpty()) System.out.println("1");
				else System.out.println("0");
				break;
			case "top": 
				if (stack.isEmpty()) System.out.println("-1");
				else System.out.println(stack.peek());
				break;
			} // switch
		} // for
		
		scan.close();
	} //main

}
```
### 회고
- 첫 줄에 주어지는 명령의 수를 제외하곤 한 줄씩 입력 받아 배열로 처리하였다.
- switch ~ case 문으로 명령의 종류를 판단하였다.
- `push()` 함수는 데이터를 넣고 출력도 해주므로 따로 출력 문을 작성할 필요가 없었다.
- `pop()` `peek()` 함수는 스택이 비어 있을 경우 오류가 발생해 비어 있는 경우를 따로 처리 해 주었다.
---
## 백준 10845번 - 큐

### 문제
정수를 저장하는 큐를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.

명령은 총 여섯 가지이다.

- push X: 정수 X를 큐에 넣는 연산이다.
- pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
- size: 큐에 들어있는 정수의 개수를 출력한다.
- empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
- front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
- back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.

### 입력
첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 
둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 
주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 
문제에 나와있지 않은 명령이 주어지는 경우는 없다.

### 출력
출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.

### 정답 코드 (java)
```
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] str = new String[2];
		LinkedList<Integer> que = new LinkedList<>();
		
		int N = scan.nextInt();
		scan.nextLine();
		
		for(int i = 0; i < N; i++) {
			str = scan.nextLine().split(" ");
			
			switch (str[0]) {
			case "push": que.offer(Integer.parseInt(str[1])); break;
			case "pop": 
				if (que.isEmpty()) System.out.println("-1");
				else System.out.println(que.poll()); break;
			case "size": System.out.println(que.size()); break;
			case "empty":
				if (que.isEmpty()) System.out.println("1");
				else System.out.println("0"); break;
			case "front":
				if (que.isEmpty()) System.out.println("-1");
				else System.out.println(que.getFirst()); break;
			case "back":
				if (que.isEmpty()) System.out.println("-1");
				else System.out.println(que.getLast()); break;
			} // switch
		} // for
		
		scan.close();
	} // main

}
```
### 회고
- 예제 입력과 출력, 명령 판단 부분은 스택 문제와 동일하게 구현하였다.
- `Queue`를 사용하려 했으나 rear을 받아오기 위해 `LinkedList`를 사용하여 구현하였다.
---
