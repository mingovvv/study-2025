package backend.backendweb.week_01.yh.jung;

import java.util.List;

public class PdfReportRenderer implements ReportRender {

    @Override
    public void render(List<String> data) {
        System.out.println("[PDF Report] 헤더 작성");
        System.out.println("[PDF Report] 바디 작성 with " + data.size() + "개 항목");
    }
}