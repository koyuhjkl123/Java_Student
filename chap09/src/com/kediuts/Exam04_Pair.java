package com.kediuts;

public class Exam04_Pair<T> {
	
	T a; // 정수
	T b; // 실수
	
	
	public Exam04_Pair(T a, T b) { // 정수형 생성자
		this.a = a;
		this.b = b;
	}
	
	
	
	T first() { // 인스턴스 메서드
		return getA();
	}
	
	T second() { // 인스턴스 메서드
		return getB();
	}
	
	
	
	public T getA() { // 값 접근자
		return a;
	}
	
	public T getB() { // 값 접근자
		return b;
	}
	

}
