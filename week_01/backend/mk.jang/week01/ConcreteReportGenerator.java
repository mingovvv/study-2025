package com.test.week01;

import com.test.week01.factory.ReportRendererFactory;
import com.test.week01.observer.ReportSubject;
import com.test.week01.strategy.ReportRendererStrategy;
import com.test.week01.template.AbstractReportGenerator;

import java.util.List;

/**
 * 실제 리포트 생성 로직을 구체화하는 클래스
 */
public class ConcreteReportGenerator extends AbstractReportGenerator {

    private final ReportRendererFactory rendererFactory;

    /**
     * ConcreteReportGenerator의 생성자
     */
    public ConcreteReportGenerator(DataPreprocessor preprocessor, ReportSubject eventNotifier, ReportRendererFactory rendererFactory) {
        super(preprocessor, eventNotifier); // 부모 생성자 호출
        this.rendererFactory = rendererFactory;
    }

    @Override
    protected void renderReport(String reportType, List<String> data) {
        System.out.println("[ConcreteReportGenerator] '" + reportType + "' 유형 리포트 렌더링 중...");
        // 팩토리를 사용하여 적절한 렌더러(전략)를 가져옴
        ReportRendererStrategy renderer = rendererFactory.createRenderer(reportType);
        // 선택된 렌더러(전략)를 사용하여 렌더링 수행
        renderer.render(data);
    }
}
