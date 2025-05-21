"""
접근 방법 브레인 스토밍
 - 이분탐색으로 풀어야 함
"""
import bisect
from itertools import combinations


def solution(info, query):

    info_dict = {}
    for entry in info:
        lang, job, career, food, score_s = entry.split()
        score = int(score_s)
        attrs = [lang, job, career, food]
        # 4개 속성 중 몇 개를 '-'로 대체할지 (0~4개)
        for r in range(5):
            # r개 속성을 '-'로 치환하는 조합
            for comb in combinations(range(4), r):
                key = attrs[:]  # 복사
                for idx in comb:
                    key[idx] = '-'
                key_str = ' '.join(key)
                print(key_str)
                info_dict.setdefault(key_str, []).append(score)
                print(info_dict)

    print("========")
    print(info_dict)
    # 2) 각 키별 점수 리스트를 정렬 (이분탐색을 위해)
    for lst in info_dict.values():
        lst.sort()
    print(info_dict)

    # 3) 각 query 에 대해, 해당 키의 리스트에서 점수 이상인 원소 개수를 이분탐색으로 구함
    answer = []
    for q in query:
        # "java and backend and junior and pizza 100" → ['java','backend','junior','pizza'], '100'
        parts = q.split(' and ')
        print(parts)
        last_food, score_s = parts[-1].split()
        key_parts = parts[:-1] + [last_food]
        key_str = ' '.join(key_parts)

        scores = info_dict.get(key_str, [])
        # bisect_left: score_s 이상의 첫 인덱스
        idx = bisect.bisect_left(scores, int(score_s))
        answer.append(len(scores) - idx)

    return answer


if __name__ == '__main__':
    info = ["java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"]
    query = ["java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"]
    print(solution(info, query))
