package com.keduits;

import java.text.MessageFormat;

public class Exam07_MessageFormatTest {

	public static void main(String[] args) {

		Object[][] data = { { "세종대왕", 1, "조선" }, { "오바마", 2, "미국" }, { "징기스칸", 3, "몽고" } };

		for (int i = 0; i < data.length; i++) {
			System.out.print(MessageFormat.format("이름 : {0}\t\t 번호 : {1}\t\t 국적 : {2}\n", data[i]));
		}

	}

}
