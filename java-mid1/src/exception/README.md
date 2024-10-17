### 예외 처리1- 이론
자바는 프로그램 실행 중에 발생할 수 있는 예기치 못한 상황, 즉 예외(Exception)를 처리하기 위한 메커니즘을 제공한다. 이는 프로그램의 `안정성`과 `신뢰성`을 높이는 데 중요한 역할을 한다.

자바의 예외 처리는 다음 키워드를 사용한다.
`try`,`catch`,`finally`,`throw`,`throws`

#### 예외 계층
예외도 객체이다. 모든 객체의 최상위 부모는 `Object`이므로 예외의 최상위 부모도 `Object`이다.

- `Throwable`: 최상위 예외이다. 하위에 `Exception`과 `Error`가 있다.
- `Error`: 메모리 부족이나 심각한 시스템 오류와 같이 애플리케이션에서 복구가 불가능한 시스템 예외이다. 애플리케이션 개발자는 이 예외를 잡으려고 해서는 안된다.
- `Exception`: 체크 예외
  - 애플리케이션 로직에서 사용할 수 있는 실질적인 최상위 예외이다.
  - `Exception`과 그 하위 예외는 모두 컴파일러가 체크하는 체크 예외이다. 단 `RuntimeException`은 예외로 한다.
  - `RuntimeException`: 언체크 예외, 런타임 예외
    - 컴파일러가 체크하지 않는 언체크 예외이다.
    - `RuntimeException`과 그 자식 예외는 모두 언체크 예외이다.
    - `RuntimeException`의 이름을 따라서 `RuntimeException`과 그 하위 언체크 예외를 **런타임 예외**라고 많이 부른다.

#### 체크 예외 vs 언체크 예외(런타임 예외)
체크 예외는 발생한 예외를 개발자가 명시적으로 처리해야 한다. 그렇지 않으면 컴파일 오류가 발생한다.

#### 주의
상속 관계에서 부모 타입은 자식을 담을 수 있다. 이 개념이 예외 처리에도 적용되는데, 상위 예외를 `catch`로 잡으면 그 하위 예외까지 함께 잡는다.
따라서 애프리케이션 로직에서는 `Throwable`예외를 잡으면 안된다. 앞서 이야기한 잡으면 안되는 `Error`예외도 함께 잡을 수 있기 때문이다.
애플리케이션 로직은 이런 이유로 `Exception`부터 필요한 예외로 생각하고 잡으면 된다.

### 예외 기본 규칙
예외는 폭탄 돌리기와 같다. 예외가 발생하면 잡아서 처리하거나, 처리할 수 없으면 밖으로 던져야한다.
여기서 말하는 밖이란 예외가 발생한 메서드를 호출한 메서드를 뜻한다.

1. 예외는 잡아서 처리하거나 밖으로 던져야 한다.
2. 예외를 잡거나 던질 때 지정한 예외뿐만 아니라 그 예외의 자식들도 함께 처리할 수 있다.
   - 예를 들어서 `Exception`을 `catch`로 잡으면 그 하위 예외들도 모두 잡을 수 있다.
   - 예를 들어서 `Exception`을 `throws`로 던지면 그 하위 예외들도 모두 던질 수 있다.
- 예외를 처리하지 못하고 계속 던지게 된다면, 자바 `main()` 밖으로 예외를 던지면서 예외 정보와 스택 트레이스(Stack Trace)를 출력하면서 시스템이 종료된다.
  - 스택 트레이스 정보를 활용하면 예외가 어디서 발생했는지, 그리고 어떤 경로를 거쳐서 넘어왔는지 확인할 수 있다.

```java
public class MyCheckedException extends Exception {
    public MyCheckedException(String message) {
        super(message);
    }
}
```
- `MyCheckedException`는 `Exception`을 상속 받았다. 고로 체크 예외가 된다.
- 예외가 제공하는 기본 기능이 있는데, 그 중에 오류 메시지를 보관하는 기능도 있다. 예제에서 보는 것처럼 생성자를 통해서 해당 기능을 그대로 사용하면 편리하다.
- `super(message)`로 전달한 메시지는 `Throwable`에 있는 `detailMessage`에 보관된다. (String detailMessage)
- `getMessage()`를 통해 조회할 수 있다.

#### 체크 예외를 밖으로 던지는 코드

