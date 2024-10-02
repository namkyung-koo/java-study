package lang.string.test;

public class TestString2 {

    public static void main(String[] args) {
        String[] arr = {"hello", "java", "jvm", "spring", "jpa"};

        int len = 0, sum = 0;
        for (String s : arr) {
            len = s.length();
            System.out.println(s + ":" + len);
            sum += len;
        }
        System.out.println("sum = " + sum);
    }
}
