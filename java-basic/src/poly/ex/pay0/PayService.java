package poly.ex.pay0;

public class PayService {

    public void processPay(String option, int amount) {

        System.out.println("결제를 시작합니다: option=" + option + ", amount=" + amount);
        Pay pay = selectPay(option);

        if (pay != null) {
            pay.pay(amount);
            System.out.println("결제가 성공했습니다.");
        } else {
            System.out.println("결제가 실패했습니다.");
        }
    }

    public static Pay selectPay(String payName) {
        if (payName.equals("kakao")) {
            return new KakaoPay();
        } else if (payName.equals("naver")) {
            return new NaverPay();
        } else {
            return null;
        }
    }
}
