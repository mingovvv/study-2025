## week 01 백엔드 과제 문제

---
## 목표 : ReportGenerator 클래스와 연관된 다른 클래스를 다양한 디자인패턴으로 리팩토링하자.

### ReportGenerator.java
```java
package com.study.week01.report.asis;

import java.util.List;

public class ReportGenerator {

    private final DataPreprocessor preprocessor = new DataPreprocessor();
    private final EmailSender emailSender = new EmailSender();
    private final ReportLogger logger = new ReportLogger();

    public void generate(String type, List<String> data) {
        // 전처리
        preprocessor.preprocess(data);

        // 본 처리
        if ("CSV".equalsIgnoreCase(type)) {
            new CsvReportRenderer().render(data);
        } else if ("PDF".equalsIgnoreCase(type)) {
            new PdfReportRenderer().render(data);
        } else if ("EXCEL".equalsIgnoreCase(type)) {
            new ExcelReportRenderer().render(data);
        } else {
            throw new IllegalArgumentException("지원하지 않는 타입: " + type);
        }

        // 후처리
        logger.log(type, data.size());
        emailSender.send("admin@example.com", type + " 보고서가 생성되었습니다.");
    }
}
```

### DataPreprocessor.java
```java 
package com.study.week01.report.asis;

import java.util.List;

public class DataPreprocessor {
    public void preprocess(List<String> data) {
        System.out.println("[전처리] 데이터 정렬 중...");
        data.sort(String::compareTo);

        System.out.println("[전처리] 이상치 제거 중...");
        data.removeIf(d -> d.contains("N/A"));
    }
}
```

### CsvReportRenderer.java
```java
package com.study.week01.report.asis;

import java.util.List;

public class CsvReportRenderer {
    public void render(List<String> data) {
        System.out.println("[CSV Report] 헤더 작성");
        System.out.println("[CSV Report] 바디 작성 with " + data.size() + "개 항목");
    }
}
```

### PdfReportRenderer.java
```java
package com.study.week01.report.asis;

import java.util.List;

public class PdfReportRenderer {
    public void render(List<String> data) {
        System.out.println("[PDF Report] 헤더 작성");
        System.out.println("[PDF Report] 바디 작성 with " + data.size() + "개 항목");
    }
}
```

### ExcelReportRenderer.java
```java
package com.study.week01.report.asis;

import java.util.List;

public class ExcelReportRenderer {
    public void render(List<String> data) {
        System.out.println("[Excel Report] 헤더 작성");
        System.out.println("[Excel Report] 바디 작성 with " + data.size() + "개 항목");
    }
}
```

### EmailSender.java
```java
package com.study.week01.report.asis;

public class EmailSender {
    public void send(String to, String message) {
        System.out.println("[Email] " + to + "에게 메시지 전송: " + message);
    }
}
```