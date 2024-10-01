package poly.ex.sender;

public class SmsSender implements Sender{

    @Override
    public void sendMessage(String msg) {
        System.out.println("SMS를 발송합니다: " + msg);
    }
}
