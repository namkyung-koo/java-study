package array;

public class ArrayRef2 {

    public static void main(String[] args) {

        // 자바는 배열을 생성할 때 내부값을 자동으로 초기화한다.

        int[] students; // 배열 변수(배열의 주소/참조값을 담는 변수) 선언
        students = new int[5]; // 배열 생성 (배열의 주소값을 반환)

        System.out.println(students); // 주소값 출력

        // 변수 값 대입
        students[0] = 90;
        students[1] = 80;
        students[2] = 70;
        students[3] = 60;
        students[4] = 50;
        // students[5] = 40; // 범위 초과

        // 반복문으로 코드 리팩토링
        for (int i = 0; i < students.length; i++) {
            System.out.println("학생" + (i + 1) + " 점수: " + students[i]);
        }
    }
}
