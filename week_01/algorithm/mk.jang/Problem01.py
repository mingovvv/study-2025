"""
## Collections

[list]
 - 순서 O, 중복 O
 - Mutable
 - ["a", "b", "c"]

[tuple]
 - 순서 O, 중복 O
 - Immutable
 - ("a", "b", "c")

[Set]
 -  - 순서 X (Python 3.7+부터 내부적으론 유지되지만 비보장), 중복 X
 - Mutable
 - {"a", "b", "c"}

[dictionary]
 - Python 3.7+ 순서 O (삽입 순서), 키 중복 X
 - Mutable
 - {"a": 1, "b": 2}
"""

from collections import Counter

def solution(k, tangerine):

    # 귤의 크기별 갯수 구하기, dictionary
    size_counter = Counter(tangerine)
    # 각 원소의 정렬 기준 : x[1]
    sorted_sizes = sorted(size_counter.items(), key=lambda x: x[1], reverse=True)

    total = 0
    kind_count = 0

    for size, count in sorted_sizes:
        total += count
        kind_count += 1
        if total >= k:
            break

    return kind_count

if __name__ == '__main__':
    k = 6
    tangerine = [1, 3, 2, 5, 4, 5, 2, 3]

    print(solution(k, tangerine))