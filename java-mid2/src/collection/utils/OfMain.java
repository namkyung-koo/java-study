package collection.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OfMain {

    public static void main(String[] args) {
        // 편리한 불변 컬렉션 생성
        List<Integer> list = List.of(1, 2, 3);
        Set<Integer> set = Set.of(1, 2, 3);
        Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three");

        System.out.println("list = " + list);
        System.out.println("set = " + set);
        System.out.println("map = " + map);
        System.out.println("list.getClass() = " + list.getClass());
        System.out.println("set.getClass() = " + set.getClass());
        System.out.println("map.getClass() = " + map.getClass());

        // Exception in thread "main" java.lang.UnsupportedOperationException
//        list.add(4);
    }
}
