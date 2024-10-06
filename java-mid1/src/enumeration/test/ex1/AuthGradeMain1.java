package enumeration.test.ex1;

public class AuthGradeMain1 {

    public static void main(String[] args) {
//        AuthGrade[] authGrades = {AuthGrade.GUEST, AuthGrade.LOGIN, AuthGrade.ADMIN};
        AuthGrade[] authGrades = AuthGrade.values();
        for (AuthGrade authGrade : authGrades) {
            printAuthGrade(authGrade);
        }
    }

    private static void printAuthGrade(AuthGrade authGrade) {
        System.out.println("grade=" + authGrade + ", level=" + authGrade.getLevel() + ", 설명=" + authGrade.getDescription());
    }
}
