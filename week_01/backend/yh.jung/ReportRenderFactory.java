public class ReportRenderFactory {
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
}
