### 중첩 클래스란 ?
클래스 안에 클래스를 중첩해서 정의할 수 있는데, 이것을 중첩 클래스(Nested Class)라 한다.
```java
class Outer {
    ...
    // 중첩 클래스
    class Nested {
        ...
    }
}
```

#### 중첩 클래스는 총 4가지가 있고, 크게 2가지로 분류할 수 있다.
- 정적 중첩 클래스(static)
- 내부 클래스(non-static)
  - 내부 클래스(inner class)
  - 지역 클래스(local class) - 코드 블록에 선언
  - 익명 클래스(anonymous class) - 이름 없는 클래스

중첩 클래스를 정의하는 위치는 변수의 선언 위치와 같다.
- 정적 중첩 클래스 -> 정적 변수와 같은 위치
- 내부 클래스 -> 인스턴스 변수와 같은 위치
- 지역 클래스 -> 지역 변수와 같은 위치
```java
class Outer {
    ...
    // 정적 중첩 클래스
    static class StaticNested {
        ...
    }
    
    // 내부 클래스
    class Inner {
        ...
    }
}
```
```java
class Outer {
    
    public void process() {
        // 지역 변수
        int localVar = 0;
        
        // 지역 클래스
        class Local {...}
        
        Local local = new Local();
    }
}
```

그렇다면 중첩이라는 단어와 내부라는 단어는 무슨 차이가 있는 걸까?
- **중첩(Nested)**: `어떤 다른 것`이 내부에 위치하거나 포함되는 구조적인 관계
  - 예시. 큰 나무 상자 안에 전혀 다른 나무 상자를 넣은 것은 중첩`(Nested)`이라 한다.
- **내부(Inner)**: 나의 내부에 있는 `나를 구성하는 요소`
  - 예시. 나의 심장은 나의 내부`(Inner)`에서 나를 구성하는 요소이다.

중첩과 내부를 분류하는 핵심은 바로 바깥 클래스 입장에서 볼 때 안에 있는 클래스가 나의 인스턴스에 소속이 되는가 되지 않는가의 차이이다.
- 정적 중첩 클래스는 바깥 클래스와 전혀 다른 클래스이다. 따라서 바깥 클래스의 인스턴스에 소속되지 않는다.
- 내부 클래스는 바깥 클래스를 구성하는 요소이다. 따라서 바깥 클래스의 인스턴스에 소속된다.

### 내부 클래스의 종류
- 내부 클래스(Inner Class): 바깥 클래스의 인스턴스 멤버에 접근 가능
- 지역 클래스(Local Class): 내부 클래스의 특징 + 지역 변수에 접근 가능
- 익명 클래스(Anonymous Class): 지역 클래스의 특징 + 클래스의 이름이 없는 특별한 클래스

### 중첩 클래스를 사용하는 이유
- **논리적 그룹화**: 특정 클래스가 다른 하나의 클래스 안에서만 사용되는 경우 해당 클래스 안에 포함하는 것이 논리적으로 더 그룹화가 된다.
- **캡슐화**: 중첩 클래스는 바깥 클래스의 `private`멤버에 접근할 수 있다. 이로인해 둘을 긴밀하게 연결하고 불필요한 `public`메서드를 제거할 수 있다.
  - 예시. getter()

### 정적 중첩 클래스
```java
public class NestedOuter {
    
    private static int outClassValue = 3;
    private int outInstanceValue = 2;
    
    static class Nested {
        private int nestedInstanceValue = 1;
        
        public void print() {
            // 자신의 멤버에 접근
            System.out.println(nestedInstanceValue);
            
            // 바깥 클래스의 인스턴스 멤버에는 접근할 수 없다.
//            System.out.println(outInstanceValue);
            
            // 바깥 클래스의 클래스 멤버에는 접근할 수 있다. private도 접근 가능
            System.out.println(outClassValue);
        }
    }
}
```

```java
import nested.nested.NestedOuter;

public class NestedOuterMain {

    public static void main(String[] args) {
        NestedOuter outer = new NestedOuter();
        NestedOuter.Nested nested = new NestedOuter.Nested();
        nested.print();
    }
}
```
- 정적 중첩 클래스는 `new 바깥클래스.중첩클래스()`로 생성할 수 있다.
- 여기서 `new NestedOuter()`로 만든 바깥 클래스의 인스턴스와 `new NestedOuter.Nested()`로 만든 중첩 클래스의 인스턴스는 서로 아무 관계가 없는 인스턴스이다.
  - 정적 중첩 클래스의 인스턴스만 따로 생성해도 된다.

### 정적 중첩 클래스 정리
중첩 클래스는 그냥 다른 클래스 2개를 따로 만든 것과 같다.</br>
차이점은 `private` 접근 제어자에 접근할 수 있다는 정도이다.