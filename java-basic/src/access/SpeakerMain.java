package access;

public class SpeakerMain {

    public static void main(String[] args) {
        Speaker speaker = new Speaker(90);
        speaker.showVolume();

        speaker.volumeUp();
        speaker.showVolume();

        speaker.volumeUp();
        speaker.showVolume();

        // 필드에 직접 접근
        System.out.println("volume 필드 직접 접근 수정");
        // private 접근제어자는 클래스 내부에서의 접근을 제외한 모든 접근을 막는다.
//        speaker.volume = 200;
        speaker.showVolume();
    }
}
