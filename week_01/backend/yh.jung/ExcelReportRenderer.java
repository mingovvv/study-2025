import java.util.List;

public class ExcelReportRenderer implements ReportRender {

    @Override
    public void render(List<String> data) {
        System.out.println("[Excel Report] 헤더 작성");
        System.out.println("[Excel Report] 바디 작성 with " + data.size() + "개 항목");
    }
}