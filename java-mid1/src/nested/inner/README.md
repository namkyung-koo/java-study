### 내부 클래스
- 바깥 클래스의 인스턴스에 소속된다.
```java
public class InnerOuter {
    private static int outClassValue = 3;
    private int outInstanceValue = 2;
    
    class Inner {
        private int innerInstanceValue = 1;
        
        public void print() {
            // 자신의 멤버에 접근
            System.out.println(innerInstanceValue);
            
            // 외부 클래스의 인스턴스 멤버에 접근 가능. private도 접근 가능
            System.out.println(outInstanceValue);
            // 외부 클래스의 클래스 멤버에 접근 가능. private도 접근 가능
            System.out.println(outClassValue);
        }
    }
}
```

```java
public class InnerOuterMain {

    public static void main(String[] args) {
        InnerOuter outer = new InnerOuter();
        InnerOuter.Inner inner = outer.new Inner();
        inner.print();
    }
}
```
- 내부 클래스는 `바깥클래스의 인스턴스 참조.new 내부클래스()`로 생성할 수 있다.
    - InnerOuter.Inner inner = outer.new Inner();
- 이렇게 생성된 내부 클래스는 개념상 바깥 클래스의 인스턴스 내부에 생성된다.
    - 실제로는 내부 인스턴스는 바깥 인스턴스의 참조값을 보관한다.
- 따라서 바깥 클래스의 인스턴스를 먼저 생성해야 내부 클래스의 인스턴스를 생성할 수 있다.