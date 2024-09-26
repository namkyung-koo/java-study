package pack;

import pack.a.User;

public class PackageMain3 {

    // 클래스 이름이 겹쳤을 때, 하나만 import 할 수 있다.
    public static void main(String[] args) {
        User userA = new User();
        pack.b.User userB = new pack.b.User();
    }
}
