### java.lang 패키지
자바 언어를 이루는 가장 기본이 되는 클래스들을 보관하는 패키지

### java.lang 패키지의 대표적인 클래스
- Object: 모든 자바 객체의 부모 클래스
- String: 문자열
- Integer, Long, Double: 래퍼 타입, 기본형 데이터 타입을 객체로 만든 것
- Class: 클래스 메타 정보
- System: 시스템과 관련된 기본 기능들을 제공

#### java.lang 패키지는 자동으로 import 되기 때문에 임포트 구문을 생략해도 된다.

### Object 클래스
- 자바에서 모든 클래스의 최상위 부모 클래스
- 즉, 부모가 없으면 묵시적으로 Object 클래스를 상속 받는다.
- extends Object 구문 생략을 권장

### 자바에서 Object 클래스가 최상위 부모 클래스인 이유
- 공통 기능 제공
  - 객체의 정보를 제공하는 toString()
  - 객체의 같음을 비교하는 equals()
  - 객체의 클래스 정보를 제공하는 getClass()
  - 기타 여러가지 기능
- 다형성의 기본 구현
  - Object 클래스로 모든 객체에 대한 다형적 참조 가능
  - 대신 자식 인스턴스의 메서드를 호출하기 위해서는 다운캐스팅을 해야한다.

### Object가 없다면 ?
- void action(Object obj)과 같이 모든 객체를 받을 수 있는 메서드를 만들 수 없다.
- Object[] objects처럼 모든 객체를 저장할 수 있는 배열을 만들 수 없다.

### toString()
- 객체의 정보를 문자열 형태로 제공한다. 디버깅과 로깅에 유용화게 사용된다.
``` java
public String toString() {
    return getClass().getName() + '0' + Integer.toHexString(hashcode());
}
```

- System.out.println() 메서드는 사실 내부에서 toString()을 호출한다.

### 참고 - 객체의 참조값 직접 출력
toString()이나 hashcode()를 재정의하면 객체의 참조값을 출력할 수 없다. 이때는 다음 코드를 사용하면 객체의 참조값을 출력할 수 있다.
``` java
String refValue = Integer.toHexString(System.identityHashCode(dog));
System.out.println("refValue = " + refValue);
```

실행 결과
``` java
refValue = 72ea2f77
```

### equals() - 동일성과 동등성
Object는 동등성 비교를 위한 equals() 메서드를 제공한다.
- 동일성(Identity): == 연산자를 사용해서 두 객체의 참조가 동일한 객체를 가리키고 있는지 확인
- 동등성(Equality): equals() 메서드를 사용하여 두 객체가 논리적으로 동등한지 확인

### Object.equals() 메서드
``` java
public boolean equals(Object obj) {
    return (this == obj);
}
```
- Object가 기본으로 제공하는 equals()는 == 으로 동일성을 비교한다.
- 따라서 동등성 비교를 하고 싶으면 equals() 메서드를 재정의해야 한다.
