package com.keduit;

import java.util.stream.IntStream;

public class _07_LazinessTest {

	public static void main(String[] args) {

		IntStream is = IntStream.rangeClosed(1, 10);

		is.filter(x -> {
			System.out.println("filter : " + x);
			return x % 2 == 0;
		}).map(x -> {
			System.out.println("map : " + x);
			return x * x;
		}).forEach(x-> {
			System.out.println("forEach : " + x);
		});

	}

}
