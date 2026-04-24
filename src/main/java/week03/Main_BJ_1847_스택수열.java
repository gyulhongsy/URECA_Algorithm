package week03;

import java.util.Scanner;
import java.util.Stack;

public class Main_BJ_1847_스택수열 {

	static int N;
	static int[] input;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		N = scan.nextInt();
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = scan.nextInt();
		} //for - input
		scan.close();
		
		for (int i = 0, j = 1; i < N; i++) {
			while (j <= input[i]) {
				stack.push(j);
				j++;
				sb.append("+").append("\n");
			} //while - push
			
			if (input[i] == stack.peek()) {
				stack.pop();
				sb.append("-").append("\n");
			} else break;
		} //for
		
		if (stack.size() == 0) {
			System.out.println(sb);
		} else System.out.println("NO");
		
	} //main
}
