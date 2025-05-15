"""
- 접근 방법 브레인 스토밍
 - m명이 늘어날 때마다 서버 1대가 추가됨 (1대 * 3명 ~ 2대 * 3명 = 3명 ~ 6명미만, 3~5명까지는 한 대로 가능)
 - 한번 늘어난 서버는 k시간 동안 운영되고 그 이후 반납함
 - 1. 리스트를 순회하면서 플레이어의 수와 서버 허용치(m + 증설된 서버 수)를 나누어 그 몫의 값 만큼 서버를 증설한다. (증설된 서버 수 관리해야 함)
 - 2. 증설한 시간 + k시간 = 반납시간을 튜플로 관리한다. (반납시간, 수)
 - 3. 순회하면서 반납시간이 되면 증설된 서버 수를 감소시킨다.
 - 4. 전체 순회가 끝나면 증설된 서버 수를 반환한다.

시간 복잡도 O(n)

"""


def solution(players, m, k):

    # 서버 증설 횟수
    additional_server_cnt = 0
    # (반납시간, 서버 수) 리스트
    server_expiry = []

    for time, user_cnt in enumerate(players):

        # 만료된 서버는 리스트에서 제거
        server_expiry = [(expiry_time, cnt) for expiry_time, cnt in server_expiry if expiry_time > time]

        current_server_cnt = 0
        for expiry, cnt in server_expiry:
            current_server_cnt += cnt

        # 필요한 서버 수 계산(몫)
        required_server_cnt = user_cnt // m

        # 3. 서버 증설이 필요한 경우 추가
        if required_server_cnt > current_server_cnt:
            additional_servers = required_server_cnt - current_server_cnt
            additional_server_cnt += additional_servers
            server_expiry.append((time + k, additional_servers))

    return additional_server_cnt


if __name__ == '__main__':
    players = [0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5]
    m = 3
    k = 5
    print(solution(players, m, k))