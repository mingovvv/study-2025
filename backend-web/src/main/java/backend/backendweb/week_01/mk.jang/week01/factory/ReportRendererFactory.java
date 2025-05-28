package backend.backendweb.week_01.mk.jang.week01.factory;

import backend.backendweb.week_01.mk.jang.week01.strategy.ReportRendererStrategy;
import backend.backendweb.week_01.mk.jang.week01.strategy.concrete.CsvReportRenderer;
import backend.backendweb.week_01.mk.jang.week01.strategy.concrete.ExcelReportRenderer;
import backend.backendweb.week_01.mk.jang.week01.strategy.concrete.PdfReportRenderer;

/**
 * [팩토리 메소드 패턴]: 구체적인 팩토리 (Concrete Factory)
 *
 * ReportRendererStrategy의 인스턴스를 생성하는 책임
 * 인스턴스화 로직을 캡슐화하여 클라이언트 코드(ReportGenerator)가 구체적인 전략 클래스로부터 독립되도록 함
 */
public class ReportRendererFactory {

    /**
     * 지정된 리포트 유형에 따라 전략 인스턴스를 생성하고 반환
     */
    public ReportRendererStrategy createRenderer(String type) {

        if ("CSV".equalsIgnoreCase(type)) {
            return new CsvReportRenderer();
        } else if ("PDF".equalsIgnoreCase(type)) {
            return new PdfReportRenderer();
        } else if ("EXCEL".equalsIgnoreCase(type)) {
            return new ExcelReportRenderer();
        } else {
            throw new IllegalArgumentException("지원하지 않는 리포트 유형입니다: " + type);
        }

    }

}
