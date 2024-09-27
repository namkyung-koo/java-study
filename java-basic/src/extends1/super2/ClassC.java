package extends1.super2;

public class ClassC extends ClassB{

    public ClassC() {

        // ClassB는 기본 생성자가 없기 때문에 매개변수를 넘겨주어야 한다.
        super(10, 20);
        System.out.println("ClassC 생성자");
    }
}
