package poly.ex3;

abstract class AbstractAnimal {

    AbstractAnimal() {
        System.out.println("추상 클래스 생성자 호출");
    }

    public abstract void sound(); // 추상 메서드는 메서드 바디가 없다.

    public void move() {
        System.out.println("동물이 움직입니다.");
    }
}

