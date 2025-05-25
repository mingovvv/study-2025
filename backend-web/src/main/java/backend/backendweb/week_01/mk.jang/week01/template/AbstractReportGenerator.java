package backend.backendweb.week_01.mk.jang.week01.template;

import backend.backendweb.week_01.mk.jang.week01.DataPreprocessor;
import backend.backendweb.week_01.mk.jang.week01.observer.ReportSubject;

import java.util.List;

/**
 * [템플릿 메소드 패턴]: 추상 클래스 (Abstract Class)
 *
 * 리포트 생성 알고리즘의 골격을 정의하는 추상 클래스
 * 리포트 생성의 '전처리 -> 본처리(렌더링) -> 후처리(알림)'의 단계를 추상화 함.
 *
 * 이점:
 *  - 리포트 생성의 전체적인 흐름을 일관되게 유지하면서 특정 단계를 유연하게 변경할 수 있음
 *  - SRP(단일 책임 원칙) 개선: 이 클래스는 리포트 생성 '흐름' 관리에 집중하고, 실제 알림 로직은 외부의 에 위임함
 *
 */
public abstract class AbstractReportGenerator {

    protected final DataPreprocessor preprocessor;
    protected final ReportSubject eventNotifier;

    public AbstractReportGenerator(DataPreprocessor preprocessor, ReportSubject eventNotifier) {
        this.preprocessor = preprocessor;
        this.eventNotifier = eventNotifier;
    }

    /**
     * 리포트 생성 프로세스의 템플릿 메소드입니다.
     * 전처리, 본처리(렌더링), 후처리(알림)의 고정된 순서로 작업을 수행합니다.
     *
     */
    public final void generateReport(String reportType, List<String> data) {

        System.out.println("--- [" + getClass().getSimpleName() + "] 리포트 생성 시작 (유형: " + reportType + ") ---");

        // 1. 전처리 단계 (공통 로직)
        preprocessData(data);

        // 2. 본처리 단계 (렌더링 - 구체적인 방식은 하위 클래스 또는 전략에 의해 결정)
        renderReport(reportType, data);

        // 3. 후처리 단계 (알림 - ReportSubject에 위임)
        notifyGenerationCompletion(reportType, data.size());

        System.out.println("--- [" + getClass().getSimpleName() + "] 리포트 생성 완료 (유형: " + reportType + ") ---");
    }

    /**
     * 데이터 전처리 단계를 수행.
     */
    protected void preprocessData(List<String> data) {
        System.out.println("[AbstractReportGenerator] 데이터 전처리 중...");
        preprocessor.preprocess(data); // DataPreprocessor에 위임
    }

    /**
     * 실제 리포트 렌더링을 수행하는 추상 메소드 또는 구체적인 렌더링 로직을 호출하는 메소드.
     * 이 메소드는 하위 클래스에서 구현되거나, 전략 패턴을 통해 렌더러 객체에 위임됨.
     *
     */
    protected abstract void renderReport(String reportType, List<String> data);

    /**
     * 리포트 생성 완료 후 알림 단계를 수행.
     */
    protected void notifyGenerationCompletion(String reportType, int dataSize) {
        System.out.println("[AbstractReportGenerator] 생성 완료 알림 전송 중...");
        eventNotifier.notifyObservers(reportType, dataSize); // ReportSubject에 위임
    }

}
