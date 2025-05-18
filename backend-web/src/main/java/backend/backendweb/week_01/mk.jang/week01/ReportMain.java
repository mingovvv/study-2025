package backend.backendweb.week_01.mk.jang.week01;

import backend.backendweb.week_01.mk.jang.week01.factory_v2.RendererCreator;
import backend.backendweb.week_01.mk.jang.week01.factory_v2.concrete.CsvRendererCreator;
import backend.backendweb.week_01.mk.jang.week01.observer.ReportObserver;
import backend.backendweb.week_01.mk.jang.week01.observer.ReportSubject;
import backend.backendweb.week_01.mk.jang.week01.observer.concrete.DefaultReportEventNotifier;
import backend.backendweb.week_01.mk.jang.week01.observer.concrete.EmailNotificationListener;
import backend.backendweb.week_01.mk.jang.week01.observer.concrete.ReportEventLogger;
import backend.backendweb.week_01.mk.jang.week01.template.AbstractReportGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * 메인 클래스
 * ReportGenerator 클래스를 구성하고 사용한다. (클라이언트 파트)
 *
 * 결과
 * [Notifier] 옵서버 등록됨: ReportEventLogger
 * [Notifier] 옵서버 등록됨: EmailNotificationListener
 * ===== CSV 리포트 생성 =====
 * --- [ConcreteReportGenerator_v2] 리포트 생성 시작 (유형: CSV) ---
 * [AbstractReportGenerator] 데이터 전처리 중...
 * [전처리] 데이터 정렬 중...
 * [전처리] 이상치 제거 중...
 * [ConcreteReportGenerator_v3_OptionB] 'CSV' 유형 리포트 렌더링 중 (Creator: CsvRendererCreator)
 * [CsvRendererCreator] CsvReportRenderer 생성 중...
 * [CSV Report] 헤더 작성
 * [CSV Report] 바디 작성 with 5개 항목
 * [AbstractReportGenerator] 생성 완료 알림 전송 중...
 * [Notifier] 2개의 옵서버에게 알림 전송 중 (타입: CSV, 크기: 5)...
 * [Logger] 보고서 생성됨 - 타입: CSV, 데이터 크기: 5
 * [Email] mk.jang@twolinecloud.com에게 메시지 전송: CSV 보고서가 성공적으로 생성되었습니다. (데이터 항목 수: 5)
 * --- [ConcreteReportGenerator_v2] 리포트 생성 완료 (유형: CSV) ---
 */
public class ReportMain {
    public static void main(String[] args) {

        // 1. 공통 의존성 객체들 생성
        DataPreprocessor preprocessor = new DataPreprocessor();
        ReportSubject eventNotifier = new DefaultReportEventNotifier();

        // 2. 옵저버(리스너) 생성 및 ReportSubject에 등록
        ReportObserver logger = new ReportEventLogger();
        ReportObserver emailer = new EmailNotificationListener("mk.jang@twolinecloud.com");
        eventNotifier.attach(logger);
        eventNotifier.attach(emailer);

        // 3. 리포트 생성기(템플릿 메소드 구현체) 생성 및 의존성 주입
        RendererCreator csvCreator = new CsvRendererCreator();
        AbstractReportGenerator reportGenerator = new ConcreteReportGenerator_v2(preprocessor, eventNotifier, csvCreator);

        System.out.println("===== CSV 리포트 생성 =====");
        reportGenerator.generateReport("CSV", new ArrayList<>(List.of("가", "나", "다", "라", "마")));

    }
}
