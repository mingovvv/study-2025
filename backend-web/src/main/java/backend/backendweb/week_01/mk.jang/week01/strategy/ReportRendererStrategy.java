package backend.backendweb.week_01.mk.jang.week01.strategy;

import java.util.List;

/**
 * [전략 패턴]: 전략 인터페이스 (Strategy Interface)
 *
 * 지원되는 모든 리포트 렌더링 알고리즘에 대한 공통 인터페이스를 정의
 * 이를 통해 렌더링 알고리즘을 사용하는 클라이언트(ReportGenerator)에게 동적으로 알고리즘을 변경할 수 있도록 함
 *
 * 전략 패턴의 이점:
 *  - 유연성: 런타임 또는 설정 시에 렌더링 알고리즘을 변경할 수 있음.
 *  - 확장성: 이 인터페이스를 구현하여 새로운 렌더링 전략을 쉽게 추가할 수 있음
 *
 * SOLID 원칙과의 연관성:
 *  - 개방/폐쇄 원칙 (OCP): 컨텍스트(ReportGenerator)나 기존 전략을 수정하지 않고 새로운 렌더링 전략을 도입할 수 있음. 시스템은 확장(새로운 렌더러)에는 열려 있고, 수정에는 닫혀 있음.
 *  - 의존관계 역전 원칙 (DIP): ReportGenerator는 구체적인 렌더러 구현이 아닌 이 추상화(ReportRenderer)에 의존하게 됨.
 *
 */
public interface ReportRendererStrategy {

    /**
     * 제공된 데이터를 기반으로 리포트를 렌더링
     */
    void render(List<String> data);

}
