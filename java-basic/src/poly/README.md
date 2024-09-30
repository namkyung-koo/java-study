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