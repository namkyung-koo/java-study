package lang.immutable.address;

public class MemberMainV2 {

    public static void main(String[] args) {
        ImutableAddress address = new ImutableAddress("서울");

        MemberV2 memberA = new MemberV2("회원A", address);
        MemberV2 memberB = new MemberV2("회원B", address);

        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);
        System.out.println();

        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);
        System.out.println();


        // 회원B의 주소를 부산으로 변경해야함
//        memberB.getAddress().setValue("부산"); // 컴파일 오류
        memberB.setAddress(new ImutableAddress("부산"));
        System.out.println("부산 -> memberB.address");
        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);
    }
}
