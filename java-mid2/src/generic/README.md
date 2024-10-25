## 제네릭 - Generic1
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

## 제네릭 - Generic2

### 제네릭을 도입한 타입 매개변수의 제한과 실패
제네릭을 도입해서 코드 재사용성을 늘리고, 타입 안전성 문제도 해결해보자

```java
import java.util.Random;

public class AnimalHospital<T> {

  private T animal;

  public void set(T animal) {
    this.animal = animal;
  }

  public void checkup() {
    // T의 타입을 메서드를 정의하는 시점에는 알 수 없다. Object의 기능만 사용 가능하다.
    animal.toString();
    animal.equals(null);

    // 컴파일 에러
//      System.out.println("동물 이름: " + animal.getName());
//        animal.sound();
  }

  public T getBigger(T target) {
    // 컴파일 에러
//    return animal.getSize() > target.getSize() ? animal : target;
    return null;
  }
}
```

#### 문제
- 제네릭에서 타입 매개변수를 사용하면 어떤 타입이든 들어올 수 있다.
- 따라서 타입 매개변수를 어떤 타입이든 수용할 수 있는 `Object`로 가정하고, `Object`의 기능만 사용할 수 있다.

만약 타입 인자가 모두 `Animal`과 그 자식만 들어올 수 있게 제한한다면 어떨까 ?

```java
public class AnimalHospital<T extends Animal> {
    
    private T animal;
    
    public void set(T animal) {
        this.animal = animal;
    }
    
    public void checkup() {
      System.out.println("동물 이름: " + animal.getName());
      System.out.println("동물 크기: " + animal.getSize());
      animal.sound();
    }
    
    public T getBigger(T target) {
        return animal.getSize() > target.getSize() ? animal : target;
    }
}
```

- 타입 매개변수 `T`를 `Animal`과 그 자식만 받을 수 있도록 제한을 둔다.
- 즉 `T`의 상한이 `Animal`이 되는 것이다.

### 기존 문제와 해결
#### 타입 안전성 문제
- 개 병원에 고양이를 전달하는 문제가 발생한다. -> 해결
- `Animal`타입을 반환하기 때문에 다운캐스팅을 해야한다. -> 해결
- 실수로 고양이를 입력했는데, 개를 반환하는 상황이라면 캐스팅 예외가 발생한다. -> 해결

#### 제네릭 도입 문제
- 제네릭에서 타입 매개변수를 사용하면 어떤 타입이든 들어올 수 있다. -> 해결
- 그리고 어떤 타입이든 수용할 수 있는 `Object`로 가정하고, `Object`의 기능만 사용할 수 있다. -> 해결
  - 여기서는 `Animal`을 상한으로 두어서 `Animal`의 기능을 사용할 수 있다.

### 제네릭 메서드
제네릭 타입과 제네릭 메서드는 둘 다 제네릭을 사용하기는 하지만 서로 다른 기능을 제공한다.

```java
public class GenericMethod {
    
    public static Object objMethod(Object obj) {
      System.out.println("object print: " + obj);
      return obj;
    }
    
    public static <T> T genericMethod(T t) {
      System.out.println("generic print: " + t);
      return t;
    }
    
    public static <T extends Number> T numberMethod(T t) {
      System.out.println("bound print: " + t);
      return t;
    }
}
```
#### 제네릭 타입
- 정의: `GenericClass<T>`
- 타입 인자 전달: 객체를 생성하는 시점
  - 예시. `new GenericClass<String>`
#### 제네릭 메서드
- 정의: `<T> T genericMethod(T t)`
- 타입 인자 전달: 메서드를 호출하는 시점
  - 예시. `GenericMethod.<Integer>genericMethod(10)`
- 제네릭 메서드는 클래스 전체가 아니라 특정 메서드 단위로 제네릭을 도입할 때 사용한다.
- 제네릭 메서드를 정의할 때는 메서드의 반환 타입 왼쪽에 다이아몬드를 사용해서 `<T>`와 같이 타입 매개변수를 적어준다.
- 제네릭 메서드는 메서드를 실제 호출하는 시점에서 다이아몬드를 사용해서 `<Interger>`와 같이 타입을 정하고 호출한다.

#### 인스턴스 메서드, static 메서드
제네릭 메서드는 인스턴스 메서드와 static 메서드에 모두 적용할 수 있다.
```java
class Box<T> { // 제네릭 타입 
     static <V> V staticMethod(V t) {} // static 메서드에 제네릭 메서드 도입
     <Z> Z instanceMethod(Z z) {} // 인스턴스 메서드에 제네릭 메서드 도입 가능
}
```
#### 참고
제네릭 타입은 static 메서드에 타입 매개변수를 사용할 수 없다. 제네릭 타입은 객체를 생성하는 시점에 타입이 정해진다.
그런데 static 메서드는 인스턴스 단위가 아니라 클래스 단위로 작동하기 때문에 제네릭 타입과는 무관하다.
<br>
따라서 static 메서드에 제네릭을 도입하려면 제네릭 메서드를 사용해야 한다.
```java
class Box<T> {
    T instanceMethod(T t) {} // 가능
    static T staticMethod(T t) {} // 제네릭 타입의 T 사용 불가능
}
```

### 제네릭 메서드의 타입 매개변수 제한
다음 코드는 타입 매개변수를 `Number`로 제한했다. 따라서 `Number`와 그 자식만 받을 수 있다.
<br>
참고로 `Integer`, `Double`, `Long`과 같은 숫자 타입이 `Number`의 자식이다.
```java
public static <T extends Number> T numberMethod(T t) {}
```

