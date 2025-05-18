package backend.backendweb.week_01.mk.jang.week01.observer;

/**
 * [옵서버 패턴]: 주체 인터페이스 (Subject Interface)
 *
 * 옵서버 목록을 관리하고 옵서버를 등록, 해제, 통지하는 메소드를 제공하는 객체(주체)의 인터페이스를 정의.
 * 상태 변화를 감지하고 옵저버에게 알리는 역할.
 *
 */
public interface ReportSubject {

    // 옵서버 등록
    void attach(ReportObserver observer);

    // 옵서버 등록 해제
    void detach(ReportObserver observer);

    // 이벤트 발생
    void notifyObservers(String reportType, int dataSize);

}
