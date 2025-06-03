"""
- 접근 방법 브레인 스토밍
 - 1. 시간을 분으로 바꿔서 나열할까?
 - 2. 대실 종료시간에 처음부터 +10(분)을 해서 데이터의 원자성을 확보
 - 3. 우선순위 힙 사용 (java : PriorityQueue와 유사함)
"""
import heapq

def solution(book_time):
    # 시간을 분 단위로 변환해 (start, end+10m) 리스트 생성
    intervals = []
    for start_s, end_s in book_time:
        sh, sm = map(int, start_s.split(':'))
        eh, em = map(int, end_s.split(':'))
        start = sh * 60 + sm
        end = eh * 60 + em + 10  # 청소시간 +10m
        intervals.append((start, end))
        print(start, '~' ,end)

    # 시작 시간 기준 오름차순 정렬
    intervals.sort(key=lambda x: x[0])

    # 우선순위 힙 사용해 방 별 '다음 사용 가능 시각'을 관리
    heap = []
    max_rooms = 0

    for start, end in intervals:
        # 이미 청소가 끝나서 사용 가능한 방이 있으면 pop
        if heap and heap[0] <= start:
            heapq.heappop(heap)
        # (새로 쓰거나 재사용하든) 방 하나를 이 예약에 할당
        heapq.heappush(heap, end)
        # 현재 사용 중인 방 개수를 갱신
        now_rooms = len(heap)
        max_rooms = max(max_rooms, now_rooms)

    return max_rooms


if __name__ == '__main__':
    book_time = [["15:00", "17:00"], ["16:40", "18:20"], ["14:20", "15:20"], ["14:10", "19:20"], ["18:20", "21:20"]]
    print(solution(book_time))
