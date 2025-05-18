"""
접근 방법 브레인 스토밍
 - n * n 2차원 배열을 만들고 각 배열의 값을 채운다. 이중 for문? n의 값이 제한사항을 보니 10^7까지라서 O(n^2) 시간복잡도면 문제날 것 같은데?
 - 필요한 구간만 리스트에 저장해두기(left ~ right)
"""


def solution(n, left, right):

    answer = []

    for i in range(left, right+1):
        row = i // n # 몫
        col = i % n # 나머지
        print(f"row, col = {row}, {col}")
        answer.append(max(row, col) + 1)


    return answer


if __name__ == '__main__':
    n = 3
    left = 2
    right = 5
    print(solution(n, left, right))
