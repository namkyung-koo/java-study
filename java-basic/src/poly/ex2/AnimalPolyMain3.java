package poly.ex2;

public class AnimalPolyMain3 {

    public static void main(String[] args) {
        Animal[] animals = {new Cat(), new Dog(), new Cow(), new Duck()};

        for (Animal animal : animals) {
            soundAnimal(animal);
        }
    }

    public static void soundAnimal(Animal animal) {
        animal.sound();
    }
}
