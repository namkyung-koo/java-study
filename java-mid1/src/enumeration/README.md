### 문자열과 타입 안전성1
자바가 제공하는 열거형(Enum Type)을 제대로 이해하기 위해 열거형이 생겨난 이유에 대해 학습해보자

```java
public class DiscountService {
    
    public int discount(String grade, int price) {
        int discountPercent = 0;
        
        if (grade.equals("BASIC")) {
            discountPercent = 10;
        } else if (grade.equals("GOLD")) {
            discountPercent = 20;
        } else if (grade.equals("DIAMOND")) {
            discountPercent = 30;
        } else {
            System.out.println(grade = ": 할인X");
        }
        return price * discountPercent / 100;
    }
}
```

```java
public class StringGradeEx0_1 {

    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();
        int basic = discountService.discount("BASIC", price);
        int gold = discountService.discount("GOLD", price);
        int diamond = discountService.discount("DIAMOND", price);

        System.out.println("BASIC 등급의 할인 금액: " + basic);
        System.out.println("GOLD 등급의 할인 금액: " + gold);
        System.out.println("DIAMOND 등급의 할인 금액: " + diamond);
    }
}
```

### 문제점
단순히 문자열을 입력하는 방식은
- **타입 안정성 부족**: 문자열은 오타가 발생하기 쉽고, 유효하지 않은 값이 입력될 수 있다. (컴파일 시 오류 감지 불가)
- **데이터 일관성**: "GOLD", "gold", "Gold" 등 다양한 형식의 문자열을 입력할 수 있어 일관성이 떨어진다.

#### 결국 위의 문제를 해결하려면 특정 범위로 값을 제한해야 한다. 그러나 String 타입을 사용해서는 문제를 해결할 수 없다.

### 문자열과 타입 안전성2
대안으로 문자열 상수를 사용해보자

```java
public class StringGrade {
    public static final String BASIC = "BASIC";
    public static final String GOLD = "GOLD";
    public static final String DIAMOND = "DIAMOND";
}
```

```java
public class DiscountService {

    public int discount(String grade, int price) {
        int discountPercent = 0;

        if (grade.equals(StringGrade.BASIC)) {
            discountPercent = 10;
        } else if (grade.equals(StringGrade.GOLD)) {
            discountPercent = 20;
        } else if (grade.equals(StringGrade.DIAMOND)) {
            discountPercent = 30;
        } else {
            System.out.println(grade + ": 할인X");
        }
        return price * discountPercent / 100;
    }
}
```

```java
public class StringGradeEx1_1 {

    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();
        int basic = discountService.discount(StringGrade.BASIC, price);
        int gold = discountService.discount(StringGrade.GOLD, price);
        int diamond = discountService.discount(StringGrade.DIAMOND, price);

        System.out.println("BASIC 등급의 할인 금액: " + basic);
        System.out.println("GOLD 등급의 할인 금액: " + gold);
        System.out.println("DIAMOND 등급의 할인 금액: " + diamond);
    }
}
```

#### 두 번째 방법 역시 존재하지 않는 등급("VIP")이나 오타("DIAMONDD")를 인자로 전달해도 오류가 발생하지 않는 문제가 있다.

### 타입 안전 열거형 패턴 - Type-Safe Enum Pattern
지금까지 설명한 문제를 해결하기 위해 나온 결과가 바로 타입 안전 열거형 패턴이다.</br>
`String`처럼 아무런 문자열이나 다 사용할 수 있는 것이 아니라, `개발자가 나열한 항목`만 안전하게 사용할 수 있다.

```java
public class ClassGrade {
    public static final ClassGrade BASIC = new ClassGrade();
    public static final ClassGrade GOLD = new ClassGrade();
    public static final ClassGrade DIAMOND = new ClassGrade();
}
```
- 회원 등급을 다루는 클래스를 만들고, 인스턴스를 상수로 선언한다.
- 상수마다 별도의 인스턴스를 생성하여 대입한다.
  - `static`을 사용해 상수를 메서드 영역에 선언한다.
  - `final`을 사용해 인스턴스(참조값)을 변경할 수 없게 한다.

```java
public class DiscountService {

    public int discount(ClassGrade classGrade, int price) {
        int discountPercent = 0;

        if (classGrade == ClassGrade.BASIC) {
            discountPercent = 10;
        } else if (classGrade == ClassGrade.GOLD) {
          discountPercent = 20;
        } else if (classGrade == ClassGrade.DIAMOND) {
          discountPercent = 30;
        } else {
          System.out.println("할인X");
        }
        return price * discountPercent / 100;
    }
}
```
- `discount()` 메서드는 매개변수로 `ClassGrade` 클래스를 사용한다.
- 값을 비교할 때는 `==`(동등성 비교)를 통해 참조값을 직접 비교한다.

