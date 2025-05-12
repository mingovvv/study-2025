package com.test.week01.observer;

/**
 * [옵서버 패턴]: 옵서버 인터페이스 (Observer Interface)
 *
 * 주체(Subject)의 상태 변화를 통지받아야 하는 객체들의 인터페이스를 정의.
 *
 * 옵서버 패턴의 이점:
 * - 느슨한 결합 (Loose Coupling): 주체(ReportGenerator)는 구체적인 옵서버가 아닌 옵서버 인터페이스에만 의존. 구체적인 옵서버는 주체를 변경하지 않고도 추가하거나 제거할 수 있습니다.
 * - 동적인 관계: 옵서버는 런타임에 등록하거나 해제할 수 있음.
 *
 * SOLID 원칙과의 연관성:
 *  - 개방/폐쇄 원칙 (OCP): 새로운 옵서버(알림 방식)를 추가할 때 주체의 코드를 수정할 필요가 없음. 시스템은 확장(새로운 옵서버)에는 열려 있고, 수정(주체 코드)에는 닫혀 있음.
 *  - 의존관계 역전 원칙 (DIP): 주체는 구체적인 구현이 아닌 추상화(ReportObserver 인터페이스)에 의존함.
 *
 */
public interface ReportObserver {

    /**
     * 주체가 리포트 생성 이벤트를 옵서버에게 알리기 위해 호출하는 메소드
     */
    void update(String reportType, int dataSize);

}
