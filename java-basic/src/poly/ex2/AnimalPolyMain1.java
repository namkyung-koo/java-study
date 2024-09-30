package poly.ex2;

public class AnimalPolyMain1 {

    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Cow cow = new Cow();
        Duck duck = new Duck();

        soundAnimal(cat);
        soundAnimal(dog);
        soundAnimal(cow);
        soundAnimal(duck);
    }

    public static void soundAnimal(Animal animal) {
        animal.sound();
    }
}
