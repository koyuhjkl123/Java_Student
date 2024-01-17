package com.kediuts;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Exam03_TokenPrintTest {

	public static void main(String[] args) {

		String s = "of the people, by the people, for the people";

		try {
			showTokens(s, ",");
		} catch (NoSuchElementException e) {
			System.out.println("끝");
		}

	}

	public static String showTokens(String s, String g) throws NoSuchElementException {
//		방법 2
		String arr = s.replace(g, ""); // 문자열 ,(쉼표) 제거
		StringTokenizer st = new StringTokenizer(arr); // 교재 354페이지 참조
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		return st.nextToken();

	}
	
	
//	public static void main(String[] args) {
//		방법 1
//		String s = "of the people, by the people, for the people";
//
//		try {
//			showTokens(s, ", ");
//		} catch (Exception e) {
//			System.out.println("끝");
//		}
//
//	}
	
//	public static String[] showTokens(String s, String g) throws Exception {
//		
//		String arr = s.replace(",", "");
//		String[] arrs = arr.split(" ");
//
//		while (true) {
//			for (String x : arrs) {
//				System.out.println(x);
//			}
//			System.out.println(arrs[1000]);
//		}
//
//	}

}
