package static2;

import static static2.DecoData.*;

public class DecoDataMain {

    public static void main(String[] args) {
        System.out.println("1, 정적 호출");
        DecoData.staticCall();

        System.out.println("2. 인스턴스 호출");
        DecoData decoData1 = new DecoData();
        decoData1.instanceCall();
        decoData1.instanceCall();
        decoData1.instanceCall();
        decoData1.instanceCall();

        System.out.println("3. 인스턴스 호출2");
        DecoData decoData2 = new DecoData();
        decoData2.instanceCall();

        // 인스턴스를 통한 접근(권장하지 않는 방법. 인스턴스 메서드와 헷갈릴 수 있다.)
        DecoData decoData3 = new DecoData();
        decoData3.staticCall();

        // 클래스를 통한 접근
        DecoData.staticCall();
    }
}
