package backend.backendweb.week_02.yh.jung;

import java.io.Serializable;

public class Singleton03 implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Singleton03 instance;
    /*
    * 싱글턴 인스턴스의 직렬화 결과에는 아무런 실 데이터를 가질 이유가 없기 때문에 인스턴스 필드를 transient로 선언(transient : 직렬화 제외)
    * readResolve 메서드라도 역직렬화 과정 중간에 역직렬화된 인스턴스의 참조를 훔쳐오는 공격을 행할경우 다른 객체로 바뀔 위험
    */
    transient long currentId = 0L;

    private Singleton03() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGenerator 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static Singleton03 getInstance() {
        if (instance == null) {
            instance = new Singleton03();
        }
        return instance;
    }

    public long getNextId() {
        return ++currentId;
    }

    /*
    * 직렬화 관련 메서드인 readResolve() 를 정의
    * readResolve 메서드를 정의하게 되면, 역직렬화 과정에서 readObject를 통해 만들어진 인스턴스 대신 readResolve에서 반환되는 인스턴스를 내가 원하는 것으로 바꿀 수 있기 때문
    */
    private Object readResolve() {
        return instance;
    }

}
