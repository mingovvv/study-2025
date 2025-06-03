## week 03 백엔드 과제 문제

---

## 목표 : 낙관적 락(Optimistic Locking) 과 비관적 락(Pessimistic Locking) 을 적용해 보고,간단한 “좌석 예약(Seat Reservation)” API를 통해 동시성 문제를 직접 체험해 보자.

## 시나리오 :

 - 온라인 공연 예매 시스템이 있다. 공연마다 한정된 좌석이 있으며, 여러 사용자가 “한 좌석(Seat)을 예약(Purchase)”하기 위해 동시 요청을 보낸다고 가정하자.
 - 좌석이 한 번 팔리면 다시 팔 수 없으므로, available 필드를 false로 변경해야 한다.
 - 락이 없는 상태에서는 여러 쓰레드가 동시에 동일한 좌석을 “조회→예약”하여 이중 판매 현상이 발생할 수 있다.
 - 이를 방지하기 위해 낙관적 락과 비관적 락 두 가지 방식을 도입하고, 각각의 동작과 장단점을 비교·검증해야 한다.

## 제공되는 코드 구조 :

과제 수행에 필요한 기본 코드 구조는 다음과 같다.

- entity 패키지:
    - Seat.java
- repository 패키지:
    - SeatRepository.java
- service 패키지:
    - ReservationService.java
- controller 패키지:
    - ReservationController.java

## 과제
1. 동시성(Concurrency) 문제를 해결하기 위해 락을 도입해보자.
2. 테스트코드 ReservationControllerConcurrencyTest.java를 통과해야만 한다.


#### 자신의 dto, service 패키지의 클래스를 이용하고 추가로 MapStruct mapper를 위한 클래스를 별도로 만들 것.
