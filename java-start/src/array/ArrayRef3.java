package array;

public class ArrayRef3 {

        public static void main(String[] args) {
            int[] students = new int[]{90, 80, 70, 60, 50};

            // 반복문으로 코드 리팩토링
            for (int i = 0; i < students.length; i++) {
                System.out.println("학생" + (i + 1) + " 점수: " + students[i]);
            }
        }
}