### 제네릭 메서드 타입 추론
```java
Integer i = 10; // Auto-Boxing
Integer result = GenericMethod.<Integer>genericMethod(i);
Integer result2 = GenericMethod.genericMethod(i);
Integer integerValue2 = GenericMethod.genericMethod(20);
Double doubleValue = GenericMethod.numberMethod(20.0);
```

### 와일드카드1
제네릭 타입을 조금 더 편리하게 사용할 수 있는 와일드카드(wildcard)에 대해 알아보자.
<br>
참고로 와일드카드라는 뜻은 컴퓨터 프로그래밍에서 `*`,`?`와 같이 하나 이상의 문자들을 상징하는 특수 문자를 뜻한다.

#### 참고!!!!
와일드카드는 제네릭 타입이나 제네릭 메서드를 선언하는 것이 아니다. 와일드카드는 이미 만들어진 제네릭 타입을 활용할 때 사용한다.

### 비제한 와일드카드
```java
// 이것은 제네릭 메서드이다.
// Box<Dog> dogBox를 전달한다. 타입 추론에 의해 타입 T가 Dog가 된다.
static <T> void printGenericV1(Box<T> box) {
  System.out.println("T = " + box.get());
}

// 이것은 제네릭 메서드가 아니다. 일반적인 메서드이다.
// Box<Dog> dogBox를 전달한다. 와일드카드 ?는 모든 타입을 받을 수 있다.
static void printWildcardV1(Box<?> box) {
  System.out.println("? = " + box.get());
}
```
- 와일드카드인 `?`는 모든 타입을 다 받을 수 있다는 뜻이다.
  - 다음과 같은 해석할 수 있다. `? == <? extends Object>`
  - 이렇게 `?`만 사용해서 제한 없이 모든 타입을 다 받을 수 있는 와일드카드를 비제한 와일드카드라 한다.

#### 제네릭 메서드 vs 와일드카드
제네릭 타입이나 제네릭 메서드를 정의하는게 꼭 필요한 상황이 아니라면, 더 단순한 와일드카드 사용을 권장한다.

### 와일드카드2
### 상한 와일드카드
```java
static void printWildcard(Box<? extends Animal> box) {
    Animal animal = box.get();
  System.out.println("이름 = " + animal.getName());
}
```
- `Animal`과 그 하위 타입만 입력 받는다. 만약 다른 타입을 입력하면 컴파일 오류가 발생한다.

#### 타입 매개변수가 꼭 필요한 경우
```java
static <T extends Animal> T printAndReturnGeneric(Box<T> box) {
    T t = box.get();
    System.out.println("이름 = " + t.getName());
    return t;
}

static Animal printAndReturnWildcard(Box<? extends Animal> box) {
    Animal animal = box.get();
    System.out.println("이름 = " + animal.getName());
    return animal;
}
```
`printAndReturnGeneric()`은 전달한 타입을 명확하게 반환할 수 있다.
```java
Dog dog = WildCardEx.printAndReturnGeneric(dogBox);
```
반면에 `printAndReturnWildcard()`는 전달한 타입을 명확하게 반환할 수 없다. 여기서는 `Animal`타입을 반환한다.
```java
Animal animal = WildcardEx.printAndReturnWildcard(dogBox);
```

### 하한 와일드카드
```java
Box<? super Animal> box
```

### 타입 이레이져
제네릭은 자바 컴파일 단계에서만 사용되고, 컴파일 이후에는 제네릭 정보가 삭제된다. 제네릭에 사용한 타입 매개변수가 모두 사라지는 것이다.
쉽게 이야기해서 컴파일 전인 `.java`에는 제네릭의 타입 매개변수가 존재하지만, 컴파일 이후인 자바 바이트코드 `.class`에는 타입 매개변수가 존재하지 않는 것이다.

#### 대략적인 작동방식
1. 제네릭 타입 선언
2. 제네릭 타입에 `Integer` 타입 인자 전달
3. 자바 컴파일러는 컴파일 시점에서 타입 매개변수를 타입 인자 형태로 바꿔서 이해한다.(예시. `T -> Integer`)
4. (컴파일 후) 상한 제한없이 선언한 타입 매개변수 `T`는 `Object`로 변환된다.
5. 값을 반환 받는 부분(`getter`)를 타입 인자로 지정한 `Integer`로 다운캐스팅 하는 코드를 추가한다.
   1. 타입 매개변수를 `제한`하면 `제한한 타입`으로 코드를 변경한다.

#### 타입 이레이저 방식의 한계
컴파일 이후에는 제네릭 타입 정보가 존재하지 않는다. 
`.class`로 자바를 실행하는 런타임에는 우리가 지정한 `Box<Integer>`, `Box<String>`의 타입 정보가 모두 제거된다.
<br>
따라서 런타임에 타입을 활용하는 다음과 같은 코드는 작성할 수 없다.
```java
class EraserBox<T> {
    
    public boolean instanceCheck(Object param) {
        return param instanceof T; // 오류
    }
    
    public void create() {
        return new T(); // 오류
    }
}
```
```java
class EraserBox {
    
    public boolean instanceCheck(Object param) {
        return param instanceof Object; // 오류
    }
    
    public void create() {
        return new Object(); // 오류
    }
}
```
- `T`는 런타임에 모두 `Object`가 되어버린다.
- `instanceof`는 `Object`와 비교하면서 항상 참이 반환되는 문제가 발생한다.
- 개발자의 의도와는 다르게 `new T`는 항상 `new Object`가 되어버린다. 따라서 자바는 타입 매개변수에 `new`를 허용하지 않는다. 