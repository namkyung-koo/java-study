package poly.ex2;

public class AnimalPolyMain2 {

    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Cow cow = new Cow();
        Duck duck = new Duck();
        Animal[] animals = {cat, dog, cow, duck};

        for (Animal animal : animals) {
            soundAnimal(animal);
        }
    }

    public static void soundAnimal(Animal animal) {
        animal.sound();
    }
}
