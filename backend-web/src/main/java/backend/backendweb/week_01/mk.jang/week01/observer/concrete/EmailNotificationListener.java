package backend.backendweb.week_01.mk.jang.week01.observer.concrete;

import backend.backendweb.week_01.mk.jang.week01.observer.ReportObserver;

/**
 * [옵서버 패턴]: 구체적인 옵서버 (Concrete Observer)
 *
 */
public class EmailNotificationListener implements ReportObserver {

    private final String adminEmail;

    public EmailNotificationListener(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    /**
     * 주체가 리포트 생성에 대한 이메일 알림을 보내기 위해 호출하는 메소드.
     */
    @Override
    public void update(String reportType, int dataSize) {
        String message = reportType + " 보고서가 성공적으로 생성되었습니다. (데이터 항목 수: " + dataSize + ")";
        sendEmail(adminEmail, message);
    }

    /**
     * 이메일 전송.
     */
    private void sendEmail(String to, String message) {
        System.out.println("[Email] " + to + "에게 메시지 전송: " + message);
    }

}
