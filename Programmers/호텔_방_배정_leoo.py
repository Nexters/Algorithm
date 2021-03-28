"""
호텔 방 배정
- https://programmers.co.kr/learn/courses/30/lessons/64063

- category: hash, union find
- condition
  1. room_number 배열 각 원소들의 값은 1 이상 k 이하인 자연수입니다.
  2. 고객이 원하는 방이 이미 배정되어 있으면 원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방을 배정합니다.
  3. room_number 배열은 모든 고객이 방을 배정받을 수 있는 경우만 입력으로 주어진다.
"""

# O(2*n) for worst case
from sys import setrecursionlimit
import typing

setrecursionlimit(10 ** 9)


def solution(_, room_number):
    rooms: typing.Dict[int, int] = dict()
    answer: typing.List[int] = []

    def find(idx):
        if not rooms.get(idx):
            return idx

        rooms[idx] = find(rooms[idx])
        return root

    for want in room_number:
        able = find(want)
        # condition3에 의해, want 방번호 보다 큰 경우만 확인 하면 된다.
        rooms[able] = find(able + 1)  # condition2
        answer.append(able)
    return answer
expect = [1,2,3,4,5,6,7,8,9,10]
input_args = (10, (1,)*10)
assert solution(*input_args) == expect

"""
# O(n^2)
def solution(k, room_number):
    N = len(room_number)
    set_room = set(room_number)
    _min, _max = min(set_room), max(set_room)
    ceil = _max+(N-len(set_room))
    
    room = list(range(_min,ceil+1))
    visited = [0]*len(room)

    answer = []
    for rn in room_number:
        idx = rn-_min
        while True:
            if not visited[idx]:
                visited[idx] = 1
                answer.append(idx+_min)
                break
            else:
                idx+=1
    return answer
"""