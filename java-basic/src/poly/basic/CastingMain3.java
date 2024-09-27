package poly.basic;

// upcasting vs downcasting
public class CastingMain3 {

    public static void main(String[] args) {
        Child child = new Child();
        Parent parent1 = (Parent) child; // 업캐스팅은 (Parent) 생략 가능. 심지어 생략을 권장
        Parent parent2  = child; // 업캐스팅 생략

        parent1.parentMethod();
        parent2.parentMethod();
    }
}
