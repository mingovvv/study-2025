package backend.backendweb.week_01.mk.jang.week01.factory_v2;

import com.test.week01.strategy.ReportRendererStrategy;

/**
 * [팩토리 메소드 패턴]: 생성자 추상 클래스 (Creator)
 *
 * 어떤 구체적인 전략을 생성할지는 이 클래스를 상속하는 하위 클래스(ConcreteCreator)에서 결정한다.
 *
 * 이점 (엄밀한 팩토리 메소드 패턴):
 *  - 유연성 및 확장성 (OCP): 새로운 유형의 전략이 추가될 때, 이 추상 클래스나 기존 코드를 수정할 필요 없이 새로운 ConcreteCreator를 추가하여 확장할 수 있음
 *  - 결합도 감소 (DIP): 클라이언트는 이 추상 Creator와 추상 Product(ReportRendererStrategy)에만 의존하게 되어 구체적인 제품 클래스로부터 분리됨
 *  - 생성 로직 위임: 객체 생성의 책임을 하위 클래스로 위임하여, 생성 로직이 변경되거나 다양해질 때 유연하게 대처할 수 있음
 *
 * SOLID 원칙과의 연관성:
 *  - OCP (개방/폐쇄 원칙): 새로운 전략과 그 전략(렌더러)를 생성하는 팩토리를 추가할 때 기존 코드를 수정하지 않음. 확장에는 열려있고, 수정에는 닫혀있음
 *  - DIP (의존관계 역전 원칙): 클라이언트는 구체적인 팩토리나 제품이 아닌 추상화에 의존함
 *
 */
public abstract class RendererCreator {

    /**
     * 팩토리 메소드 - 인스턴스를 생성합
     * 구체적인 전략 객체를 생성할지는 이 클래스를 상속하는 하위 클래스에서 구현
     *
     */
    public abstract ReportRendererStrategy createRenderer();

}
