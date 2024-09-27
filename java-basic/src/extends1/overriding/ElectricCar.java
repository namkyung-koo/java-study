package extends1.overriding;

public class ElectricCar extends Car {

    // @이 붙은 부분을 에노테이션이라 한다. (프로그램이 읽을 수 있는 특별한 주석이라 생각할 것)
    @Override
    public void move() {
        System.out.println("전기차를 빠르게 이동합니다.");
    }

    public void charge() {
        System.out.println("차를 충전합니다.");
    }
}
