
# 들어가며
기본기는 가장 중요하게 생각되기 때문에 자바 기본기를 단단히 다지고자 챕터별로 글로 정리하면서
<br>
좀 더 디테일하게 알 수 있고 까먹지 않기 위함

<br>

# 변수
값을 저장할 수 있는 저장공간

<br>

## 변수 타입
byte : 데이터의 기본 단위 중 하나이며, 0 또는 1의 값을 가질 수 있는 최소 단위 8개의 비트를 모아 하나의 '1바이트'로 형성됨
- boolean : True(참), false(거짓)을 표현
- String : 문자열을 표현
- char : 단일문자를 표현하는 2byte
- chort : 2byte
- int : 정수를 표현하는 4byte
- long : 정수를 표현하는 8byte
- float : 실수를 표현하는 4byte
- double : 실수를 표현하는 8byte

<br>

## 상수
변수와 동일하게 '값을 저장할 수 있는 저장공간'이지만 변수와 다르게 한번 값을 저장하면 값의 변경이 불가능하며,
'final'만 붙이면 된다

예 : final int SPEED = 10;

<br>

## 두 변수 값 바꾸기
임시로 값을 저장할 tmp 변수 생성 후 아래와 같이 진행
<br>

<details>
    <summary>코드 보기(html)</summary>

```java
int x = 10;
int y = 5;
int tmp;

tmp = x;
x = y;
y = tmp;
```

</details>
