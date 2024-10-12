### 익명 클래스
익명 클래스(Anonymous class)는 지역 클래스인데, 클래스의 이름이 없다는 특징이 있다.

#### 지역 클래스의 선언과 생성을 한번에
```java
Printer printer = new Printer() {
    // body
}
```

#### 예제 코드
```java
public class AnonymousOuter {
    
    private int outInstance = 3;
    
    public void process(int paramVar) {
        
        int localVar = 1;
        
        // 익명 클래스의 사용
        Printer printer = new Printer() {
            
            int value = 0;
            
            @Override
            public void print() {
                System.out.println("value = " + value);
                System.out.println("localVar = " + localVar);
                System.out.println("printer = " + printer);
                System.out.println("outInstance = " + outInstance);
            }
        };
        printer.print();
        System.out.println("printer.class = " + printer.getClass());
    }

    public static void main(String[] args) {
        AnonymousOuter main = new AnonymousOuter();
        main.process(2);
    }
}
```
```java
value = 0
localVar = 1
paramVar = 2
outInstanceVar = 3
printer.class = class nested.anonymous.AnonymousOuter$1
```
- 익명 클래스는 클래스의 본문(body)을 정의하면서 동시에 생성한다.
- `new` 다음에 바로 상속 받으면서 구현 할 부모 타입을 입력하면 된다.
- 위 코드는 인스턴스를 생성하는 것이 아니고, `Printer`라는 이름의 인터페이스를 구현한 익명 클래스를 생성하는 것이다.

#### 익명 클래스 특징
- 익명 클래스는 이름 없는 지역 클래스를 선언하면서 동시에 생성한다.
- **익명 클래스는 부모 클래스를 상속 받거나, 또는 인터페이스를 구현해야 한다.**
- 익명 클래스는 이름을 가지지 않으므로, 생성자를 가질 수 없다.(기본 생성자만 사용된다.)
- 익명 클래스는 바깥 클래스 이름 + `$` + 숫자로 정의된다.

#### 익명 클래스의 장점
- 클래스를 별도로 정의하지 않고도 인터페이스나 추상 클래스를 즉석에서 구현할 수 있어 코드가 간결해진다.
- 익명 클래스는 단 한 번만 인스턴스를 생성할 수 있다.

### 람다(Lamda)
자바 8에 들어서면서 큰 변화가 있었는데 바로 메서드(정확히는 함수)를 인수로 젇날할 수 있게 되었다.

#### 리팩토링 - 람다

```java
import java.util.Random;

public class ExRefMain {

    public static void hello(Process process) {
        System.out.println("프로그램 시작");
        // 코드 조각 시작
        process.run();
        // 코드 조각 종료
        System.out.println("프로그램 종료");
    }

    public static void main(String[] args) {
        
        hello(() -> {
            int randomValue = new Random.nextInt(6) + 1;
            System.out.println("주사위 = " + randomValue);
        });
        
        hello(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("i = " + i);
            }
        });
    }
}
```