```java
public class ClassGradeEx2_1 {

  public static void main(String[] args) {
    int price = 10000;
    
    DIscountService = discountService = new DiscountService();
    int basic = discountService.discount(ClassGrade.BASIC, price);
    int gold = discountService.discount(ClassGrade.GOLD, price);
    int diamond = discountService.discount(ClassGrade.DIAMOND, price);

    System.out.println("BASIC 등급의 할인 금액: " + basic);
    System.out.println("GOLD 등급의 할인 금액: " + gold);
    System.out.println("DIAMOND 등급의 할인 금액: " + diamond);
  }
}
```

### private 생성자
이 방식은 외부에서 임의로 `ClassGrade`의 인스턴스를 생성할 수 있다는 문제가 있다.</br>
생성자의 접근 제어자를 `private`로 선언해 인스턴스 생성을 막자주자

### 타입 안전 열겨형 패턴(Type-Safe Enum Pattern)의 장점
- **타입 안정성 향상**: 정해진 객체만 인자로 사용할 수 있기 때문에, 잘못된 값을 입력하는 문제르 근본적으로 방지할 수 있다.
- **데이터 일관성**: 정해진 객체만 사용하므로 데이터의 일관성이 보장된다.

### 타입 안전 열겨형 패턴(Type-Safe Enum Pattern)의 단점
- 많은 코드를 작성해야 한다.
- 뿐만 아니라 `private` 생성자를 추가해야 한다.

### [최종 보스] 열거형 - Enum Type
자바에서 `타입 안전 열거형 패턴`을 사용하기 쉽게 `열거형`을 지원한다.

```java
public enum Grade {
    BASIC, GOLD, DIAMOND
}
```
- `class` 대신에 `enum`을 사용한다.
- 원하는 상수 이름을 그저 나열하면 된다.
- 열거형도 클래스이다.
- 열거형은 자동으로 `java.lang.Enum`을 상속 받는다.
- 외부에서 임의로 생성할 수 없다.(자동으로 `private` 생성자를 만듦)

```java
public class DiscountService {
    
    public int discount(Grade grade, int price) {
        int discountPercent = 0;
        
        if (grade == Grade.BASIC) {
            discountPercent = 10;
        } else if (grade == Grade.GOLD) {
            discountPercent = 20;
        } else if (grade == Grade.DIAMOND) {
            discountPercent = 30;
        } else {
          System.out.println("할인X");
        }
        return price * discountPercent / 100;
    }
}
```

```java
public class EnumEx3_1 {

  public static void main(String[] args) {
    int price = 10000;
    
    DiscountService = discountService = new DiscountService();
    
    int basic = discountService.discount(Grade.BASIC, price);
    int gold = discountService.discount(Grade.GOLD, price);
    int diamond = discountService.discount(Grade.DIAMOND, price);

    System.out.println("BASIC 등급의 할인 금액: " + basic);
    System.out.println("GOLD 등급의 할인 금액: " + gold;
    System.out.println("DIAMOND 등급의 할인 금액: " + diamond);
  }
}
```

### 열거형(ENUM)의 장점
- **타입 안정성 향상**: 열거형은 사전에 정의된 상수들로만 구성되므로, 유효하지 않은 값이 입력될 가능성이 없다. (컴파일 오류 발생)
- **간결성 및 일관성**: 열거형을 사용하면 코드가 더 간결하고 명확해지며, 데이터 일관성이 보장된다.
- **확장성**: 새로운 회원 등급을 탕비에 추가하고 싶을 때, 그저 ENUM에 새로운 상수를 추가하기만 하면 된다.

### ENUM - 주요 메서드
- **values()**: 모든 ENUM 상수를 포함하는 배열을 반환한다.
- **valueOf(String name)**: 주어진 이름과 일치하는 ENUM 상수를 반환한다.
- **name()**: ENUM 상수의 이름을 문자열로 반환한다.
- **ordinal()**: ENUM 상수의 선언 순서(0부터 시작)를 반환한다.
- **toString()**: ENUM 상수의 이름을 문자열로 반환한다. `toString()`은 오버라이딩 가능.

### 열거형 정리
- 열거형은 `java.lang.Enum`를 자동(강제)으로 상속 받는다.
- 열거형은 이미 `java.lang.Enum`을 상속 받았기 때문에 추가로 다른 클래스를 상속을 받을 수 없다.
- 열거형은 인터페이스를 구현할 수 있다.
- 열거형에 추상 메서드를 선언하고 구현할 수 있다.