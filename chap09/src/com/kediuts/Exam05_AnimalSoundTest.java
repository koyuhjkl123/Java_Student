package com.kediuts;

import java.util.ArrayList;
import java.util.List;

interface Animal{
	void sound();
}

class Dog implements Animal{

	@Override
	public void sound() {
		System.out.println("멍멍~~");
		
	}
	
}
class Cuckoo implements Animal{

	@Override
	public void sound() {
		System.out.println("뻐꾹뻐꾹~~");
	}
	
}
public class Exam05_AnimalSoundTest{
//	  제네릭 T타입인 Animal클래스에게 상속받은 타입만 들어 올 수 있다.
//	  printsound(List<T> List타입을 한 이유 : 메인 메소드에서 타입이 List라서
//	배열타입으로 할 경우 오류 발생
	public static <T extends Animal> void printSound(List<T> a) {
		for(T t: a) { // 업캐스팅!
//			T Animal 클래스이라면
//			Animal t = Dog(); Animal타입인 t가 Dog()클래스를 참조하는것
			t.sound();
		}
		
	}

	public static void main(String[] args) {

//		객체형을 담은 배열리스트
		List<Animal> lists = new ArrayList<>();
		lists.add(new Dog());
		lists.add(new Cuckoo());
		printSound(lists);
		
		List<Dog> dogs = new ArrayList<Dog>();
		dogs.add(new Dog());
//		dogs.add(new Cuckoo()); // Dog클래스와 형제관계이므로 오류 발생!!
		printSound(dogs);
	}

}