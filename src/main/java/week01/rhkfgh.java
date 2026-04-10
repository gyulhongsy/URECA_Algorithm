package week01;

import java.util.Scanner;

public class rhkfgh {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int num = scan.nextInt();
		scan.nextLine();
		
		for(int i = 0; i < num; i++) {
			int cnt = 0;
			boolean isValid = false;
			
			String str = scan.nextLine();
			
			for(int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == '(') cnt++;
				else cnt--;
				
				if (cnt < 0) break;
			} // for
			if (cnt == 0) isValid = true;
			
			if (isValid) System.out.println("YES");
			else System.out.println("NO");
			
		} // for
		
		scan.close();
	} // main

}
