package com.test.week01.strategy.concrete;

import com.test.week01.strategy.ReportRendererStrategy;

import java.util.List;

/**
 * [전략 패턴]: 구체적인 전략 (Concrete Strategy)
 * - 실제 사용되는 알고리즘 정의
 */
public class PdfReportRenderer implements ReportRendererStrategy {

    @Override
    public void render(List<String> data) {
        System.out.println("[PDF Report] 헤더 작성");
        System.out.println("[PDF Report] 바디 작성 with " + data.size() + "개 항목");
    }

}