```java
public void callThrow() throws MyCheckedException {
    client.call();
}
```
- 체크 예외를 처리할 수 없을 때는 `throws` 키워드를 사용해서, `method() throws 예외`와 같이 밖으로 던질 예외를 필수로 지정해주어야 한다.
- `throws`를 지정하지 않으면 컴파일 오류가 발생한다.

#### 체크 예외의 장단점
- **장점**: 개발자가 실수로 예욀르 누락하지 않도록 컴파일러를 통해 문제를 잡아주는 훌륭한 안전 장치이다. 이를 통해 개발자는 어떤 체크 예외가 발생하는지 쉽게 파악할 수 있다.
- **단점**: 하지만 실제로는 개발자가 모든 체크 예외를 반드시 잡거나 던지도록 처리해야 하기 때문에, 너무 번거로운 일이 된다. 크게 신경쓰고 싶지 않은 예외까지 모두 챙겨야 한다.

### 언체크 예외
- `RuntimeException`과 그 하위 예외는 언체크 예외로 분류된다.
- 언체크 예외는 말 그대로 컴파일러가 예외를 체크하지 않는다는 뜻이다.
- 언체크 예외는 체크 예외와 기본적으로 동일하다. 차이가 있다면 예외를 던지는 `throws`를 선언하지 않고, 생략할 수 있다. 생략한 경우 자동으로 예외를 던진다.

#### 체크 예외 vs 언체크 예외
- **체크 예외**: 예외를 잡아서 처리하지 않으면 항상 `throws` 키워드를 사용해서 던지는 예외를 선언해야 한다.
- **언체크 예외**: 예외를 잡아서 처리하지 않아도 `throws` 키워드를 생략할 수 있다.

#### 언체크 예외의 장단점
- **장점**: 신경쓰고 싶지 않은 언체크 예외를 무시할 수 있다. 체크 예외의 경우 처리할 수 없는 예외를 밖으로 던지려면 항상 `throws 예외`를 선언해야 하지만, 언체크 예외는 이 부분을 생략할 수 있다.
- **단점**: 언체크 예외는 개발자가 실수로 예외를 누락할 수 있다. 반면에 체크 예외는 컴파일러를 통해 예외 누락을 잡아준다.

#### 정리
체크 예외와 언체크 예외의 차이는 예외 처리를 할 수 없을 때 예외를 밖으로 던지는 부분에 있다. 이 부분을 필수로 선언해야 하는가 생략할 수 있는가의 차이다.

### 예외 처리2 - 실습
앞서 만든 프로그램은 반환 값을 사용해서 예외를 처리했다. 다음과 같은 문제가 있었다.
- 정상 흐름과 예외 흐름이 섞여 있기 때문에 코드를 한 눈에 이해하기 어렵다.
- 심지어 예외 흐름이 더 많은 코드 분량을 차지한다. 실무에서는 예외 처리가 훨씬 더 복잡하다.

#### 예제 코드
```java
public class NetworkClientException extends Exception {
    
    private String errorCode;
    
    public NetworkClientException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}
```
- **오류 코드**
  - 이전에는 오류 코드(`errorCode`)를 반환 값으로 리턴해서, 어떤 오류가 발생했는지 구분했다.
  - 여기서는 어떤 종류의 오류가 발생했는지 구분하기 위해 예외 안에 오류 코드를 보관한다.
- **오류 메시지**
  - 오류 메시지(`message`)에는 어떤 오류가 발생했는지 개발자가 보고 이해할 수 있는 설명을 담아둔다.
  - 오류 메시지는 상위 클래스인 `Throwable`에서 기본으로 제공하는 기능을 사용한다.

```java
public class NetworkClient {
    
    private final String address;
    public boolean connectError;
    public boolean sendError;
    
    public NetworkClient(String address) {
        this.address = address;
    }
    
    public void connect() throws NetworkClientException {
        if (connectError) {
            throw new NetworkClientException("connectError", address + " 서버 연결 실패");
        }
        // 연결 성공
        System.out.println(address + " 서버 연결 성공");
    }
    
    public void send(String data) throws NetworkClientException {
        if (sendError) {
            throw new NetworkClientException("sendError", address + " 서버에 데이터 전송 실패: " + data)
        }
        // 전송 성공
        System.out.println(address + " 서버에 데이터 전송: " + data);
    }
    
    public void disconnect() {
        System.out.println(address + " 서버 연결 해제");
    }
    
    public void initError(String data) {
        if (data.contains("error1")) {
            connectError = true;
        }
        if (data.contains("error2")) {
            sendError = true;
        }
    }
}
```

