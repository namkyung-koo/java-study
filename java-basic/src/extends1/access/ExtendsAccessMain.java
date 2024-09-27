package extends1.access;

import extends1.access.child.Child;
import extends1.access.parent.Parent;

public class ExtendsAccessMain {

    public static void main(String[] args) {
        Child child = new Child();
        child.call();
//        Parent parent = new Parent();
    }
}
