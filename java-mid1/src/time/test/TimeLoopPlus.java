package time.test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimeLoopPlus {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2024, 1, 1);
        for (int i = 0; i < 5; i++) {
            LocalDate nextDate = localDate.plus(2 * i, ChronoUnit.WEEKS);
            System.out.println("날짜 " + (i + 1) + ": " + nextDate);
        }
    }
}
