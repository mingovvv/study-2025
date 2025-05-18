package backend.backendweb.week_01.yh.jung;

import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

    private static final DataPreprocessor preprocessor = new DataPreprocessor();
    private static final EmailSender emailSender = new EmailSender();
    private static final ReportLogger logger = new ReportLogger();

    public static void main(String[] args) {
        String type = "CSV";

        List<String> data = new ArrayList<>();
        for(int i=0; i<10; i++) {
            data.add(String.valueOf(i));
        }

        generate(type, data);
    }

    public static void generate(String type, List<String> data) {
        // 전처리
        preprocessor.preprocess(data);

        // 본 처리
        ReportRender reportRender = ReportRenderFactory.getReportRender(type);
        reportRender.render(data);

        // 후처리
        logger.log(type, data.size());
        emailSender.send("admin@example.com", type + " 보고서가 생성되었습니다.");

        System.out.println("----------------------------------------------");

        type = "PDF";

        // 전처리
        preprocessor.preprocess(data);

        // 본 처리
        ReportRender reportRender2 = ReportRenderFactory.getReportRender2(type);
        reportRender2.render(data);

        // 후처리
        logger.log(type, data.size());
        emailSender.send("admin@example.com", type + " 보고서가 생성되었습니다.");

    }

}