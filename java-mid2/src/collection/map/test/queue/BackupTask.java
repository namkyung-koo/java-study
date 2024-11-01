package collection.map.test.queue;

public class BackupTask implements Task {
    @Override
    public void excute() {
        System.out.println("자료 백업...");
    }
}
