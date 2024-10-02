package lang.string.equals;

public class StringEqualsMain2 {

    public static void main(String[] args) {
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println("메서드 호출 비교1: " + isSame(s1, s2));

        String s3 = "hello";
        String s4 = "hello";
        System.out.println("메서드 호출 비교2: " + isSame(s3, s4));
    }

    private static boolean isSame(String x, String y) {
//        return x == y;
        return x.equals(y);
    }
}
