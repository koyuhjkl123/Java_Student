package com.kediuts;


public class Exam06_Max<T> {

//	Number 클래스와 Double클래스는 형제관계이므로 instanceof로 타입인지를 확인해줘야한다.
	public T max(T a, T b){
		if(a instanceof Number && b instanceof Number) {
			if(a instanceof Double && b instanceof Double) {
				Double a1 = (Double) a; // T타입인 a를 Double타입으로 형변환
				Double b1 = (Double) b; // T타입인 a를 Double타입으로 형변환
//				반환타입이 T여서 반환 시 T로 형변환 후 반환
				return (T) doubleValue(a1, b1);
			}else {// Double타입이 아니면서, Number클래스 경우
				Number a1 = (Number) a; // Number클래스로 형변환
				Number b1 = (Number) b; // Number클래스로 형변환
				return (T) intValue(a1, b1); // 반환 시 T타입 형 변환
			}
//			String 타입인지 확인
		}else if (a instanceof String && b instanceof String){
			String a1 = (String) a; // String 타입으로 형변환
			String b1 = (String) b; // String 타입으로 형변환
			
			if(a1.length() > b1.length()) {
				return a;
			}else {
				return b;
			}
			
		} // Number타입이 아닌경우 
		return b;
	}
	public Number intValue(Number a, Number b) { // 정수값일 경우
		if(a.intValue() > b.intValue()) { // intValue : int정수값을 반환
			return a;
		}else {
			return b;
		}
	}
	
	public Double doubleValue(Double a, Double b) { // 실수값일 경우
		if(a.doubleValue() > b.doubleValue()) { // doubleValue : double실수값을 반환
			return a;
		}else {
			return b;
		}
	}

}
