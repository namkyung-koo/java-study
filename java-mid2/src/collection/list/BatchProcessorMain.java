package collection.list;

public class BatchProcessorMain {

    public static void main(String[] args) {
//        MyArrayList<Integer> list = new MyArrayList<>();
        MyLinkedList<Integer> list = new MyLinkedList<>();

        BatchProcessor Processor = new BatchProcessor(list);
        Processor.logic(1_000_000);
    }
}
