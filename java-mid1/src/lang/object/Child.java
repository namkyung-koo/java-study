package lang.object;

// 이 경우 Object 클래스를 묵시적으로 상속 받지 않는다.
public class Child extends Parent{

    public void childMethod() {
        System.out.println("Child.childMethod");
    }
}
