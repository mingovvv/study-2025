package backend.backendweb.week_01.mk.jang.week01.observer.concrete;

import backend.backendweb.week_01.mk.jang.week01.observer.ReportObserver;
import backend.backendweb.week_01.mk.jang.week01.observer.ReportSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * [옵저버 패턴]: 구체적인 주체 (Concrete Subject) 구현
 *
 * 인터페이스를 구현하여 옵저버를 관리하고, 리포트 생성과 같은 특정 이벤트 발생 시 등록된 옵저버들에게 알림을 전달하는 역할
 *
 */
public class DefaultReportEventNotifier implements ReportSubject {

    /**
     * 이 주체에 등록된 옵저버들의 리스트.
     */
    private final List<ReportObserver> observers;

    /**
     * 옵저버 리스트를 초기화
     */
    public DefaultReportEventNotifier() {
        this.observers = new ArrayList<>();
    }

    /**
     * 옵저버를 이 주체에 등록. 등록된 옵저버는 이후 이벤트 알림을 받게 됨.
     */
    @Override
    public void attach(ReportObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("[Notifier] 옵서버 등록됨: " + observer.getClass().getSimpleName());
        }
    }

    /**
     * 주체에서 옵저버를 제거함. 제거된 옵저버는 더 이상 이벤트 알림을 받지 않음.
     */
    @Override
    public void detach(ReportObserver observer) {
        boolean removed = observers.remove(observer);
        if (removed) {
            System.out.println("[Notifier] 옵서버 제거됨: " + observer.getClass().getSimpleName());
        }
    }

    /**
     * 이 주체에 등록된 모든 옵저버에게 리포트 생성 이벤트와 관련된 정보를 전달.
     * 알림은 등록된 각 옵저버의 메소드를 호출함으로써 수행됨.
     */
    @Override
    public void notifyObservers(String reportType, int dataSize) {

        if (observers.isEmpty()) {
            System.out.println("[Notifier] 등록된 옵서버가 없어 알림을 생략합니다.");
            return;
        }

        System.out.println("[Notifier] " + observers.size() + "개의 옵서버에게 알림 전송 중 (타입: " + reportType + ", 크기: " + dataSize + ")...");

        for (ReportObserver observer : new ArrayList<>(observers)) {
            observer.update(reportType, dataSize);
        }

    }
}
