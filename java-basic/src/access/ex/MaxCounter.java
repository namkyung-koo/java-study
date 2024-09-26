package access.ex;

public class MaxCounter {
    private int count;
    private int max;

    MaxCounter(int max) {
        this.max = max;
    }

    public void increment() {
        if (count + 1 > max) {
            System.out.println("최대값을 초과할 수 없습니다.");
            return ;
        }
        count += 1;
    }

    public int getCount() {
        return count;
    }
}
