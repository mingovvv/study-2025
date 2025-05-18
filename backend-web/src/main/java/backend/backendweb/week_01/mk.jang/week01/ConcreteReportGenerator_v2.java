package backend.backendweb.week_01.mk.jang.week01;

import com.test.week01.factory_v2.RendererCreator;
import com.test.week01.observer.ReportSubject;
import com.test.week01.strategy.ReportRendererStrategy;
import com.test.week01.template.AbstractReportGenerator;

import java.util.List;

/**
 * AbstractReportGenerator 를 상속받고, 외부에서 주입된 RendererCreator (추상 팩토리)를 사용하여
 * 구체적인 전략을 생성하는 구체적인 리포트 생성기입니다.
 */
public class ConcreteReportGenerator_v2 extends AbstractReportGenerator {

    private final RendererCreator rendererCreator;

    public ConcreteReportGenerator_v2(DataPreprocessor preprocessor, ReportSubject eventNotifier, RendererCreator rendererCreator) {
        super(preprocessor, eventNotifier);
        this.rendererCreator = rendererCreator;
    }

    @Override
    protected void renderReport(String reportType, List<String> data) {
        System.out.println("[ConcreteReportGenerator_v3_OptionB] '" + reportType + "' 유형 리포트 렌더링 중 (Creator: " + rendererCreator.getClass().getSimpleName() + ")");

        ReportRendererStrategy renderer = rendererCreator.createRenderer();
        renderer.render(data);
    }
}
