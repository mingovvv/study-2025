package backend.backendweb.week_01.mk.jang.week01.factory_v2.concrete;

import backend.backendweb.week_01.mk.jang.week01.factory_v2.RendererCreator;
import backend.backendweb.week_01.mk.jang.week01.strategy.ReportRendererStrategy;
import backend.backendweb.week_01.mk.jang.week01.strategy.concrete.PdfReportRenderer;

/**
 * [팩토리 메소드 패턴]: 구체적인 생성자 클래스 (ConcreteCreator)
 *
 * PdfReportRenderer 전략 객체를 생성하는 RendererCreator 인터페이스의 구체적인 구현체
 *
 */
public class PdfRendererCreator extends RendererCreator {

    @Override
    public ReportRendererStrategy createRenderer() {
        System.out.println("[PdfRendererCreator] PdfReportRenderer 생성 중...");
        return new PdfReportRenderer();
    }

}
