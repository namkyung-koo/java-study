### Class 클래스
- 자바에서 `Class`클래스는 클래스의 정보(메타데이터)를 다루는데 사용된다. 
- `Class` 클래스를 통해 개발자는 실행 중인 자바 애플리케이션 내에서 필요한 클래스의 속성과 메서드에 대한 정보를 조회하고 조작할 수 있다.

### Class 클래스 - 주요 기능
- 타입 정보 얻기: 클래스의 이름, 슈퍼클래스, 인터페이스, 접근 제한자 등과 같은 정보 조회
- 리플렉션: 클래스에 정의된 메서드, 필드, 생성자 등을 조회하고, 이들을 통해 객체 인스턴스를 생성하거나 메서드를 호출
- 동적 로딩과 생성: `Class.forName()` 메서드를 사용하여 클래스를 동적으로 로드하고, `newInstance()` 메서드를 통해 새로운 인스턴스를 생성
- 애노테이션 처리: 클래스에 적용된 애노테이션(annotation)을 조회하고 처

### 예시
`String.class`는 `String`클래스에 대한 `Class`객체를 나타내며, 이를 통해 `String`클래스에 대한 메타데이터를 조회하거나 조작할 수 있다.

``` java
package lang.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassMetaMain {

    // main() 옆에 throws Exception이 없으면 컴파일 오류가 발생한다.
    public static void main(String[] args) throws Exception {
    // 클래스 조회
    Class clazz = String.class; // 1. 클래스에서 조회
    // Class clazz = new String().getClass(); // 2. 인스턴스에서 조회
    // Class clazz = Class.forName("java.lang.String"); // 3. 문자열로 조회

    // 모든 필드(멤버 변수) 조회
    Field[] fields = clazz.getDeclaredFields();

    // 모든 메서드 조회
    Method[] methods = class.getDeclaredMethods();

    // 상위 클래스 정보 조희
    class.getSuperclass().getName();

    // 인터페이스 정보 조회
    Class[] interfaces = class.getInterfaces();
    }
}
```

* `class`는 자바의 예약어이므로 패키지명이나 변수명으로 사용할 수 없다. 
* 따라서 자바 개발자들은 `class` 대신 `clazz`라는 이름을 관행으로 사용한다.

### 클래스 생성하기
- `Class`클래스에는 클래스의 모든 정보가 들어있다.
- 이 정보를 기반으로 인스턴스를 생성하거나, 메서드 호출, 필드의 값 변경을 할 수 있다.

``` java
package lang.clazz;

public class Hello() {
    public String hello() {
        return "hello!";
    }
}
```

``` java
package lang.clazz;

public class ClassCreateMain {
    public static void main(String[] args) throws Exception {
        // Class helloClasss = Hello.class;
        Class helloClass = Class.forName("lang.clazz.Hello");
        Hello hello = (Hello) helloClass.getDeclaredConstructor().newInstance();
        String result = hello.hello();
        System.out.println("result = " + result);
    }
}
```
실행 결과
``` java
result = hello!
```

#### getDeclaredConstructor().newInstance()
- `getDeclaredConstructor()`: 생성자를 선택한다.
- `newInstance()`: 선택된 생성자를 기반으로 인스턴스를 생성한다.

### 리플렉션(Reflection)
- `Class`를 사용하여 클래스의 메타 정보를 기반으로 클래스에 정의된 메서드, 필드, 생성자 등을 조회하고, 이들을 통해 객체 인스턴스를 생성하거나 메서드를 호출하는 작업
