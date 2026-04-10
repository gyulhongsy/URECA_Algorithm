package week01;

import java.util.LinkedList;
import java.util.Scanner;

public class QueueTest {

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
