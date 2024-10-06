package enumeration.ref1;

public class ClassGrade {

    private final int discountPersent;

    public static final ClassGrade BASIC = new ClassGrade(10); // x001
    public static final ClassGrade GOLD = new ClassGrade(20); // x002
    public static final ClassGrade DIAMOND = new ClassGrade(30); // x003

    public ClassGrade(int discountPersent) {
        this.discountPersent = discountPersent;
    }

    public int getDiscountPersent() {
        return discountPersent;
    }
}
