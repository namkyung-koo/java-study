### 다형성 시작
객체 지향 프로그래밍의 대표적인 특징으로는 캡슐화, 상속, 다형이 있다.
그 중에서 다형성은 객체 지향 프로그래밍의 꽃이라 불린다.</br>
</br>
다형성을 이해하기 위한 2가지 핵심 이론
- 다형적 참조
- 메서드 오버라이딩

### 다형적 참조
부모는 자식을 품을 수 있다.</br>
예시) Parent poly = new Child();

그러나 자식의 기능은 호출할 수 없다. 컴파일 오류 발생</br>
예시) poly.childMethod();

- 부모 타입의 변수가 자식 인스턴스를 참조한다.
- Parent poly = new Child();
- Child 인스턴스를 만들었다. 자식 타입인 Child를 생성했기 때문에 메모리 상에 Child와 Parent가 모두 생성된다.
- 생성된 참조값을 Parent 타입의 변수인 poly에 담아둔다.
- 만약 손자가 있다면 손자도 손자 타입도 참조할 수 있다.

### 다형적 참조의 한계
Parent poly = new Child()처럼 자식을 참조한 상황에서 poly가 자식 타입인 Child에 있는 childMethod()를 호출하면 어떻게 될까?

- 호출자인 poly는 Parent 타입이다.
- 따라서 Parent 클래스부터 시작해서 필요한 기능을 찾는다.
- 그러나 상속 관계는 부모 방향으로 찾아 올라갈 수는 있지만 자식 방향으로 찾아 내려갈 수는 없다.
- 따라서 childMethod()를 찾을 수 없다.

### 다운캐스팅
호출하는 타입을 자식인 Child 타입으로 변경하면 인스턴스의 Child에 있는 childMethod()를 호출할 수 있다.

* 다운캐스팅(부모 타입 -> 자식 타입)</br>
Child child = (Child) poly;</br>
child.childMethod();</br>
  ((Child).poly).child.Method(); // 일시적 다운캐스팅</br>

### 다운캐스팅이 가능한 경우
Parent parent1 = new Child();
((Child) parent1).childMethod();

### 다운캐스팅이 불가능한 경우
Parent parent2 = new Parent();
((Child) parent2).childMethod(); // 자식 인스턴스가 생성되지 않았기 때문에 찾을 수 없다.

### 업캐스팅
다운캐스팅과 반대로 현재 타입을 부모 타입으로 변경하는 것
#### 업캐스팅은 생략할 수 있다. 다운캐스팅은 생략할 수 없다. 참고로 업캐스팅은 매우 자주 사용하기 때문에 생략을 권장한다.

### 업캐스팅이 안전하고 다운캐스팅이 위험한 이유
- 객체를 생성하면 해당 타입의 상위 부모 타입은 모두 함께 생성된다.
- 따라서 위로만 타입을 변경하는 업캐스팅은 메모리 상에 인스턴스가 모두 존재하기 때문에 항상 안전하다.
- 반면에 다운캐스팅의 경우 인스턴스에 존재하지 않는 하위 타입으로 캐스팅하는 문제가 발생할 수 있다.

### instanceof
참조하는 대상이 다양하기 때문에 instanceof를 통해 어떤 인스턴스를 참조하고 있는지 확인할 수 있다.

#### 오른쪽 대상이 왼쪽 대상을 담을 수 있는지 생각하면 판단하기 쉽다!
예시)</br>
new Parent() instanceof Parent // true. 같은 타입
new Child() instanceof Parent // true. 부모는 자식을 담을 수 있다.
new Parent() instanceof Child // false. 자식은 부모를 담을 수 없다.
new Child instanceof Child // true. 같은 타입

### 다형성과 메서드 오버라이딩
#### 꼭 기억해야 할 점은 오버라이딩 된 메서드가 항상 우선권을 가진다는 점이다.

예시)</br>
Parent poly = new Child();</br>
poly.method();</br>

- poly.method(): Parent 타입에 있는 method()를 실행하려고 한다.
- 그런데 하위 타입인 Child.method()가 오버라이딩 되어 있다.
- 오버라이딩 된 메서드는 항상 우선권을 가진다.
- 따라서 Parent.method()가 아니라 Child.method()가 실행된다.

### 다형적 참조와 메서드 오버라이딩에서 발생할 수 있는 문제
1. 부모 클래스의 인스턴스를 생성할 수 있는 문제
2. 부모 클래스를 상속 받는 곳에서 메서드 오버라이딩을 하지 않을 가능성

#### 위 문제들이 발생하지 않게 제약을 하는 것이 바로 추상 클래스다!

### 추상 클래스
- 이름 그대로 추상적인 개념을 제공하는 클래스이다. 
- 따라서 실체인 인스턴스가 존재하지 않는다. 
- 대신 상속을 목적으로 사용되고, 부모 클래스 역할을 담당한다.
- 추상 메서드가 하나라도 포함되면 추상 클래스

### 추상 메서드
- 부모 클래스를 상속 받는 자식 클래스가 반드시 오버라이딩 해야 하는 메서드를 부모 클래스에서 정의할 수 있다.
- 따라서 실체가 존재하지 않고
- 메서드 바디가 없다

### 인터페이스
- 모든 메서드가 추상 메서드인 클래스
- 당연히 인스턴스 생성 불가
- 상속시 자식은 모든 메서드를 오버라이딩 해야한다.
- 주로 다형성을 위해 사용된다.
- 인터페이스는 다중 구현(다중 상속)을 지원한다.

### interface 키워드
- interface 키워드를 사용하면 - 추상 메서드의 public abstract 키워드 생략이 가능하다.
- 인터페이스를 상속받을 때는 extends 대신에 implements라는 구현 키워드를 사용해야 한다.

### 인터페이스와 멤버 변수
- 인터페이스에서 멤버 변수는 public static final이 모두 포함되었다고 간주된다.

### 인터페이스를 사용해야 하는 이유
- 제약: 인터페이스를 구현하는 곳에서 반드시 구현하라는 제약을 준다.
- 다중 구현: 인터페이스는 부모를 여러 명 두는 다중 구현이 가능하다.

### 다형성의 실세계 비유
- 역할과 구현으로 세상을 구분

### 역할과 구현을 분리(자바)
- 자바 언어의 다형성을 활용한다.
- 역할 = 인터페이스
- 구현 = 인터페이스를 구현한 클래스, 구현 객체
- 객체를 설계할 때 역할과 구현을 명확히 분리한다.
- 객체 설계시 역할(인터페이스)을 먼저 부여하고, 그 역할을 수행하는 구현 객체를 만든다.

### 객체의 협력이라는 관계부터 생각
- 혼자 있는 객체는 없다.
- 클라이언트: 요청, 서버: 응답
- 수많은 객체 클라이언트와 객체 서버는 서로 협력 관계를 가진다.

### 다형성의 본질
- 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경할 수 있다.
- 다형성의 본질을 이해하려면 협력이라는 객체 사이의 관계에서 시작해야한다.
- 클라이언트를 변경하지 않고 서버의 구현 기능을 유연하게 변경할 수 있다.

### OCP(Open-Closed Principle) 원칙
좋은 객체 지향 설계 원칙 중 하나로 OCP 원칙이라는 것이 있다.
- Open for extension: 새로운 기능의 추가나 변경 사항이 생겼을 때, 기존 코드는 확장할 수 있어야 한다.
- Closed for modification: 기존 코드는 수정되지 않아야 한다.</br>

쉽게 말해서 기존의 코드 수정 없이 새로운 기능을 추가할 수 있다는 의미다.