### 래퍼 클래스 - 기본형의 한계
기본형은 객체가 아니기 때문에 다음과 같은 한계가 있다.
- 객체가 아님: 객체 지향 프로그래밍의 장점을 살릴 수 없다. 예를 들어 객체가 아니므로 메서드를 제공할 수 없다.
- null 값을 가질 수 없음: 때로는 데이터가 `없음`이라는 상태를 나타내야 할 필요가 있는데, 기본형은 항상 값을 가지기 때문에 이런 표현을 할 수가 없다.

### 직접 만든 래퍼 클래스
``` java
public class MyInteger {
    
    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getter() {
        ...
    }

    public int compareTo(int target) {
        value와 target의 값을 비교하는 메서드
    }

    @Override
    public String toString() {
        ...
    }
```

- `MyInteger`는 `int value`라는 기본형 멤버 변수를 편리하게 사용하도록 다양한 메서드를 제공한다.
- `compareTo()` 메서드를 클래스 내부로 캡슐화 했다.

### 래퍼 클래스 - 자바 래퍼 클래스
자바는 기본형에 대응하는 래퍼 클래스를 기본으로 제공한다.
- `byte` -> `Byte`
- `short` -> `Short`
- `int` -> `Integer`
- `long` -> `Long`
- `float` -> `Float`
- `double` -> `Double`
- `char` -> `Character`
- `boolean` -> `Boolean`

#### 자바가 제공하는 기본 래퍼 클래스의 특징
- 불변이다.
- `equals`로 비교해야 한다.

### Interger클래스의 valueOf() 메서드
- 주어진 값을 Integer 객체로 변환하는 메서드
- 주로 `int` 또는 `String`값을 `Integer` 객체로 변환하는 데 사용한다.
- valueOf(int i) 메서드는 -128에서 127 사이의 값에 대해 캐싱된 Integer 객체를 반환한다.
- 즉, 이 범위 내에서는 새로운 객체를 생성하지 않고, 이미 생성된 객체를 반환해 메모리 효율성을 높인다.

### 박싱(Boxing): 기본형에서 래퍼 클래스로 변경
- new Integer(10)은 deprecated된 방법이므로 직접 사용하면 안된다.
- 대신 Integer.valueOf(10)을 사용하면 된다.
  - 내부에서 new Integer(10)을 사용해서 객체를 생성하여 반환한다.

### 언박싱(Unboxing): 래퍼 클래스에서 기본형으로 변경
- 래퍼 클래스에 들어있는 기본형 값을 다시 꺼내는 메서드이다.
- 예) `intValue()`

### 오토 박싱(Auto-Boxing)과 오토 언박싱(Auto-Unboxing)
자바에서 `int`를 `Integer`로 변환하거나, `Integer`를 `int`로 변환할 때 자동으로 변환해준다.

### 래퍼 클래스 - 주요 메서드
- `valueOf()`: 래퍼 타입을 반환한다. 숫자, 문자열 모두 지원한다.
- `parseInt()`: 문자열을 기본형으로 변환한다.
- `compareTo()`: 자신의 값과 인수로 넘어온 값을 비교한다. 자신의 값이 크면 `1`, 같으면 `0`, 자신의 값이 작으면 `-1` 반환
- `Integer.sum()`, `Integer.min()`, `Integer.max()`: `static` 메서드이다.

### 기본형 VS 래퍼 클래스
- CPU 연산을 아주 많이 수행하는 특수한 경우이거나, 수만 ~ 수십만 이상 연속해서 연산을 수행해야 하는 경우라면 기본형을 사용해서 최적화를 고려하자.
- 그렇지 않은 일반적인 경우라면 코드를 유지보수하기 더 나은 것을 선택하자!
- 권장하는 방법은 개발 이후에 성능 테스트를 해보고 정말 문제가 되는 부분을 찾아서 최적화 하는 것이다.