#### 남은 문제
- 예외 처리를 도입했지만, 아직 예외가 복구되지 않는다.
- 사용 후에는 반드시 `disconnect()`를 호출해서 연결을 해제해야 한다.

#### 개선된 코드 1
```java
public class NetworkService {
    public void sendMessage(String data) {
        String address = "http://example.com";
        
        NetworkClient client = new NetworkClient(address);
        client.initError(data);
        
        try {
            client.connect();
            client.send(data);
            client.disconnect(); // 예외 발생 시 무시
        } catch (NetworkClientException e) {
            System.out.println("[오류] 코드: " + e.getErrorCode() + ", 메시지: " + e.getMessage);
        }
    }
}
```
- 하나의 `try`안에 정상 흐름을 모두 담는다.
- 예외 부분은 `catch`블럭에서 해결한다.
- 정상 흐름은 `try`블럭에 들어가고, 예외 흐름은 `catch`블럭으로 명확하게 분리할 수 있다.

#### 남은 문제
- 사용 후에는 반드시 `disconnect()`를 호출해서 연결을 해제해야 한다.

#### 개선된 코드 2
```java
public class NetworkService {
    public void sendMessage(String data) {
        String address = "http://example.com";
        
        NetworkClient client = new NetworkClient(address);
        client.initError(data);
        
        try {
            client.connect();
            client.send(data);
        } catch (NetworkClientException e) {
            System.out.println("[오류] 코드: " + e.getErrorCode() + ", 메시지: " + e.getMessage);
        }
        // NetworkClientException이 아닌 다른 예외가 발생해서 예외가 밖으로 던져지면 무시된다.
        client.disconnect();
    }
}
```
코드를 실행해보면 오류가 발생해도 서버 연결 해제에 성공하는 것을 확인할 수 있다.

#### 하지만 지금과 같은 방식에는 큰 문제가 있다.
바로 `catch`에서 잡을 수 없는 예외가 발생할 때이다.
따라서 `client.disconnect()`가 호출되지 않는 문제가 발생한다.

### finally
자바는 어떤 경우라도 반드시 호출되는 `finally` 기능을 제공한다.
```java
try {
    // 정상 흐름
} catch {
    // 예외 흐름
} finally {
    // 반드시 호출해야 하는 마무리 흐름
}
```
- 정상 흐름 -> `finally`
- 예외 catch -> `finally`
- 잡을 수 없는 예외 던짐 -> `finally`
  - `finally`코드 블럭이 끝나고 나서 이후에 예외가 밖으로 던져진다.

#### `finally`블럭은 반드시 호출된다. 따라서 주로 `try`에서 사용한 자원을 해제할 때 주로 사용한다.

#### 개선된 코드 3
```java
public class NetworkService {
    public void sendMessage(String data) {
        String address = "http://example.com";
        
        NetworkClient client = new NetworkClient(address);
        client.initError(data);
        
        try {
            client.connect();
            client.send(data);
        } catch (NetworkClientException e) {
            System.out.println("[오류] 코드: " + e.getErrorCode() + ", 메시지: " + e.getMessage());
        } finally {
            client.disconnect();
        }
    }
}
```

처리할 수 없는 `RuntimeException`이 발생했다고 가정
```java
전송할 문자: error2
https://example.com 서버 연결 성공
https://example.com 서버 연결 해제
Exception in thread "main" java.lang.RuntimeException: ex
at exception.ex2.NetworkClientV2.send(NetworkClientV2.java:24)
at exception.ex2.NetworkServiceV2_5.sendMessage(NetworkServiceV2_5.java:13)
at exception.ex2.MainV2.main(MainV2.java:22)
```
- 예외를 밖으로 던지는 경우에도 서버 연결 해제에 성공
- `catch`에서 잡을 수 없는 예외가 발생해서, 예외를 밖으로 던지는 경우에도 `finally`를 먼저 호출하고 나서 예외를 밖으로 던진다.

