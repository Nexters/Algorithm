'''
2021 kakao 메뉴 리뉴얼
orders: 각 손님들이 주문한 메뉴들이 담긴 배열
course: 코스요리를 구성하는 단품 메뉴의 갯수가 담긴 배열
'''

from itertools import combinations


def solution(orders, course):
    answer = []

    for c in course:
        temp = []
        count_list = dict()

        for o in orders:
            temp += combinations(sorted(o), c)  # order에서 course개만큼 고르는 모든 조합 구하기

        for t in temp:  # dict에서 개수만큼 count 올려주기
            if t in count_list:
                count_list[t] += 1
            else:
                count_list[t] = 1
        count_list = sorted(count_list.items(), key=lambda x: x[1], reverse=True)

        if len(count_list) == 0 or count_list[0][1] < 2:  # 길이가 0이거나 최대 카운트 수가 2보다 작으면
            continue

        for count in count_list:
            if count[1] < 2 or count_list[0][1] != count[1]:
                break
            answer.append(''.join(count[0]))

    answer.sort()
    return answer


solution(["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"], [2, 3, 4])
solution(["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"], [2, 3, 5])
solution(["XYZ", "XWY", "WXA"], [2, 3, 4])
