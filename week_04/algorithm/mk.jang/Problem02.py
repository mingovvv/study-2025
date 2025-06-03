"""
"""


def solution(rows, columns, queries):
    # 행렬 초기화: 1부터 rows×columns까지 가로 순서로 채움
    board = [[i * columns + j + 1 for j in range(columns)] for i in range(rows)]

    answer = []

    # 테두리 회전 수행
    for x1, y1, x2, y2 in queries:

        sx = x1 - 1
        sy = y1 - 1
        ex = x2 - 1
        ey = y2 - 1

        # 회전할 테두리에서 첫 번째 값을 임시 저장
        prev = board[sx][sy]
        print(f'prev, start value = {prev}')
        min_val = prev

        # 윗변: (sx, sy+1) 부터 (sx, ey) 까지
        for j in range(sy + 1, ey + 1):
            print(f'윗변 from ~ to = [{sy + 1} ~ {ey + 1}], j = {j}')
            curr = board[sx][j]
            print(f'curr = {curr}')
            board[sx][j] = prev
            print(f'board[sx][j] = {board[sx][j]}')
            prev = curr
            print(f'prev = {prev}')
            min_val = min(min_val, prev)

        # 오른쪽변: (sx+1, ey) 부터 (ex, ey) 까지
        for i in range(sx + 1, ex + 1):
            print(f'오른쪽변 from ~ to = [{sx + 1} ~ {ex + 1}], i = {i}')
            curr = board[i][ey]
            board[i][ey] = prev
            prev = curr
            min_val = min(min_val, prev)

        # 아랫변: (ex, ey-1) 부터 (ex, sy) 까지 (역순)
        for j in range(ey - 1, sy - 1, -1):
            print(f'아랫변 from ~ to = [{ey - 1} ~ {sy - 1}], j = {j}')
            curr = board[ex][j]
            board[ex][j] = prev
            prev = curr
            min_val = min(min_val, prev)

        # 왼쪽변: (ex-1, sy) 부터 (sx, sy) 까지 (역순)
        for i in range(ex - 1, sx - 1, -1):
            curr = board[i][sy]
            board[i][sy] = prev
            prev = curr
            min_val = min(min_val, prev)

        # 이번 회전에서 움직인 숫자들 중 최솟값 저장
        answer.append(min_val)

    return answer


if __name__ == '__main__':
    row = 6
    columns = 6
    queries = [[2, 2, 5, 4]]
    # queries = [[2, 2, 5, 4], [3, 3, 6, 6], [5, 1, 6, 3]]
    print(solution(row, columns, queries))
