package pack;

public class PackageMain1 {

    public static void main(String[] args) {
        Data data = new Data();
        // 다른 패키지에 위치한 class
        pack.a.User user = new pack.a.User();
    }
}
