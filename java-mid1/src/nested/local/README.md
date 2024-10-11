### 지역 클래스
- 지역 클래스(Local class)는 내부 클래스의 종류의 하나이다. 따라서 내부 클래스의 특징을 그대로 가진다.
- 지역 클래스는 지역 변수와 같이 코드 블럭 안에서 정의된다.

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
### 접근 가능한 변수
- 자기 자신의 인스턴스 변수
- `자신이 속한 코드 블럭`의 지역 변수
- `자신이 속한 코드 블럭`의 매개 변수
- 바깥 클래스의 인스턴스 변수

### 특징
- 지역 클래스는 지역 변수처럼 `접근 제어자`를 사용할 수 없다. 
- 일반 클래스처럼 `인터페이스`를 구현하거나, 부모 클래스를 `상속` 받을 수 있다.

### 지역 변수 캡처
지역 클래스가 접근하는 지역 변수의 값은 변경하면 안된다 !

#### 변수의 생명 주기
- 클래스 변수(static 변수): 프로그램 종료까지, 가장 길다(`메서드 영역`)
- 인스턴스 변수: 인스턴스의 생존 기간(`힙 영역`)
  - 본인이 소속된 인스턴스가 GC 되기 전까지 존재한다. 생존 주기가 긴 편이다.
- 지역 변수: 메서드 호출이 끝나면 사라진다(`스택 영역`)
  - 지역 변수는 `스택 영역`의 `스택 프레임` 안에 존재한다.
  - 메서드 호출이 종료되면 `스택 프레임`이 제거된다.
  - 생존 주기가 아주 짧다.
  - `매개 변수`도 `지역 변수`의 한 종류이다.

#### 예제

```java
public class LocalOuterV3 {
    
    private int outInstanceVar = 3;

    public Printer process(int paramVar) {
        
        int localVar = 1; // 지역 변수는 스택 프레임이 종료되는 순간 함께 제거된다.
        
        class LocalPrinter implements Printer {
            
            int value = 0;
            
            @Override
            public void print() {
                System.out.println("value = " + value);
                
                // 인스턴스 변수는 지역 변수보다 더 오래 살아남는다.
                System.out.println("localVar = " + localVar);
                System.out.println("paramVar = " + paramVar);
                // 바깥 클래스의 인스턴스 변수도 당연히 접근 가능
                System.out.println("outInstanceVar = " + outInstanceVar);
            }
        }
        
        // 부모 클래스는 자식 객체를 담을 수 있다.
        Printer printer = new LocalPrinter();
        return printer;
    }

    public static void main(String[] args) {
        LoclaOuterV3 localOuter = new LocalOuterV3();
        Printer printer = localOuter.process(2);
        // process() 스택 프레임이 사라진 이후에 실행
        printer.print();
    }
}
```

#### 내가 헷갈렸던 점
- 지역 클래스의 인스턴스는 `메서드 영역`에 생성되나 `힙 영역`에 생성되나 ?
  - 답: 지역 클래스로 만든 인스턴스도 `힙 영역`에 존재한다.
  - 결론: 객체의 인스턴스는 그냥 전부 `힙 영역`에 생성된다고 간주하기

#### 흐름
- LocalPrinter 클래스는  `힙 영역`에 생성된다.
- `localVar`와 `paramVar`는 지역 변수기 때문에 process() 스택 메서드가 종료되면 사라진다.
- 그러나 printer.print()가 정상적으로 작동한다.

#### Why ?
- 지역 변수의 생명 주기 != 인스턴스의 생명 주기
  - 인스턴스는 살아있지만 지역 변수는 이미 제거된 상태일 수 있다.
- 자바는 이런 문제를 해결하기 위해 `지역 클래스의 인스턴스를 생성하는 시점`에 `필요한 지역 변수를 복사해서 생성한 인스턴스`에 함께 넣어둔다.
- 그러므로 실제로는 지역 변수에 접근하는 것이 아니라 인스턴스에 있는 캡처한 `캡처 변수`에 접근한다.
- 이런 과정을 변수 캡처(Capture)라 한다.

#### 그러므로 지역 클래스의 인스턴스가 생성된 이후로는 지역 변수 또는 매개 변수를 변경해선 안된다 !!!

```java
import java.lang.reflect.Field;

public static void main(String[] args) {
    ...

    // 추가
    System.out.println("필드 확인");
    Field[] fields = printer.getClass().getDeclareFields();
    for (Field  field : fields) {
        System.out.println("field = " + field);
    }
}
```
```java
필드 확인
//인스턴스 변수
field = int nested.local.LocalOuterV3$1LocalPrinter.value
//캡처 변수
field = final int nested.local.LocalOuterV3$1LocalPrinter.val$localVar
field = final int nested.local.LocalOuterV3$1LocalPrinter.val$paramVar
//바깥 클래스 참조
field = final nested.local.LocalOuterV3
nested.local.LocalOuterV3$1LocalPrinter.this$0
```

#### 용어 - 사실상 final
영어로 effectively final이라 한다.
- final 키워드는 사용하지 않았지만, 값을 변경하지 않는 지역 변수를 뜻한다.
- 변경하려고 하면 지역 변수의 값과 인스턴스에 캡처한 캡처 변수의 값이 서로 달라지는 `동기화 문제`가 발생한다.