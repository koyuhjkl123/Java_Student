package com.keduits;

import java.util.Arrays;

public class BookTest {

	public static void main(String[] args) {
		
		Book[] book = { 
			new Book(15000),
			new Book(50000),
			new Book(20000)
		};
		int[] count = new int[3];
		System.out.println("정렬 전");
		for(Book b: book) {
			b.sort();
		}
		Arrays.sort(book); // compareTo 오버라이딩된 메소드가 있기 때문에
//		정렬이 가능하다.
		
		System.out.println("정렬 후");
		for(int i=0; i < count.length; i++) {
			book[i].sort();
		}

	}

}
