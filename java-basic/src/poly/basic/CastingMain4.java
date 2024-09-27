package poly.basic;

// 다운캐스팅을 자동으로 하지 않는 이유
public class CastingMain4 {

    public static void main(String[] args) {
        Parent parent1 = new Child();
        Child child1 = (Child) parent1;
        child1.childMethod(); // 문제 없는 코드

        Parent parent2 = new Parent();
        Child child2 = (Child) parent2; // ClassCastException 발생
        child2.childMethod();
    }
}
