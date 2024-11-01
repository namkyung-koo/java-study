package collection.map.test.queue;

public class CleanTask implements Task {
    @Override
    public void excute() {
        System.out.println("사용하지 않는 자원 정리...");
    }
}
