"""
- 접근 방법 브레인 스토밍
 - 1. 최단거리 이야기 나온다 ? -> BFS로 풀자
"""
from collections import deque

def solution(board):

    rows = len(board)
    cols = len(board[0])

    # 'R'(시작)와 'G'(목표) 좌표 찾기
    start = None
    goal = None
    for i in range(rows):
        for j in range(cols):
            if board[i][j] == 'R':
                start = (i, j)
            elif board[i][j] == 'G':
                goal = (i, j)

    directions = [(-1,0), (1,0), (0,-1), (0,1)]
    visited = [[False] * cols for _ in range(rows)]

    q = deque()

    # (현재행, 현재열, 이동횟수)
    q.append((start[0], start[1], 0))
    visited[start[0]][start[1]] = True

    # BFS 순회
    while q:
        row, col, distance = q.popleft()

        # 목표에 도달하면 이동횟수 반환
        if (row, col) == goal:
            return distance

        # 상,하,좌,우 방향으로 이동 시도
        for dr, dc in directions:
            nr, nc = row, col
            # 장애물이나 경계에 부딪힐 때까지 계속 한 칸씩 전진
            while True:
                rr = nr + dr
                cc = nc + dc
                # 범위를 벗어나거나 다음 칸에 'D'가 있으면 그만
                if not (0 <= rr < rows and 0 <= cc < cols) or board[rr][cc] == 'D':
                    break

                nr = rr
                nc = cc

            # 이동 후 도착 위치가 원래 위치와 같으면 (즉, 바로 앞이 막혀서 한 칸도 못 가면) 무시
            if nr == row and nc == col:
                continue

            # 새로운 지점이라면 큐에 넣고 방문 표시
            if not visited[nr][nc]:
                visited[nr][nc] = True
                q.append((nr, nc, distance+1))

    # BFS 종료 시에도 목표에 닿지 못하면 -1
    return -1

if __name__ == '__main__':
    board = ["...D..R", ".D.G...", "....D.D", "D....D.", "..D...."]
    print(solution(board))
