package static1;

public class Data3 {
    public String name;
    public static int count; // static 변수는 공용으로 쓸 변수로 이해하면 된다.

    public Data3(String name) {
        this.name = name;
        count++;
    }
}
