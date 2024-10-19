## 제네릭
대부분의 최신 프로그래밍 언어는 제네릭(Generic) 개념을 제공한다.

### 제네릭이 필요한 이유

#### 문제 제시

> 래퍼 클래스(Wrapper class) 객체를 멤버 변수로 갖는 래퍼 클래스가 있다고 생각해보자.
> 
> `IntegerBox`, `StringBox`, `DoubleBox`, `BooleanBox` ..
>
> 담는 타입이 늘어날 때마다, `XxxBox` 클래스를 만들어야 하는 번거로움이 있다.

#### 다형성을 통한 중복 해결 시도

> `Object`는 모든 타입의 부모이다. 따라서 다형성을 사용해서 위 문제를 간단히 해결할 수 있을 것 같다.

```java
public class ObjectBox {
    
    private Object value;
    
    public void set(Object o) {
        this.value = o;
    }
    
    public Object get() {
        return value;
    }
}
```

잘 작동할 것만 같지만 역시나 문제가 있다.
- **반환 타입이 맞지 않는 문제**
  - `XxxBox.set()` 메서드를 호출은 문제가 없다.
  - `XxxBox.get()`을 호출할 때 문제가 나타난다.
  - `XxxType = Object`는 성립하지 않는다. 자식은 부모를 담을 수 없다. 따라서 직접 `XxxType`으로 다운 캐스팅을 해야한다.
  - 앞서 학습했다시피, 다운캐스팅은 최대한 `지양`하는 것이 좋다.
- **잘못된 타입의 인수 전달 문제**
  - 예시. `integerBox.set("문자100");`
  - 개발자의 의도는 `integerBox`에는 숫자 타입이 입력되기를 기대했다.
  - 하지만 `set(Object ..)`메서드는 모든 타입의 부모인 `Object`를 매개변수로 받기 때문에 `오토박싱(Autoboxing)된 데이터`도 받을 수 있다.
  - 결과적으로 `Integer integer = (Integer) integerBox.get();`을 호출하는 순간 예외가 발생하고 프로그램이 종료된다.

#### 정리
다형성을 활용한 덕분에 코드의 중복을 제거하고, 기존 코드를 재사용할 수 있게 되었다.
하지만 입력할 때 실수로 원하지 않는 타입이 들어갈 수 있는 `타입 안전성 문제`가 발생한다.
그리고 반환 시점에도 `Object`를 반환하기 때문에 원하는 타입을 정확하게 받을 수 없고, 항상 위험한 다운 캐스팅을 시도해야한다.
> 결과적으로 이 방식은 타입 안전성이 떨어진다.

### 제네릭 적용
제네릭을 사용하면 코드 재사용과 타입 안전성이라는 두 마리 토끼를 한 번에 잡을 수 있다.

#### 제네릭 적용 예제 코드
```java
public class GenericBox<T> {
    
    private T value;
    
    public void set(T value) {
        this.value = value;
    }
    
    public T get() {
        return value;
    }
}
```
- `<>`(다이아몬드)를 사용한 클래스를 제네릭 클래스라 한다.
- 제네릭 클래스를 사용할 때는 `Integer`, `String` 같은 타입을 미리 결정하지 않는다.
- 대신에 클래스명 오른쪽에 `<T>`와 같이 선언을 한다. 여기서 `T`를 **타입 매개변수**라 한다.
- 제네릭 클래스 내부에서 `T`타입이 필요한 곳에 `T value`와 같이 타입 매개변수를 적어두면 된다.

#### 예제 main문

```java
import generic.ex1.GenericBox;

public class Main {

    public static void main(String[] args) {
        // 생성 시점에 T의 타입을 결정
        GenericBox<Integer> integerGenericBox = new GenericBox<Integer>();
        integerGenericBox.set(10);
        Integer integer = integerGenericBox.get();
        System.out.println("integer = " + integer);
        
        // 타입 추론: 생성하는 제네릭 타입 생략 가능
        GenericBox<String> stringGenericBox = new GenericBox<>();
        stringGenericBox.set("Newjeans");
        String string = stringGenericBox.get();
        System.out.println("string = " + string);
    }
}
```
#### 실행 결과
```java
integer = 10;
string = Newjeans;
```

#### 문제 해결
- **코드 재사용**: 제네릭 클래스만 만들면 정의하는 과정에서 모든 래퍼 클래스를 타입 매개변수로 받을 수 있다.
- **타입 안전성**: 생성된 제네릭 객체에는 해당 래퍼 클래스 객체만 전달하고 반환할 수 있다.
  - 다운캐스팅을 할 필요가 없다.
  - 잘못된 타입을 반환할 수 없다.
- **원하는 모든 타입 사용 가능**: 제네릭 클래스를 사용하면 다음과 같이 사용자가 정의한 클래스 타입도 지정할 수 있다.
  - `new GenericBox<MyClass>()`

참고로 제네릭을 도입한다고 해서 앞서 설명한 `GenericBox<String>`, `GenericBox<Integer>`와 같은 코드가 실제로 만들어지는 것은 아니다.
대신에 자바 컴파일러가 우리가 입력한 타입 정보를 기반으로 이런 코드가 있다고 가정하고 컴파일 과정에서 타입 정보를 반영한다.

### 제네릭 용어와 관례
> 제네릭의 핵심은 **사용할 타입을 미리 결정하지 않는다는 점**이다.
> 
> **메서드의 매개변수는 사용할 값에 대한 결정을 나중으로 미루는 것**이고, **제네릭의 타입 매개변수는 사용할 타입에 대한 결정을 나중으로 미루는 것**이다.
> 
> 제네릭에서 사용하는 용어도 매개변수, 인자의 용어를 그대로 가져다 사용한다. 다만 값이 아니라 **타입을 결정**하는 것이기 때문에 앞에 타입을 붙인다.

- 타입 매개변수: `GenericBox<T>`에서 `T`
- 타입 인자:
  - `GenericBox<Integer>`에서 `Integer`
  - `GenericBox<String>`에서 `String`

#### 용어 정리
- 제네릭(Generic)
  - 범용적인, 범용적으로 사용할 수 있다
- 제네릭 타입(Generic Type)
  - 클래스나 인터페이스를 정의할 때 타입 매개변수를 사용하는 것
- 타입 매개변수(Type Parameter)
  - 제네릭 타입이나 메서드에서 사용되는 변수로, 실제 타입으로 대체된다.
  - 예시. `GenericBox<T>`, 여기에서 `<T>`를 타입 매개변수라 한다.
- 타입 인자(Type Argument)
  - 제네릭 타입을 사용할 때 제공되는 실제 타입
  - 예시. `GenericBox<Integer>`, 여기에서 `Integer`를 타입 인자라 한다.

#### 제네릭 명명 관례
일반적으로 대문자를 사용하고 용도에 맞는 단어의 첫 글자를 사용하는 관례를 따른다.

- E - Element
- K - Key
- N - Number
- T - Type
- V - Value
- S,U,V etc. - 2nd,3rd,4th types

### 기타
- 한 번에 여러 타입 매개변수를 선언할 수 있다.
  - `class Data<K, V> {}`
- 타입 인자로 `기본형`은 사용할 수 없다.
- 로 타입(Raw Type): 타입 인자를 지정하지 않는 것
  - 예시. `GenericBox integerBox = new GenericBox();`
  - 로 타입(또는 원시 타입)을 사용하면 내부의 타입 매개변수가 `Object`로 사용된다.
  - 제네릭이 처음 등장했을 때, 과거 코드와의 호환을 위해 지원.
  - 지금은 사용하지 않아야한다!