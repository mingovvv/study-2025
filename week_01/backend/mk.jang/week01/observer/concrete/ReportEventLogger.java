package com.test.week01.observer.concrete;

import com.test.week01.observer.ReportObserver;

/**
 * [옵서버 패턴]: 구체적인 옵서버 (Concrete Observer)
 *
 */
public class ReportEventLogger implements ReportObserver {

    /**
     * 주체가 리포트 생성 이벤트를 기록하기 위해 호출하는 메소드.
     */
    @Override
    public void update(String reportType, int dataSize) {
        System.out.println("[Logger] 보고서 생성됨 - 타입: " + reportType + ", 데이터 크기: " + dataSize);
    }

}
