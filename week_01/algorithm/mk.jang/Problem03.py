from collections import deque

def execute_forklift(grid, target):
    n, m = len(grid), len(grid[0])
    visited = [[False] * m for _ in range(n)]
    q = deque()

    # 상하좌우
    dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    # 외곽에 위치한 '.'을 전부 시작점으로 큐에 넣음
    for i in range(n):
        for j in range(m):
            if (i == 0 or i == n-1 or j == 0 or j == m-1) and grid[i][j] == '.':
                q.append((i, j))
                visited[i][j] = True

    # .. todo

def execute_crane(grid, target):
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] == target:
                grid[i][j] = '.'

def pad_storage(storage):
    n, m = len(storage), len(storage[0])
    padded = [['.'] * (m + 2)]  # top padding
    for row in storage:
        padded.append(['.'] + row + ['.'])  # left/right padding
    padded.append(['.'] * (m + 2))  # bottom padding
    return padded

def solution(storage, requests):
    storage_ = [list(row) for row in storage]
    storage_ = pad_storage(storage_)

    for request in requests:
        if len(request) == 2:
            execute_crane(storage_, request[0])
        else:
            execute_forklift(storage_, request[0])

    answer = sum(cell != '.' for row in storage_ for cell in row)
    return answer


if __name__ == '__main__':
    storage = ["AZWQY", "CAABX", "BBDDA", "ACACA"]
    requests = ["A", "BB", "A"]
    print(solution(storage, requests))