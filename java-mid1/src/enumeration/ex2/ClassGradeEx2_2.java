package enumeration.ex2;

public class ClassGradeEx2_2 {

    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        // 처음 보는 개발자는 객체 생성을 하라는 의미로 받아들일 수 있다.
        // 기본 생성자의 접근 제어자를 private로 지정해 인스턴스 생성을 막을 수 있다.
//        ClassGrade newClassGrade = new ClassGrade();
//        int result = discountService.discount(newClassGrade, price);
//        System.out.println("newClassGrade 등급의 할인 금액: " + result);
    }
}
