package method;

public class Overloading3 {

    public static void main(String[] args) {

        // 첫 번째 add 메서드를 삭제하면 자동 형변환이 되면서 두 번째 add 메서드를 호출한다.
        System.out.println("1: " + add(1, 2));
        System.out.println("2: " + add(1.2, 1.5));
    }

//    public static int add(int a, int b) {
//        System.out.println("1번 호출");
//        return a + b;
//    }

    public static double add(double a, double b) {
        System.out.println("2번 호출");
        return a + b;
    }
}
