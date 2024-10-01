package lang.object.tostring;

public class ToStringMain1 {

    public static void main(String[] args) {
        Object object = new Object();
        String string = object.toString();

        // toString() 반환값 출력
        System.out.println(string);

        // object 직접 출력
        System.out.println(object);

        // println 내부에서 obj.toString() 메서드를 호출하기 때문에 결과가 같다!
    }
}
