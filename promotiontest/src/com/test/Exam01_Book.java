package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Exam01_Book {

	String title; // 책 제목
	String[] names; // 여러권의 책
	String add_book; // 책을 추가할 book 정보를 저장
	String remove_book; // 책을 삭제할 book 정보를 저장
	boolean book_isrental; // 책 대여 여부
	String rental_names_true;
	String rental_names_false;

	StringBuilder book_data;
	Exam01_Book_Rental book_rental;

	List<Exam01_Book> books = new ArrayList<Exam01_Book>();

	public Exam01_Book() {

//		보유 서적 : 예제로 배우는 Java, HTML 웹페이지 만들기, 슬램덩크, 자료구조
		books.add(new Exam01_Book("예제로 배우는 Java", false));
		books.add(new Exam01_Book("HTML 웹페이지 만들기", false));
		books.add(new Exam01_Book("슬램덩크", false));
		books.add(new Exam01_Book("자료구조", false));
	};

	public Exam01_Book(String title, boolean isrental) {
		this.title = title;

	}

	public Exam01_Book(Exam01_Book_Rental book_rental) {
		this.book_rental = book_rental;
	}

	public String getTitle() {
		return title;
	}

//	새로운 책을 등록 / 기존의 있던 Book을 검사
	public StringBuilder BookAdd(String book1) {
		StringBuilder ab = new StringBuilder();
		add_book = book1; // 추가할 책을 저장

		boolean isbook = false;

		for (int i = 0; i < books.size(); i++) {
			if ((books.get(i).getTitle().equals(book1))) {
				isbook = true;
				break;
			}
		}
		if (!isbook) {
			books.add(new Exam01_Book(book1, isbook));
			book_data = ab.append("== 책 추가 ==\n\n");
			add_book = null;
			return ab;
		}
		return ab;
	}

	// 등록된 책을 삭제
	public void BookrRemove(String book1) {
		remove_book = book1;
		StringBuilder ab = new StringBuilder();

		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(book1)) {
				books.remove(i);
				book_data = ab.append("== 책 삭제 ==\n\n");
				break;
			}
		}
	}

	@Override
	public String toString() { // 책을 출력
		StringBuilder ab = new StringBuilder();

//		책 각 추가 및 삭제에 대한 정보가 없으면 기존 정보 출력
		if ((add_book == null && remove_book == null)) {
			ab.append("== 카페 정보 ==\n");

		} else if ((add_book != null && remove_book == null)) {
			ab.append(book_data); // 책추가
			ab.append("== 카페 정보 ==\n");
			add_book = null;

		} else if (add_book == null && remove_book != null) {
			ab.append(book_data); // 책 삭제
			ab.append("== 카페 정보 ==\n");
			remove_book = null;
		} else if ((book_rental.getIsRental())) {
			ab.append(book_rental.book_name + "책 을 대여 완료하였습니다.");
			ab.append("대여기간 : " + book_rental.getToday() + "입니다.");
			ab.append("== 카페 정보 ==\n");
		}

		ab.append(String.format("이름 : %s\n", "IT 카페")); // IT 카페
		ab.append(String.format("주소 : %s\n", "신림역 3번 출구")); // 신림역 3번 출구
		ab.append("보유 서적 : \n");
		System.out.println(book_rental.book_isrental);
		Map<String, Boolean> sm = books.stream().
				collect(Collectors.groupingBy(t -> {
							for(int i=0; i < books.size(); i++) {
								if(books.get(i).book_isrental) {
									return "대여 불가";
								}else {
									return "대여 가능";
								}
							}
						}), 
						);
				
//		.forEach(book -> ab.append(book.getTitle() + " : " + book.book_isrental + "\n"));

		return ab.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Exam01_Book book = (Exam01_Book) o;
		return Objects.equals(getTitle(), book.getTitle());
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

}
