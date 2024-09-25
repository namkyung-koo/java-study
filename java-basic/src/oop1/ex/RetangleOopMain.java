package oop1.ex;

public class RetangleOopMain {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();

        System.out.println("넓이: " + rectangle.area);
        System.out.println("둘레 길이: " + rectangle.perimeter);
        System.out.println("정사각형 여부: " + rectangle.square);
    }
}
