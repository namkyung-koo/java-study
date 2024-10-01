package poly.ex.sender;

public class EmailSender implements Sender{

    @Override
    public void sendMessage(String msg) {
        System.out.println("메일을 발송합니다: " + msg);
    }
}
