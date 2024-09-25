package construct;

public class MemberInit {
    String name;
    int age;
    int grade;

    // 추가
    // this라는 키워드는 인스턴스 자기 자신의 참조값을 나타낸다.
    void initMember(String name, int age, int grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}
