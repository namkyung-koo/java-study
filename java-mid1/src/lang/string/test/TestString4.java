package lang.string.test;

public class TestString4 {

    public static void main(String[] args) {
        String str = "hello.txt";

        String filename, extName;
        filename = str.substring(0, 5);
        extName = str.substring(5);
        System.out.println("filename = " + filename);
        System.out.println("extName = " + extName);
    }
}
