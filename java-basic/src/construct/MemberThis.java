package construct;

public class MemberThis {
    String nameField;

    /*
    매개 변수에 nameField라는 이름이 없으면 멤버 변수에서 찾는다.
    멤버 변수에도 없으면 에러 발생한다. 항상 this를 적는 코딩 스타일도 있다.
    최근에는 IDE가 발전해서 색으로 뚜렷하게 구분해주기 때문에 this 키워드를 잘 사용하지 않는다.
     */
    void initMember(String nameParamerter) {
        nameField = nameParamerter;
    }
}
