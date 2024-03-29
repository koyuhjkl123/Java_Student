package com.keduits;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Exam09_IntStreamTest {

	public static void main(String[] args) {

		Set<IntStream[]> result = new HashSet<>();


		 IntStream.range(1, 25)
         .boxed() // IntStream -> Stream(Integer) 변환
         .flatMap(x -> // flatMap : 평면화 시킨다.
                 IntStream.range(x, 25) // x변수를 넣은 이유 중복을 피하기 위해서
//               mapToObj : 반환타입 Stream<Integer> 새로운 스트림 타입으로 변환한다.
//               새로운 int[] 배열을 0번지 x의 값, 1번지 y의 값, 2번지 : x, y의 제곱값이 더한 값
//               2번지의 값은 피타고라스 정리의 기준값, z변수의 근사값은 아니다.
                         .mapToObj(y -> new int[]{x, y, (int) Math.sqrt(x * x + y * y)})
         )// filter메서드를 활용하여 피타고라스 정리의 x, y, z의 값에 대한 조건을 걸러준다.
         .filter(arr -> arr[2] * arr[2] == arr[0] * arr[0] + arr[1] * arr[1])
         .distinct() // 고유한 값, 중복제거
         .limit(5) // 5개의 값만 호출
         .forEach(r-> System.out.print("("+r[0]+", " + r[1] + ", " + r[2]+ ")")); // 피타고라스 활용한 반환값을 Set 집합에 대입
		 
//		 .forEach(result:add); <- 아래 코드는 set을 add할 경우 성립되는 코드입니다. 
//		 result.stream().sorted() // 반환된 result 변수를 stream()하고, forEach 활용하여 각 값을 반환한다.
//		 .forEach(r-> System.out.print("("+r[0]+", " + r[1] + ", " + r[2]+ ")"));
		 
	}
	
	
	

}
