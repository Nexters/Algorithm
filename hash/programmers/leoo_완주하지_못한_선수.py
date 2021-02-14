"""
완주하지 못한 선수
- https://programmers.co.kr/learn/courses/30/lessons/42576

- category: hash
- condition
    - 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
    - completion의 길이는 participant의 길이보다 1 작습니다.
    - 참가자 중에는 동명이인이 있을 수 있습니다.
"""
from collections import Counter


def solution(participant, completion):
    """
    p_counter.subtract(c_counter) vs p_counter - c_counter
    subtract은 음수를 허용하며, a-b는 음수를 허용하지 않는다는 차이가 존재한다.
    해당 문제에서는 __sub__가 더 맞는 방법일듯 하다.
    """

    p_counter, c_counter = map(Counter, (participant, completion))
    p_counter-= c_counter
    return next(p_counter.elements(), None)

expect = "leo"
input_args = (["leo", "kiki", "eden"], ["eden", "kiki"])
assert solution(*input_args) == expect