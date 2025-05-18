package backend.backendweb.week_01.yh.jung;

public class EmailSender {
    public void send(String to, String message) {
        System.out.println("[Email] " + to + "에게 메시지 전송: " + message);
    }
}