package collection.map.test;

import java.util.*;

public class ItemPriceTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(Map.of("사과", 500, "바나나", 500, "망고", 1000, "딸기", 1000));
        List<String> list = new ArrayList<>();

        for (String key : map.keySet()) {
            Integer value = map.get(key);
            if (value == 1000) {
                list.add(key);
            }
        }
        System.out.println(list);
    }
}
