## week 02 백엔드 과제 문제

---
## 목표 : 안전하고 견고한 싱글턴 패턴 구현 및 검증
## 시나리오 : 
 - 연호는 대한전선 프로젝트에 요청된 스레드 수를 카운트 하고 로그에 남기기 위하여 고유한 순번 ID를 발급하는 UniqueIdGenerator 클래스를 만들려고 한다. 
   ID 생성기는 애플리케이션 실행 중에 단 하나의 인스턴스만 존재해야 하며, 여러 스레드에서 동시에 getNextId()를 호출하더라도 결코 중복된 ID를 반환해서는 안 된다. 
   연호는 이러한 요구사항을 만족시키기 위해 싱글턴 패턴을 적용시켰다. 하지만 AS-IS 코드에는 심각한 문제점이 발견되었다. AS-IS 코드에서 결함이 일어날 곳을 파악하고 `UniqueIdGenerator`를 개선해보자.
## 과제 :
1. 현재 작성된 AS-IS 코드의 결함을 찾는다.
2. `UniqueIdGenerator` 클래스를 개선한다.


### UniqueIdGenerator.java
```java
import java.io.Serializable;

public class UniqueIdGenerator implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private static UniqueIdGenerator instance;
    private long currentId = 0L;

    private UniqueIdGenerator() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGenerator 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static UniqueIdGenerator getInstance() {
        if (instance == null) {
            instance = new UniqueIdGenerator();
        }
        return instance;
    }

    public long getNextId() {
        return ++currentId;
    }
    
}
```