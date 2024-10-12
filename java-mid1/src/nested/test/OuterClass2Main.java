package nested.test;

public class OuterClass2Main {

    public static void main(String[] args) {
        OuterClass2 outerClass2 = new OuterClass2();
        // 잊어 버린 점: 내부 클래스의 인스턴스 생성하기 위해서는 반드시 바깥 클래스의 인스턴스가 생성되어야 한다.
        OuterClass2.InnerClass innerClass = outerClass2.new InnerClass();
        innerClass.hello();
    }
}
