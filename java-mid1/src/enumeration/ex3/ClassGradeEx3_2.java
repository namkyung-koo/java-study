package enumeration.ex3;

public class ClassGradeEx3_2 {

    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        // enum 타입은 인스턴스 생성 자체가 불가능하다.

//        Grade newGrade = new Grade(); \
//        int result = discountService.discount(newGrade, price);
//        System.out.println("newGrade 등급의 할인 금액: " + result);
    }
}
