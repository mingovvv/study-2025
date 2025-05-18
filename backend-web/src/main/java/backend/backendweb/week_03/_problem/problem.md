## week 03 백엔드 과제 문제

---

## 목표 : MapStruct를 활용한 DTO 매핑 생산성 체감하기

## 시나리오 :

- 연호는 새로운 프로젝트에서 사용자(User)와 팀(Team) 정보를 관리하는 기능을 개발하고 있습니다.
- 엔티티 설계 후, API 요청(Request)과 응답(Response)을 위한 DTO 클래스들을 정의했습니다. 초기 단계에서는 각 DTO 클래스 내부에 static of 또는 toEntity/fromEntity와
  같은 메소드를 만들어 수동으로 엔티티와 DTO 간의 데이터 변환을 처리했습니다.
- 하지만 프로젝트가 점차 확장되고 엔티티와 DTO의 필드가 다양해지면서, 다음과 같은 문제점들이 발생하기 시작했습니다.
    - 반복적인 매핑 코드: 유사한 구조의 매핑 코드가 여러 DTO에 중복되어 작성되었습니다.
    - 잦은 실수 유발: 필드 이름이 변경되거나 새로운 필드가 추가될 때, 관련된 모든 매핑 로직을 수동으로 수정해야 했고, 이 과정에서 누락이나 오타로 인한 버그가 발생하기 쉬웠습니다.
    - 유지보수의 어려움: 매핑 로직 변경 시 여러 파일을 수정해야 하는 번거로움이 있었습니다.
- 그로인해 MapStruct 라이브러리 도입을 위하여 직접 장/단점을 느껴보고자 합니다.

## 제공되는 코드 구조 :

과제 수행에 필요한 기본 코드 구조는 다음과 같습니다. (세부 코드는 별도 파일로 제공됩니다.)
내부적으로 H2 DB를 인메모리 방식으로 사용하고 있으며 초기화 데이터가 존재합니다. (data.sql 참고)

- entity 패키지:
    - User.java
    - Team.java
- enums 패키지:
    - UserRole.java
- dto 패키지:
    - request 패키지:
        - UserCreateRequest.java (내부에 public static User toEntity(...))
        - TeamCreateRequest.java (내부에 public static Team toEntity(...))
    - response 패키지:
        - UserResponse.java (내부에 public static UserResponse fromEntity(...))
        - TeamResponse.java (내부에 public static TeamResponse fromEntity(...))
        - UserSimpleResponse.java (내부에 public static UserSimpleResponse fromEntity(...))
- repository 패키지:
    - UserRepository.java
    - TeamRepository.java
- service 패키지 (메소드 시그니처만 제공):
    - UserService.java
    - TeamService.java

## 과제
1. 1단계: 전통적인 방식의 수동 매핑 구현
    - DTO 내부 매핑 메소드 완성:
    - 서비스 레이어 매핑 로직 구현
2. 2단계: MapStruct를 사용한 매핑 구현
    - MapStruct 매퍼 인터페이스 작성
    - 서비스 레이어 매핑 로직 구현(ver2)
3. 스스로 고찰 및 비교 분석