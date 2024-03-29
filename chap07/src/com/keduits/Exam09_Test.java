package com.keduits;

public class Exam09_Test {
	
	int index; // 객실 번호
	String name; // 객실 고객 이름
	String[] names = new String[10]; // 객실 방 10개


	void add(int index, String name) { // 객실 예약
		this.index = index;
		this.name = name;

//		if (names[this.index - 1] != null)은 해당 객실 번호에 이미 예약된 고객이 있는지 확인

//		(true)이미 예약된 경우: 이미 예약된 고객의 이름과 함께 해당 객실이 이미 예약되었다고 출력
//		(false)예약되지 않은 경우: 해당 객실을 현재 예약하려는 고객으로 예약하고, 예약 완료 메시지를 출력
		if (names[index - 1] != null) {
			show();
		} else {
			names[index - 1] = name;
			show();
		}
	}

	void show() {
		
		for (int i = 0; i < names.length; i++) {
			if (names[i] != null) { // String 문자열 값이 null 아니라면 출력, null 이라면 출력 하지않음
				System.out.printf("%d번 방은 이미 예약되어 예약이 불가합니다. \n", index);
			}else {
				System.out.printf("%d번 방을 %s가 예약했습니다.\n", index, name);
				break;
			}

		}
	}

}
