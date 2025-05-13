"""
[range]
 - iterable 객체
 - range(5) -> range(0, 5)
 - 반복 가능한 숫자 시퀀스를 생성, 인덱스를 순회하기 위한 전형적인 패턴

[from fractions import Fraction]
 - 분수를 정확하게 다룰 수 있도록 해주는 파이썬 클래스
"""
def solution(weights):
    """
    브루트포스 방식 -> 이중 for문으로 시간복잡도 O(n^2) 방식... 너무 느려서 탈락
    """
    pair_count = 0

    for i in range(len(weights)):
        for j in range(i+1, len(weights)):
            if weights[i] == weights[j]:
                pair_count += 1
            elif weights[i] * 2 == weights[j] * 3:
                pair_count += 1
            elif weights[i] * 3 == weights[j] * 2:
                pair_count += 1
            elif weights[i] * 2 == weights[j] * 4:
                pair_count += 1
            elif weights[i] * 4 == weights[j] * 2:
                pair_count += 1
            elif weights[i] * 4 == weights[j] * 3:
                pair_count += 1
            elif weights[i] * 3 == weights[j] * 4:
                pair_count += 1

    return pair_count

from collections import Counter
from fractions import Fraction

def solution2(weights):

    # 요소와 요소의 갯수를 저장할 dictionary
    count = Counter()
    pair_count = 0

    # 비율 집합
    ratios = [Fraction(1, 1), Fraction(2, 3), Fraction(3, 2), Fraction(2, 4), Fraction(4, 2), Fraction(3, 4), Fraction(4, 3)]

    for w in weights:
        for r in ratios:
            target_weight = w * r  # 짝이 되려면 필요한 과거 weight

            # 아직 현재 인덱스에 대한 값은 count 객체에 포함되어 있지 않으므로 과거 요소 갯수만 고려하기
            pair_count += count.get(target_weight, 0) # 과거 요소들에 대한 갯수 꺼내오기
        count[w] += 1  # 현재 weight count 객체에 저장해두기

    return pair_count

if __name__ == '__main__':
    weights = [100,180,360,100,270]

    print(solution2(weights))