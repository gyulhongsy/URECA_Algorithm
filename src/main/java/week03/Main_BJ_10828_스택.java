package week03;

import java.util.Scanner;
import java.util.Stack;

public class Main_BJ_10828_스택 {

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