### 실무 예외 처리 방안
- **처리할 수 없는 예외**: 예외를 잡아서 복구할 수 있는 예외보다 복구할 수 없는 예외가 더 많다.
- **체크 예외의 부담**: 처리할 수 없는 예외는 밖으로 던져야 한다. 체크 예외이므로 `throws`에 던질 대상을 일일이 명시해야 한다.

사실 `Service`를 개발하는 개발자 입장에서 수 많은 라이브러리에서 쏟아지는 모든 예외를 다 다루고 싶지는 않을 것이다.
특히 본인이 해결할 수도 없는 모든 예외를 다 다루고 싶지는 않을 것이다. 본인이 해결할 수 있는 예외만 잡아서 처리하고, 본인이 해결할 수 없는 예외는 신경쓰지 않는 것이 더 나은 선택일 수 있다.

#### 예외 공통 처리
처리할 수 없는 예외들은 중간에 여러 곳에서 나누어 처리하기 보다는 예외를 공통적으로 처리할 수 있는 곳을 만들어서 한 곳에서 해결하면 된다.</br>
고객에게는 오류 메시지를 보여주고, 내부 개발자가 문제 상황을 빠르게 인지할 수 있도록 오류에 대한 로그를 남겨두면 된다. 

### 예시 코드
```java
private static void exceptionHandler(Exception e) {
    // 공통 처리
    System.out.println("사용자 메시지: 죄송합니다. 알 수 없는 문제가 발생했습니다.");
    System.out.println("==개발자용 디버깅 메시지==");
    e.printStackTrace(); // System.err에 스택 트레이스 출력
    
    // 필요하면 예외 별로 별도의 추가 처리 가능
    if (e instanceof SendException sendEx) {
        System.out.println("[전송 오류] 전송 데이터: " + sendEx.getSendData());
    }
}
```

실무에서는 콘솔에 바로 결과를 출력하는 `e.printStackTrace()`는 잘 사용하지 않는다.
대신에 로그 라이브러리를 통해서 예외 스택 트레이스를 출력한다.

### try-with-resources
애플리케이션에서 외부 자원을 사용하는 경우 외부 자원을 해제해야 한다.</br>
따라서 `finally`구문을 반드시 사용해야 한다.</br>
자바에서는 Try with resources라는 편의 기능을 도입했다. 여기서 자원은 `try`가 끝나면 반드시 종료해서 반납해야 하는 외부 자원을 뜻한다.

이 기능을 사용하려면 먼저 `AutoCloseable`인터페이스를 구현해야 한다.
```java
package java.lang;

public interface AutoCloseable {
    void close() throws Exception;
}
```
이 인터페이스를 구현하면 Try with resoureces를 사용할 때 `try`가 끝나는 시점에 `close()`가 자동으로 호출된다.

그리고 다음과 같이 Try with resources 구문을 사용하면 된다.
```java
try (Resource resource = new Resource()) {
    // 리소스를 사용하는 코드
}
```

#### 개선된 코드 4
```java
public class NetworkService {
    public void sendMessage(String data) {
        String address = "http://example.com";

        try (NetworkClient client = new NetworkClient(address)) {
            client.initError(data);
            client.connect();
            client.send(data);
        } catch (Exception e) {
            System.out.println("[예외 확인]: " + e.getMessage());
            throw e;
        }
    }
}
```
- Try with resources 구문은 `try`괄호 안에 사용할 자원을 명시한다.
- 이 자원은 `try`블럭이 끝나면 자동으로 `AutoCloseable.close()`를 호출해서 자원을 해제한다.
- 참고로 여기서 `catch`블럭 없이 `try`블럭만 있어도 `close()`는 호출된다.
- 여기서 `catch`블럭은 단순히 발생한 예외를 잡아서 예외 메시지를 출력하고, 잡은 예외를 `throw`를 사용해서 다시 밖으로 던진다.

#### Try with resoureces 장점
- **리소스 누수 방지**
- **코드 간결성 및 가독성 향상**: 명시적인 `close()`호출이 필요없어 코드가 더 간결하고 읽기 쉬워진다.
- **스코프 범위 한정**: 예를 들어 리소스로 사용되는 `client`변수의 스코프가 `try`블럭 안으로 한정된다. 따라서 코드 유지보수가 더 쉬워진다.
- **조금 더 빠른 자원 해제**: 기존에는 try->catch->finally로 catch 이후에 자원을 반납했다. Try with resources 구문은 `try`블럭이 끝나면 즉시 `close()를 호출한다.