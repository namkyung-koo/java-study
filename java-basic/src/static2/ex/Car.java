package static2.ex;

public class Car {

    private String name;
    private static int quantity;

    Car(String name) {
        this.name = name;
        quantity++;
        System.out.println("차량 구입, 이름: " + name);
    }

    static void showTotalCars() {
        System.out.println("구매한 차량 수: " + quantity);
    }
}
