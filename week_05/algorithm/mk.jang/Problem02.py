"""
- 접근 방법 브레인 스토밍
 - 1. 무적권 사용은 죽기 전 enemy[i]가 가장 클 때
 - 2. 우선순위힙을 사용해서 가장 enemy[i]가 적을 때는 제외 해야함
"""
import heapq

def solution(n, k, enemy):

    # 현재까지 본 적 중 무적권을 쓴 k개의 가장 큰 적 수를 담는 최소 힙
    min_heap = []
    # 힙에 담긴 무적권 처리된 적 수의 합
    sum_k = 0
    # 지금까지 합산된 모든 적 수의 합
    total = 0

    for i, e in enumerate(enemy):

        total += e
        # 일단 이번 라운드 적 수 e를 힙에 넣고, sum_k에 더한다
        heapq.heappush(min_heap, e)
        sum_k += e

        # 힙의 크기가 k보다 크면, 가장 작은 값을 빼내서 sum_k에서 제외
        if len(min_heap) > k:
            smallest = heapq.heappop(min_heap)
            sum_k -= smallest

        # 실제 병사로 막아야 할 총합 = total - sum_k
        used = total - sum_k
        if used > n:
            return i

    return len(enemy)


if __name__ == '__main__':
    n = 7
    k = 3
    enemy = [4, 2, 4, 5, 3, 3, 1]
    print(solution(n, k, enemy))
