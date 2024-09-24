package class1;

public class ClassStart5 {

    public static void main(String[] args) {
        Student student1; // Student 변수 선언
        student1 = new Student(); // Student 인스턴스 생성
        student1.name = "학생1";
        student1.age = 15;
        student1.grade = 90;

        Student student2;
        student2 = new Student();
        student2.name = "학생2";
        student2.age = 16;
        student2.grade = 80;

//        Student[] students = new Student[]{student1, student2};
        Student[] students = {student1, student2}; // 코드 최적화 가능

        // 단축키 'iter'
        for (Student student : students) {
            System.out.println("이름:" + student.name + " 나이:" + student.age + " 성적:" + student.grade);
        }
    }
}
