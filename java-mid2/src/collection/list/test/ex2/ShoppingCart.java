package collection.list.test.ex2;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void displayItems() {
        System.out.println("장바구니 상품 출력");

        int sum = 0;
        for (int i = 0; i < items.size(); i++) {
            System.out.println("상품명: " + items.get(i).getName() + ", 합계: " + items.get(i).getTotalPrice());
            sum += items.get(i).getTotalPrice();
        }
        System.out.println("전체 가격의 합: " + sum);
    }
}
