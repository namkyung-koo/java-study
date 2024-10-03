### String 클래스를 통해 문자열을 생성하는 방법
- 쌍따옴표 사용: "hello"
- 객체 생성 new String("hello");
- String은 참조형이므로 참조값만 들어갈 수 있다. 따라서 아래 코드는 뭔가 어색하다.
``` java
String str = "hello";
```

- 문자열을 매우 자주 사용되기 때문에 편의상 쌍따옴표로 문자열을 감싸면 자바 언어에서 아래와 같이 변경해준다.
``` java
String str = new String("hello");
```

### String 클래스의 속성(필드)
``` java
private final char[] value; // 자바 9 이전
private final byte[] value; // 자바 9 이후
```

- 자바에서 char는 2byte를 차지한다.
- 영어나 숫자는 보통 1byte로 표현이 가능하다.
- 단순 영어, 숫자로만 표현된 경우 1byte를 사용하고, 그렇지 않은 경우 2byte인 UTF-16 인코딩을 사용한다.
- 따라서 메모리를 더 효율적으로 사용할 수 있게 변경되었다.

### 기능(메서드)
- length(): 문자열의 길이를 반환한다.
- charAt(int index): 특정 인덱스의 문자를 반환한다.
- substring(int beginIndex, int endIndex): 문자열의 부분 문자열을 반환한다.
- indexOf(String str): 특정 문자열이 시작되는 인덱스를 반환한다.
- toLowerCase(), toUpperCase(): 문자열을 소문자 또는 대문자로 변경한다.
- trim(): 문자열 양 끝의 공백을 제거한다.
- concat(String str): 문자열을 더한다.

### String 클래스의 비교
String 클래스를 비교할 때는 `==` 비교가 아니라 항상 `equals()` 비교를 해야한다.
- 동일성(Identity): `==`연산자를 사용해서 두 객체의 참조가 동일한 객체를 가리키고 있는지 확인
- 동등성(Equality): `equals()` 메서드를 사용하여 두 객체가 논리적으로 같은지 확인

### 문자열 리터럴과 문자열 풀(String Pool)
- 자바가 실행되는 시점에 클래스에 문자열 리터럴이 있으면 문자열 풀에 String 인스턴스를 미리 만들어둔다.
- 이때 같은 문자열이 있으면 만들지 않는다.
- String 인스턴스가 같은 문자열 리터럴은 사용하면 문자열 풀에서 같은 String 인스턴스의 참조값을 반환한다.
- 문자열 풀 덕분에 같은 문자를 사용하는 경우 메모리 사용을 줄이고 성능도 최적화할 수 있다.
- 문자열 풀은 힙 영역을 사용한다.
- 탐색 시 해시 알고리즘을 사용하기 때문에 매우 빠른 속도로 String 인스턴스를 찾을 수 있다.

### String 클래스가 불변으로 설계된 이유
- 사이드 이펙트 방지
- 예) 문자열 풀에서 같은 문자를 참조하는 변수의 모든 문자가 함께 변경되어 버리는 문제가 발생

### 불변인 String 클래스의 단점
``` java
String str = "A" + "B" + "C" + "D";
String str = String("A") + String("B") + String("C") + String("D");
String str = new String("AB") + String("C") + String("D");
String str = new String("ABC") + String("D");
String str = new String("ABCD");
```

- 중간에 만들어진 `new String("AB")`, `new String("ABC")`는 제대로 사용되지도 않고, GC의 대상이 된다.

### StringBuilder(가변 String 클래스)
- `StringBuilder`는 문자열을 변경하는 동안만 사용하다가 문자열 변경이 끝나면 안전한 `String`으로 변환하는 것이 좋다.
- `StringBuilder`의 `toString()` 메서드 사용

### StringBuilder를 직접 사용하는 것이 더 좋은 경우
- 반복문에서 반복해서 문자를 연결할 때
- 조건문을 통해 동적으로 문자열을 조합할 때
- 복잡한 문자열의 특정 부분을 변경해야 할 때
- 매우 긴 대용량 문자열을 다룰 때

### 메서드 체이닝 - Methode Chaining
``` java
private int value;

public ValueAdder add(int addValue) {
    value += addValue;
    return this;
}
```

``` java
public static void main(String[] args) {
    ValueAdder adder = new ValueAdder();
    int result = adder.add(1).add(2).add(3).getValue();
    System.out.println("result = " + result);
}
```
실행 결과
``` java
result = 6
```

메서드 체이닝 기법은 코드를 간결하고 읽기 쉽게 만들어준다.

### StringBuilder와 메서드 체인
- `StringBuilder`는 메서드 체이닝 기법을 제공한다.
- 예) `insert()`, `delete()`, `reverse()`