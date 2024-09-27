package poly.basic;

public class CastingMain2 {

    public static void main(String[] args) {
        // 부모 변수가 자식 인스턴스 참조(다형적 참조)
        Parent poly = new Child();
        // 단 자식의 기능은 호출할 수 없다. 컴파일 오류 발생
//        poly.childMethod();

        // 일시적 다운캐스팅 - 해당 메서드를 호출하는 순간만 다운캐스팅
        // 참고로 캐스팅을 한다고 해서 Parent poly의 타입이 변하는 것은 아니다.
        ((Child) poly).childMethod();
    }
}
