package com.keduits;

public class Exam08_Arrays {

	public static void main(String[] args) {

		String arrays = "Empty vessels make the most sound.";
		int arrays_count = 0;
//		Empty vessels make the most sound.

//		split : arrays 변수 문자열을 띄어쓰기 기준으로 문자열을 분리하여 배열로 만듬
		String[] array = arrays.split(" "); 


		for (int i = 0; i < array.length; i++) {
			arrays_count++; // 배열 길이만큼 카운팅 하겠다
		}

		System.out.println("입력 : " + arrays); // 문자열
		System.out.println("단어 개수 : " + arrays_count); // 배열의 길이 : 6 출력 
		
		System.out.println("정렬된 토큰 : " + String.join(", ", array));
//		String.join(", ", array) : ", " => String 배열을 쉽표와 공백 기준을 갖고 문자열로 반환

	}

}
