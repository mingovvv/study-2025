package backend.backendweb.week_01.yh.jung;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ReportRenderFactory {
    private static final Map<String, Class<? extends ReportRender>> map = new HashMap<>();

    static {
        map.put("CSV", CsvReportRenderer.class);
        map.put("PDF", PdfReportRenderer.class);
        map.put("EXCEL", ExcelReportRenderer.class);
    }

    public static ReportRender getReportRender(String type) {
        if ("CSV".equalsIgnoreCase(type)) {
            return new CsvReportRenderer();
        } else if ("PDF".equalsIgnoreCase(type)) {
            return new PdfReportRenderer();
        } else if ("EXCEL".equalsIgnoreCase(type)) {
            return new ExcelReportRenderer();
        } else {
            throw new IllegalArgumentException("지원하지 않는 타입: " + type);
        }
    }

    public static ReportRender getReportRender2(String type) {
        Class<? extends ReportRender> clazz = map.get(type);
        if(clazz == null) {
            throw new IllegalArgumentException("지원하지 않는 타입: " + type);
        }

        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("인스턴스를 생성할 없음 : " + type, e);
        }

    }
}
