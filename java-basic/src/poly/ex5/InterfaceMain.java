package poly.ex5;

public class InterfaceMain {

    public static void main(String[] args) {
        // 인터페이스 인스턴스 생성 불가
//        InterfaceAnimal interfaceAnimal = new InterfaceAnimal();
        Cat cat = new Cat();
        Dog dog = new Dog();
        Cow cow = new Cow();

        soundAnimal(cat);
        soundAnimal(dog);
        soundAnimal(cow);
    }

    public static void soundAnimal(InterfaceAnimal animal) {
        animal.sound();
    }
}
