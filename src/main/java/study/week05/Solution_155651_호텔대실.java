package study.week05;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution_155651_호텔대실 {
	
	static class Book {
		LocalTime start;
		LocalTime end;
		
		public Book(LocalTime start, LocalTime end) {
			this.start = start;
			this.end = end;
		} //Constructor
	} //class - Book
	
	public int solution(String[][] book_time) {
        int answer = 0;
        ArrayList<Book> books = new ArrayList<>();
        
        for (String[] s : book_time) {
        	LocalTime start = LocalTime.parse(s[0]);
        	LocalTime end = LocalTime.parse(s[1]);
        	
        	books.add(new Book(start, end));
        } //for - insert books
        books.sort((o1, o2) -> o1.start.compareTo(o2.start));
        
        PriorityQueue<LocalTime> pq = new PriorityQueue<>();
        
        for (Book b : books) {
        	if (!pq.isEmpty() &&
        			!pq.peek().plusMinutes(10).isAfter(b.start)) {
        		pq.poll();
        	} //if
        	
        	pq.add(b.end);
        } //for - b
        answer = pq.size();
        
        return answer;
    }
}
