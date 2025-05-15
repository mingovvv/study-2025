"""
- 접근 방법 브레인 스토밍
 - 1. plans 각 요소들의 두번째 요소(start)를 분으로 바꾸고 오름차순으로 정렬한다. ex) 11:40 -> 700
 - 2. loop을 돌면서 현재 요소의 시작시간을 now(현재시간)에 세팅한다.
 - 3. 다음 요소의 시작시간 - (현재 요소의 시작시간 + 현재 요소의 소요시간) > 0  -> 큐에서 꺼내서 공부한다.
 - 4. 다음 요소의 시작시간 - (현재 요소의 시작시간 + 현재 요소의 소요시간) = 0  -> 다음 요소로 넘어간다.
 - 5. 다음 요소의 시작시간 - (현재 요소의 시작시간 + 현재 요소의 소요시간) < 0  -> 큐에 현재 요소의 남은시간을 넣고 다음 요소로 넘어간다.
 - 6. loop을 돌면서 공부가 완성된 요소는 결과 리스트에 담는다.

"""


def str_time_to_minutes(str_time: str):
    hour, minute = map(int, str_time.split(":"))
    return hour * 60 + minute


def solution(plans):

    # 시간 분 단위 변환
    for p in plans:
        p[1] = str_time_to_minutes(p[1])

    # 분 오름차순 정렬
    plans.sort(key=lambda x: x[1])

    answer = []
    remain = []  # (subject, 남은 시간) 스택

    for i, (subject, start, duration) in enumerate(plans):

        # 문자열인 소요시간을 넘버타입으로 변환
        duration = int(duration)

        # 다음 요소의 시작시간 계산
        next_start = plans[i + 1][1] if i + 1 < len(plans) else None

        # 현재 요소의 종료시간 계산
        finish_time = start + duration

        # 다음 요소가 없는 경우
        if next_start is None:
            answer.append(subject)

        # 여유 시간이 있는 경우
        elif next_start > finish_time:
            answer.append(subject)

            # 다음 과제를 할 수 있는 여유 시간 계산
            gap = (next_start - finish_time)

            # 여유 시간이 있다면 계속에서 미뤄둔 과제를 소비한다.
            while remain and gap > 0:
                prev_subject, prev_duration = remain.pop()
                if gap >= prev_duration:
                    answer.append(prev_subject) # 과제 완료
                    gap -= prev_duration
                else:
                    remain.append((prev_subject, prev_duration - gap)) # 과제 진행중에 다음 과제 시작시간이 되어 현재 요소의 과제는 미완료로 다시 리스트에 넣어둔다.
                    gap = 0
        elif next_start == finish_time:
            answer.append(subject)
        else:
            # 과제 진행중에 다음 과제 시작시간이 되어 현재 요소의 과제는 미완료로 다시 리스트에 넣어둔다.
            remain.append((subject, finish_time - next_start))

    # 모든 과제 순회 후, 리스트에 남은 과제들 마저 완료
    while remain:
        answer.append(remain.pop()[0])

    return answer


if __name__ == '__main__':
    plans = [["korean", "11:40", "30"], ["english", "12:10", "20"], ["math", "12:30", "40"]]
    print(solution(plans))
