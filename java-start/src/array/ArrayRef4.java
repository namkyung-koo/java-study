package array;

public class ArrayRef4 {

    public static void main(String[] args) {
        int[] students = {90, 80, 70, 60, 50}; // 'new int[]' 생략 가능. 단, 선언과 동시에 초기화를 진행해야한다.

        // 반복문으로 코드 리팩토링
        for (int i = 0; i < students.length; i++) {
            System.out.println("학생" + (i + 1) + " 점수: " + students[i]);
        }
    }
}
