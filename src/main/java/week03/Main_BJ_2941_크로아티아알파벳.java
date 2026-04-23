package week03;

import java.util.Scanner;

public class Main_BJ_2941_크로아티아알파벳 {

	static String str;
	static int cnt;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		str = scan.nextLine();
		scan.close();

		cnt = str.length();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '=') {
				if (i >= 2 && (str.charAt(i-1) == 'z' && str.charAt(i-2) == 'd')) cnt -= 2;
				else if (i >= 1) cnt--;
			} 
			else if (i >= 1 && str.charAt(i) == '-') cnt --;
			else if (str.charAt(i) == 'j') {
				if (i >= 1 && (str.charAt(i-1) == 'l' || str.charAt(i-1) == 'n')) cnt--;
			}
		} //for - calc cnt
		
		System.out.println(cnt);
	} //main
}
