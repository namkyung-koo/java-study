package method;

public class MethodCasting2 {

    public static void main(String[] args) {
        int number = 100;
        printNumber(number);
    }

    // double형 매개변수(파라미터)에 int형 인수를 전달하는데 문제없이 잘 작동한다.
    public static void printNumber(double d) {
        System.out.println("숫자: " + d);
    }
}
